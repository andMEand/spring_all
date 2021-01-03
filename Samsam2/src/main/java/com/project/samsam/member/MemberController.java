package com.project.samsam.member;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private MailSendService mss;

	@RequestMapping("/login.me")
	public String userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		int res = memberService.userCheck(memberVO);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1) {
			session.setAttribute("id", memberVO.getEmail()); // 세션객체에 아이디 저장 로그인 유지를 유해
			writer.write("<script>alert('로그인 성공!!');" + "location.href='./boardlist.bo';</script>");

			// return "redirect:/main.me";
		} else {
			writer.write("<script>alert('로그인 실패!!');location.href='./loginform.me';</script>");
			// return "redirect:/loginform.me";
		}
		return null;
	}

	@RequestMapping("/home.me")
	public String mainPage() throws Exception {
		return "member/home";
	}
	
	@RequestMapping("/joinform.me")
	public String joinForm() throws Exception {
		return "member/joinForm";
	}

	@RequestMapping("/loginform.me")
	public String loginForm() throws Exception {
		return "member/loginForm";
	}

//	@RequestMapping("/joinprocess.me")
//	public String insertMember(MemberVO memberVO, HttpServletResponse response) throws Exception {
//		System.out.println(1);
//		int res = memberService.insertMember(memberVO);
//		System.out.println(2);
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter writer = response.getWriter();
//		if (res != 0) {
//			writer.write("<script>alert('회원 가입 성공!!');" + "location.href='./loginform.me';</script>");
//		} else {
//			writer.write("<script>alert('회원 가입 실패!!');" + "location.href='./joinform.me';</script>");
//		}
//		return null;
//	}
	
	@RequestMapping("/signUp.me")
	public String signUp(@ModelAttribute MemberVO memberVO) {
		System.out.println(memberVO.getNick());
		 // DB에 기본정보 insert
		memberService.insertMember(memberVO);
		System.out.println("인서트완료");
		//임의의 authKey생성 & 이메일 발송
		String authKey = mss.sendAuthMail(memberVO.getEmail());
		memberVO.setAuthKey(authKey);
		System.out.println(3);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", memberVO.getEmail());
		map.put("authKey", memberVO.getAuthKey());
		System.out.println("맵" + map.get("email") + "인증키 " + map.get("authKey"));
		
		//DB에 authKey업데이트
		memberService.updateAuthKey(map);
		return "member/email_check";
		
	}
	
	 @GetMapping("/signUpConfirm.me")
	 public ModelAndView signUpConfirm(@RequestParam HashMap<String, Integer> map, ModelAndView mav){
	    //email, authKey 가 일치할경우 authStatus 업데이트
		System.out.println("연결된 email :" + map.get("email"));
	    memberService.updateAuthStatus(map);
	    
	    mav.addObject("display", "/member/loginForm.jsp");
	    mav.setViewName("/member/loginForm");
	    return mav;
	}

	@RequestMapping("/memberlist.me")
	public String getMemberlist(Model model) throws Exception {
		ArrayList<MemberVO> member_list = memberService.getMemberlist();
		model.addAttribute("member_list", member_list);

		return "member/member_list";
	}

	@RequestMapping("/memberinfo.me")
	public String selectMember(MemberVO memberVO, Model model) throws Exception {
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);

		return "member/member_info";
	}

	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO, Model model) throws Exception {
		memberService.deleteMember(memberVO);

		return "redirect:/memberlist.me";
	}
}