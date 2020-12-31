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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService; // BoardService��ü�� ��������־�� �Ѵ�
	
	@RequestMapping("/home_search.me")
	public String getSearchlist(@RequestParam(value="keyword", required= true, defaultValue="")String keyword, Model model){
		try {
        List<BoardVO2> searchList = boardService.getSearchList(keyword);
        model.addAttribute("searchList", searchList);
        System.out.println("searchList" + searchList.get(0));

		}
		catch(Exception e) {
			System.out.println("�˻� ����(��) : " + e.getMessage());
		}
        return "board/ho_search_list";    //�Խ��� �������� �̵�
        
	}
	
	@RequestMapping("/Sboarddetail.bo")
	public String getSDetail(@RequestParam(value ="b_no", required = true) int num, Model model) {
		try {
		BoardVO2 bvo = boardService.getSDetail(num);

		model.addAttribute("bvo", bvo);
		System.out.println("�ۺ���"+bvo);
		}catch(Exception e) {
			e.getMessage();
		}
		return "board/ho_search_view";
	}
	//Ȩ������ �˻� ��
	@RequestMapping("adminboard.do")
	public String adminboard() throws Exception {
		return "admin/admin_board";
	}
	
	@RequestMapping(value="/boardFind.do",
			method = RequestMethod.POST,
			produces= "application/json;charset=utf-8")
	@ResponseBody
	public List<ABoardVOto> getABoardList(Model model) throws Exception {
		List<ABoardVOto> list = boardService.findList();
		model.addAttribute("list",list);
		return list;
		}
	//���� �Խñ� ���� ��


	//////////////////////////////////////////////////////////
	
//	@RequestMapping("/boarddetail.bo")
//	public String getSearchLink(@RequestParam(value = "num", required = true) int num, Model model) {
//		BoardVO vo = boardService.getDetail(num);
//
//		model.addAttribute("vo", vo);
//
//		return "board/qna_board_view";
//	}
	
	@RequestMapping("/boardlist.bo")
	public String getBoardlist(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("1");
		int limit = 10; //�Խñ� ����
		//// @RequestParam��û�Ķ���͸� �о�ö� valuer��page�϶� int page�� �������� �����߻�X
		// ������ �Ķ���Ͱ� ������ �⺻ ��: defaultValue 1
		// ���������� ����� �� ����
		int listcount = boardService.getListCount();
		System.out.println("2");
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		hashmap.put("startrow", startrow);
		hashmap.put("endrow", endrow);
		List<BoardVO> boardlist = boardService.getBoardList(hashmap);

		// �� ������ ��
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95�� ���ؼ� �ø� ó��
		// ���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// ���� �������� ������ ������ ������ ��(10, 20, 30 ��...)
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
//		MultipartFile mf = vo.getFile(); // ������ ������ �ҷ��ð� ����.
//		String uploadPath = "C:\\Project\\upload\\";
//
//		if (mf.getSize() != 0) {
//			String originalFileExtension = mf.getOriginalFilename()
//					.substring(mf.getOriginalFilename().lastIndexOf("."));
//			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
//			// �������ּҿ� ���� ���� - ������ ������ �����Ҽ� ����. ���Ͼ��̵� ����Ҽ� �ְ� �Ϸ��� file�� null���� �ƴ��� if�� �˻�
//			// �ϰų�...
//
//			// mf.transferTo(new File(uploadPath+"/"+mf.getOriginalFilename()));
//			mf.transferTo(new File(uploadPath + storedFileName)); // ����ó�� ��� �ʿ���.
//			// mf.transferTo ���� ���ε� �߻�.
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
	 * // ���� ���� �˻� ����(����)
	 * 
	 * @RequestMapping("/boardmodify.bo") public String boardModify(BoardVO vo) {
	 * boolean res = boardService.boardModify(vo);
	 * 
	 * return "redirect:/boarddetail.bo?num=" + vo.getNum(); }
	 */
	// ���� ���� �˻�(������)
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
			writer.write("<script>alert('���� ����!!');" + "location.href='./boardlist.bo';</script>");
		} else {
			writer.write("<script>alert('���� ����(���Ѿ���)!!');" + "location.href='./boardlist.bo';</script>");
		}
		return null;
	}

	// ���� �ٿ�ε� ���
	@RequestMapping("/filedownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");

		String num = request.getParameter("num");
		String of = request.getParameter("of"); // ������ ���ε�� ����� ���� ���ϸ�
		String of2 = request.getParameter("of2"); // �������� ���ϸ�

		/*
		 * //������Ʈ ��Ʈ���丮�� ���� ��ũ���� ��� �˾Ƴ���. String uploadPath =
		 * request.getSession().getServletContext().getRealPath("/upload"); String
		 * fullPath = uploadPath+"/"+of;
		 */
		String uploadPath = "C:\\Project\\upload\\"; // ���� ��� ����
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);

		// ���� �ٿ�ε带 ���� ������ Ÿ���� application/download ����
		response.setContentType("application/download; charset=UTF-8");
		// ���� ������ ����
		response.setContentLength((int) downloadFile.length());
		// �ٿ�ε� â�� ���� ���� ��� ����
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
