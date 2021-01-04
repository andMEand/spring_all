package com.project.samsam.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@RequestMapping("/adminboard.do")
	public String adminboard() throws Exception {
		return "admin/admin_board";
	}
	//어드민 게시글 관리 뷰 끝
	
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
	

}
