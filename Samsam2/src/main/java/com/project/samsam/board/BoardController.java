package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.samsam.member.MemberVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService; // BoardService빈객체가 만들어져있어야 한다
	
	@RequestMapping("/home_search.me")
	public String getSearchlist(@RequestParam(value="keyword", required= true, defaultValue="")String keyword, Model model){
		try {
        List<BoardVO> searchList = boardService.getSearchList(keyword);
        model.addAttribute("searchList", searchList);
        System.out.println("searchList" + searchList.get(0));

		}
		catch(Exception e) {
			System.out.println("검색 에러(컨) : " + e.getMessage());
		}
        return "board/ho_search_list";    //게시판 페이지로 이동
        
	}
	
	@RequestMapping("/Sboarddetail.bo")
	public String getSDetail(@RequestParam(value ="b_no", required = true) int b_no, Model model) {
		try {
		BoardVO bvo = boardService.getSDetail(b_no);

		model.addAttribute("bvo", bvo);
		System.out.println("글보기"+bvo);
		}catch(Exception e) {
			e.getMessage();
		}
		return "board/ho_search_view";
	}
	//홈페이지 검색 끝
	
////////////////////////////	
	@RequestMapping("/adminboard.do")
	public String adminboard() throws Exception {
		return "admin/admin_board";
	}
	

	
	@RequestMapping(value="/boardFind.do",
						produces= "application/json;charset=utf-8")
	@ResponseBody
	public List<ABoardVOto> getAFindList(@RequestBody ABoardVO abvo) throws Exception {
		System.out.println("startDate : " + abvo.getStartDate());
		System.out.println("startDate : " + abvo.getEndDate());
		System.out.println("startDate : " + abvo.getKeyword());
		System.out.println("startDate : " + abvo.getKategorie());
		List<ABoardVOto> list = boardService.findList(abvo);
		System.out.println(3);

		System.out.println(2);
		return list;
		}
	//어드민 게시글관리 일반리스트 함수
	
	@RequestMapping(value="/boardWFind.do",
			produces= "application/json;charset=utf-8")
	@ResponseBody
	public List<ABoardVOto> getFindWList(@RequestBody ABoardVO abvo) throws Exception {
		System.out.println("startDate : " + abvo.getStartDate());
		System.out.println("startDate : " + abvo.getEndDate());
		System.out.println("startDate : " + abvo.getKeyword());
		System.out.println("startDate : " + abvo.getKategorie());
		System.out.println("kind : " + abvo.getKind());
		List<ABoardVOto> list = boardService.find_w_List(abvo);
		System.out.println(3);

		System.out.println(2);
		return list;
		}
	//어드민 게시글관리 신고리스트 함수 
	////////////////////
	//어드민 게시글 view Modal 함수
	
	@RequestMapping(value="/board_detail.do",
			produces="application/json;charset=utf-8")
	@ResponseBody
	public HashMap<Object,Object> map (@RequestBody ADModalVO mvo) throws Exception{
		System.out.println(" map");
		HashMap<Object,Object> map = new HashMap<Object, Object>(); 
		System.out.println("카테고리 : "+ mvo.getCategory());
		MemberVO vo = boardService.adModalView_m(mvo);
		if(vo != null) {
			map.put("MemberVO", vo);
		}
		else {
			System.out.println("modal MemberVO null");
		}
		ABoardVOto bvo = boardService.adModalView_b(mvo);
		if(bvo != null) {
			map.put("ABoardVOto", bvo);
		}
		else {
			System.out.println("modal ABoardVOto null");
		}
		
		List<CommentVO> cList = boardService.adModalView_c(mvo);
		if(cList != null) {
			map.put("cList", cList);
		}
		else {
			System.out.println("modal cList null");
		}
		CommentVO ccount = boardService.adModalView_ccount(mvo);
		if(ccount != null) {
			map.put("CommentVO", ccount);
		}
		else {
			System.out.println("modal ccount null");
		}
		
		List<WarningVO> wList = boardService.adModalView_w(mvo);
		if(wList != null) {
			map.put("wList", wList);
		}
		else {
			System.out.println("modal cList null");
		}
		WarningVO wcount = boardService.adModalView_wcount(mvo);
		if(wcount != null) {
			map.put("WarningVO", wcount);
		}
		else {
			System.out.println("modal wcount null");
		}
		
		return map;
	}
	
	
	

}
