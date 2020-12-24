package com.spring.springmember;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //클래스 첫글자 소굼자 빈객체 만들어짐 //제어만 할 수 있도록 코드작성함
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	//MemberService 객체가 memberService대입된다
	
	@RequestMapping("/login.me")
	public String userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception{
		int res= memberService.usercheck(memberVO);
		
		
		  response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if(res ==1) {
			session.setAttribute("id", memberVO.getId());
			writer.write("<script>alert('로그인 성공!!!');"+"location.href='main.me';</script>");
			//return "redirect:/main.me;
		
		}
		else {
			writer.write("<script>alert('로그인 실패!!!');"+ "location.href='./loginform.me';</script>");
			//return "redirect:/loginform.me;
			 
			
	
		/*
		
		if(res ==1) {
			session.setAttribute("id", memberVO.getId());
			
			return "redirect:/main.me";
		
		}
		else {
			
			return "redirect:/loginform.me";
			*/
			
	}
		return null;

	}
	@RequestMapping("/main.me")
	public String mainPage() throws Exception {
		return "main";
	}
	@RequestMapping("/loginform.me")
	public String loginform() throws  Exception{
		return "loginForm";
	}
	
	@RequestMapping("/joinform.me")
	public String joinform() throws  Exception{
		return "joinForm";
	}
	
	@RequestMapping("/joinprocess.me")
	public String insertMember(MemberVO memberVO, HttpServletResponse response) throws  Exception{
		int res = memberService.insertMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('회원가입 성공!!');" + "location.href='loginform.me';</script>");
		}
		else {
			writer.write("<script>alert('회원가입 실패!!');" + "location.href='joinform.me';</script>");
		}
		return null;
	}
	
	@RequestMapping("/memberlist.me")  //model 조회한 결과물을 전달하기 위해
	public String getMemberlist(Model model) throws  Exception{
		ArrayList<MemberVO> member_list	 = memberService.getMemberlist();
		model.addAttribute("member_list",member_list);
		
		
		return "member_list";
	}
	
	@RequestMapping("/memberinfo.me")//상세보기
	public String selectMember(MemberVO memberVO, Model model) throws  Exception{
		MemberVO vo =memberService.selectMember(memberVO);
		model.addAttribute("memberVO",vo);
		
		return "member_info";
	}
	
	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO, Model model) throws  Exception{
		memberService.deleteMember(memberVO);
		
		
		return "redirect:/memberlist.me";
	}
	
	@RequestMapping("/memberupdate2.me")
	public String updateMember2(MemberVO memberVO, Model model,HttpServletResponse response) throws  Exception{
		int res = memberService.updateMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('수정 성공!!');" + "location.href='memberlist.me';</script>");
		}
		else {
			writer.write("<script>alert('수정 실패!!');" + "location.href='memberlist.me';</script>");
		}
		return null;
	}
	
	@RequestMapping("/memberupdate.me")
	public String updateMember(MemberVO memberVO, Model model) throws  Exception{
		MemberVO vo =memberService.selectMember(memberVO);
		model.addAttribute("memberVO",vo);
		
		return "member_update";
	}
	/*
	 @RequestMapping("/memberupdate.me")//상세보기
	public String updateFormMember(MemberVO memberVO, Model model) throws  Exception{
		MemberVO vo =memberService.selectMember(memberVO);
		model.addAttribute("memberVO",vo);
		
		return "member_update";
	}
	@RequestMapping("/memberupdate2.me")
	public String updateMember2(MemberVO memberVO, Model model,HttpServletResponse response) throws  Exception{
		int res = memberService.updateMember(memberVO);
		
		
		return null;
		}
	 */
	
	
	
	
}
