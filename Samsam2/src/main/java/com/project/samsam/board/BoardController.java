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
	private BoardService boardService; // BoardService��ü�� ��������־�� �Ѵ�
	
	@RequestMapping("/home_search.me")
	public String getSearchlist(@RequestParam(value="keyword", required= true, defaultValue="")String keyword, Model model){
		try {
        List<BoardVO> searchList = boardService.getSearchList(keyword);
        model.addAttribute("searchList", searchList);
        System.out.println("searchList" + searchList.get(0));

		}
		catch(Exception e) {
			System.out.println("�˻� ����(��) : " + e.getMessage());
		}
        return "board/ho_search_list";    //�Խ��� �������� �̵�
        
	}
	
	@RequestMapping("/Sboarddetail.bo")
	public String getSDetail(@RequestParam(value ="b_no", required = true) int b_no, Model model) {
		try {
		BoardVO bvo = boardService.getSDetail(b_no);

		model.addAttribute("bvo", bvo);
		System.out.println("�ۺ���"+bvo);
		}catch(Exception e) {
			e.getMessage();
		}
		return "board/ho_search_view";
	}
	//Ȩ������ �˻� ��
	
	
	@RequestMapping("/adminboard.do")
	public String adminboard() throws Exception {
		return "admin/admin_board";
	}
	//���� �Խñ� ���� �� ��
	
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
	//���� �Խñ۰��� �Ϲݸ���Ʈ �Լ�
	
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
	
	//���� �Խñ۰��� �Ű���Ʈ �Լ�
	

}
