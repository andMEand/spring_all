package com.project.samsam.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService; // BoardService빈객체가 만들어져있어야 한다
	
	@RequestMapping("/home_search.me")
	public String getSearchlist(
		
		@RequestParam(defaultValue="1")int page) {
		@RequestParam(defaultValue="") String keyword //키워드 기본값
		)throws Exception{
			
			
		}
		System.out.println("서치 컨트"+page);
		int count =1000;
		
		//페이지 관련 설정, 시작번호와 끝번호를 구해서 각각 변수에 저장한다.
        Pager pager = new Pager(count, curPage);
        int start = pager.getPageBegin();
        int end =  pager.getPageEnd();
		
      //map에 저장하기 위해 list를 만들어서 검색옵션과 키워드를 저장한다.
        List<MemberBoardDTO> list = boardService.getSearchList(keyword, start, end);
        
        ModelAndView mav = new ModelAndView();
        Map<String,Object> map = new HashMap<>();
        
        map.put("list", list);                         //map에 list(게시글 목록)을 list라는 이름의 변수로 자료를 저장함.
        map.put("pager", pager);
        map.put("count", count);
        map.put("keyword", keyword);
        mav.addObject("map", map);                    //modelandview에 map를 저장
        
        System.out.println("map : "+map);
        mav.setViewName("board/home_search");                //자료를 넘길 뷰의 이름
        
        return mav;    //게시판 페이지로 이동
        
	}
	
	

	@RequestMapping("/boardlist.bo")
	public String getBoardlist(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("1");
		int limit = 10; //게시글 갯수
		//// @RequestParam요청파라메터를 읽어올때 valuer가page일때 int page에 폴스여도 에러발생X
		// 페이지 파라메터가 없을때 기본 값: defaultValue 1
		// 한페이지에 출력한 글 갯수
		int listcount = boardService.getListCount();
		System.out.println("2");
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		hashmap.put("startrow", startrow);
		hashmap.put("endrow", endrow);
		List<BoardVO> boardlist = boardService.getBoardList(hashmap);

		// 총 페이지 수
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림 처리
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);

		return "board/qna_board_list";
	}

	@RequestMapping("/boardwriteform.bo")
	public String boardInsertForm() {

		return "board/qna_board_write";
	}

//	@RequestMapping("/boardwrite.bo")
//	public String boardInsert(BoardVO vo) throws Exception {
//		MultipartFile mf = vo.getFile(); // 파일이 없으면 불러올게 없음.
//		String uploadPath = "C:\\Project\\upload\\";
//
//		if (mf.getSize() != 0) {
//			String originalFileExtension = mf.getOriginalFilename()
//					.substring(mf.getOriginalFilename().lastIndexOf("."));
//			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
//			// 지정한주소에 파일 저장 - 파일이 없으면 수행할수 없음. 파일없이도 등록할수 있게 하려면 file이 null인지 아닌지 if로 검사
//			// 하거나...
//
//			// mf.transferTo(new File(uploadPath+"/"+mf.getOriginalFilename()));
//			mf.transferTo(new File(uploadPath + storedFileName)); // 예외처리 기능 필요함.
//			// mf.transferTo 실제 업로드 발생.
//			vo.setOrg_file(mf.getOriginalFilename());
//			vo.setUp_file(storedFileName);
//		} else {
//			vo.setOrg_file("");
//			vo.setUp_file("");
//		}
//		int res = boardService.boardInsert(vo);
//
//		return "redirect:/boardlist.bo";
//	}

	@RequestMapping("/boarddetail.bo")
	public String getDetail(@RequestParam(value = "num", required = true) int num, Model model) {
		BoardVO vo = boardService.getDetail(num);

		model.addAttribute("vo", vo);

		return "board/qna_board_view";
	}

	@RequestMapping("/boardreplyform.bo")
	public String boardReplyForm(@RequestParam(value = "num", required = true) int num, Model model) {
		BoardVO vo = boardService.getDetail(num);

		model.addAttribute("vo", vo);

		return "board/qna_board_reply";
	}

	@RequestMapping("/boardreply.bo")
	public String boardReply(BoardVO vo) throws Exception {
		int res = boardService.boardReply(vo);

		return "redirect:/boardlist.bo";
	}

	@RequestMapping("/boardmodifyform.bo")
	public String getModifyForm(@RequestParam(value = "num", required = true) int num, Model model) {
		BoardVO vo = boardService.getDetail(num);

		model.addAttribute("vo", vo);

		return "board/qna_board_modify";
	}

	/*
	 * // 수정 권한 검사 안함(원본)
	 * 
	 * @RequestMapping("/boardmodify.bo") public String boardModify(BoardVO vo) {
	 * boolean res = boardService.boardModify(vo);
	 * 
	 * return "redirect:/boarddetail.bo?num=" + vo.getNum(); }
	 */
	// 수정 권한 검사(수정본)
//	@RequestMapping("/boardmodify.bo")
//	public String boardModify(BoardVO vo) {
//		int res = boardService.boardModify(vo);
//		return "redirect:/boarddetail.bo?num="+ vo.getNum();
//		
//	}
//		

	@RequestMapping("/boarddelete.bo")
	public String boardDelete(@RequestParam(value = "num", required = true) int num, HttpSession session,
			HttpServletResponse response) throws Exception {
		String id = (String) session.getAttribute("id");
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("num",  Integer.toString(num));
		hashmap.put("id",  id);
		int res= boardService.boardDelete(hashmap);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1) {
			writer.write("<script>alert('삭제 성공!!');" + "location.href='./boardlist.bo';</script>");
		} else {
			writer.write("<script>alert('삭제 실패(권한없음)!!');" + "location.href='./boardlist.bo';</script>");
		}
		return null;
	}

	// 파일 다운로드 방식
	@RequestMapping("/filedownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");

		String num = request.getParameter("num");
		String of = request.getParameter("of"); // 서버에 업로드된 변경된 실제 파일명
		String of2 = request.getParameter("of2"); // 오리지날 파일명

		/*
		 * //웹사이트 루트디렉토리의 실제 디스크상의 경로 알아내기. String uploadPath =
		 * request.getSession().getServletContext().getRealPath("/upload"); String
		 * fullPath = uploadPath+"/"+of;
		 */
		String uploadPath = "C:\\Project\\upload\\"; // 직접 경로 지정
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);

		// 파일 다운로드를 위해 컨테츠 타입을 application/download 설정
		response.setContentType("application/download; charset=UTF-8");
		// 파일 사이즈 지정
		response.setContentLength((int) downloadFile.length());
		// 다운로드 창을 띄우기 위한 헤더 조작
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(of2.getBytes(), "ISO8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary");

		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();

		byte[] buf = new byte[1024];
		int size = -1;

		while ((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}
}
