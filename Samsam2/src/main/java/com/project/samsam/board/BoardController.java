package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private BoardService boardService; // BoardService��ü�� ��������־�� �Ѵ�
	
	@RequestMapping("/home_search.me")
	public String getSearchlist(@RequestParam(value="keyword", required= true, defaultValue="")String keyword, Model model){
			//Ŀ�´�Ƽ
			List<BoardVO> c_list = boardService.getSearch_commu_List(keyword);
		try {
			if(c_list != null) {
				model.addAttribute("community",c_list); 
			}
			else{
				System.out.println("community");
			}
		}
		catch(Exception e) {
			System.out.println("�˻� ����(Ŀ�´�Ƽ) : " + e.getMessage());
		}
		//�о�
		List<BoardVO> a_list = boardService.getSearch_adopt_List(keyword);
		try {
			if(a_list != null) {
				model.addAttribute("adopt_list",a_list); 
			}
			else{
				System.out.println("adopt_list");
			}
		}
		catch(Exception e) {
			System.out.println("�˻� ����(�о�) : " + e.getMessage());
		}
		//å�Ӻо�
		List<BoardVO> f_list = boardService.getSearch_free_List(keyword);
		try {
			if(f_list != null) {
				model.addAttribute("free_doc",f_list); 
			}
			else{
				System.out.println("free_doc");
			}
		}
		catch(Exception e) {
			System.out.println("�˻� ����(å�Ӻо�) : " + e.getMessage());
		}
		
           return "board/ho_search_list";
        
	}
	
	@RequestMapping("/s_board_detail.bo")
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
	//Ȩ������ ���� ���̵�   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>a href �̵��� ���� �޼ҵ� �����ʿ�
	
////////////////////////////	
	//������ ������ ��Ʈ�ѷ�
	
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
	////////////////////
	//���� �Խñ� view  �Լ�
	
	@RequestMapping(value="/admin_b_detail.do",
			produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> map (@RequestBody ADModalVO mvo) throws Exception{
		System.out.println("��Ʈ�ѷ� board_detail.do");
		Map<String,Object> map = new HashMap<String, Object>(); 
		System.out.println("ī�װ� : "+ mvo.getCategory());
		MemberVO vo = boardService.adModalView_m(mvo);
		if(vo != null) {
			System.out.println("vo.grade : " +vo.getGrade());
			map.put("MemberVO", vo);
		}
		else {
			System.out.println("modal MemberVO null");
		}
		ABoardVOto bvo = boardService.adModalView_b(mvo);
		if(bvo != null) {
			String category=mvo.getCategory();
			if(category.equals("community")) {
				bvo.setCategory("Ŀ�´�Ƽ");
				map.put("ABoardVOto", bvo);
			}
			else{
				bvo.setCategory("�о�Խ���");
				map.put("ABoardVOto", bvo);
			}
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
		CommentVO co_count = boardService.adModalView_ccount(mvo);
		if(co_count != null) {
			map.put("CommentVO", co_count);
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
		
		System.out.println(map);
		return map;
	}
	
	
	

}
