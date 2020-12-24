package com.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MybatisController {
	@Autowired
	private MemberServiceImpl memberService;

	@RequestMapping("/main.me")
	public String mainPage() throws Exception {
		return "main";
	}

	@RequestMapping("/loginform.me")
	public String loginform() throws Exception {
		return "loginForm";
	}

	@RequestMapping("/joinform.me")
	public String joinform() throws Exception {
		return "joinForm"; 
	}
	@RequestMapping("/updateform.me")
	public String updateform(MemberVO memberVO, Model model) throws Exception {
		System.out.println("폼");
		MemberVO member =memberService.getMember(memberVO.getId());
		model.addAttribute("member", member);
		
		System.out.println("폼2");
		return "member_update";
	}

	@RequestMapping("/login.me")
	public String userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		
		MemberVO vo = memberService.getMember(memberVO.getId());

		if (vo != null) {
			if (vo.getPassword().equals(memberVO.getPassword()))
				session.setAttribute("id", memberVO.getId());
			return "redirect:/main.me";
		} else {
			return "redirect:/loginform.me";
		}
	}
	
	@RequestMapping("/joinprocess.me")
	public ModelAndView joinprocess(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView();
		memberService.insertMember(memberVO);
		
		result.setViewName("loginForm");
		return result;
	}
	
	
	@RequestMapping("/memberlist.me")  //model 조회한 결과물을 전달하기 위해
	public ModelAndView member_list() throws  Exception{
		ModelAndView result = new ModelAndView();
		ArrayList<MemberVO> member_list	 =(ArrayList<MemberVO>) memberService.getMembers();
		result.addObject("member_list",member_list);
		result.setViewName("member_list");
		
		return result;
	}
	@RequestMapping("/memberinfo.me")  //model 조회한 결과물을 전달하기 위해
	public ModelAndView member_info(MemberVO memberVO) throws  Exception{
		ModelAndView result = new ModelAndView();
		MemberVO member= memberService.getMember(memberVO.getId());
		result.addObject("member",member);
		result.setViewName("member_info");
		
		return result;
	}
	@RequestMapping("/memberdelete1.me")  //model 조회한 결과물을 전달하기 위해
	public ModelAndView member_delete(MemberVO  memberVO) throws  Exception{
		ModelAndView result = new ModelAndView();
		memberService.deleteMember(memberVO.getId());
		ArrayList<MemberVO> member_list = memberService.getMembers();
		result.addObject("member_list", member_list);
		result.setViewName("member_list");
		
		return result;
	}
	
	@RequestMapping("/memberdelete.me")  //model 조회한 결과물을 전달하기 위해
	public String member_delete2(MemberVO  memberVO) throws  Exception{
		
		memberService.deleteMember(memberVO.getId());
		
		return "redirect:/memberlist.me";
	}
	@RequestMapping("/memberupdate1.me")  //model 조회한 결과물을 전달하기 위해
	public ModelAndView member_update1(MemberVO  memberVO) throws  Exception{
		ModelAndView result = new ModelAndView();
		memberService.updateMember(memberVO);
		ArrayList<MemberVO> member_list = memberService.getMembers();
		result.addObject("member_list", member_list);
		result.setViewName("member_list");
		return result;
	}
	@RequestMapping("/memberupdate.me")  //model 조회한 결과물을 전달하기 위해
	public String member_update(MemberVO  memberVO) throws  Exception{
		memberService.updateMember(memberVO);
		return  "redirect:/memberlist.me";
	}
	

	
}
