package com.project.samsam.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private MailSendService mss;

	
	@RequestMapping("/home.me")
	public String mainPage() throws Exception {
		return "member/home";
	}
	
	@RequestMapping(value = "/loginform.me")
	public String login_Form() {

		return "member/loginForm";
	}
	
	//īī���α���
	@RequestMapping(value = "/kkoLogin.me")
	public String kko_Join(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
		System.out.println("�̸���: " + mvo.getEmail() + "�г��� : " + mvo.getNick());
		
		if(memberService.selectMember(mvo.getEmail()) == null) {
			mvo.setGrade("īī��");
			model.addAttribute("MemberVO", mvo);
			return "member/k_joinForm";
		}
		else {
			redi_attr.addAttribute("email", mvo.getEmail());
			return "redirect:/login.me";
			}
	}
	
	//īī������ ȸ������
	@RequestMapping(value = "/kkoJoin.me")
	public String kko_joinProcess(MemberVO mvo) {
		System.out.println("īī��ȸ������" + mvo.getGrade());
		int res = memberService.k_joinMember(mvo);
		if(res == 1) {
			return "member/loginForm";
		}
		else {
			return "member/k_joinform";
		}
	}
	
	@RequestMapping(value = "/login.me")
	public String userCheck(@RequestParam("email") String email, MemberVO vo, HttpSession session) throws Exception {
		System.out.println("�α��� �̸��� "+vo.getEmail());
		System.out.println("�α��� ��й�ȣ "+vo.getPw());
		
		
		if(vo.getEmail().equals("admin")) {
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("email", vo.getEmail());
			
			return "redirect:/home.me";  //���� �������� ���� �ʿ�
		}
		
		MemberVO res = memberService.selectMember(vo.getEmail());
		if(res.getGrade().equals("īī��")) {
			session.setAttribute("email", res.getEmail());
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do";//����� ������������ ���� �ʿ�
				}
			}
			return "redirect:/home.me";//������������ ���� �ʿ�
		}
		
		if(res.getPw().equals(vo.getPw())) {
			session.setAttribute("id", res.getEmail());
			session.setAttribute("email", res.getEmail());
			System.out.println("session id :" +session.getAttribute("id"));
			System.out.println("session email :" +session.getAttribute("email"));
			
			//�����ȸ������ Ȯ��
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do"; //����� ������������ ���� �ʿ�
				}
			}
			return "redirect:/home.me";  //������������ ���� �ʿ�
		}else {
			return "redirect:/loginForm.me";
		}
	}
	
	
	/////
	
	@RequestMapping("/joinform.me")
	public String joinForm() throws Exception {
		return "member/joinForm";
	}


///////////////////////////////////
	
	@RequestMapping("/signUp.me")
	public String signUp(@ModelAttribute MemberVO memberVO) {
		System.out.println(memberVO.getNick());
		 // DB�� �⺻���� insert
		memberService.joinMember(memberVO);
		System.out.println("�μ�Ʈ�Ϸ�");
		//������ authKey���� & �̸��� �߼�
		String authKey = mss.sendAuthMail(memberVO.getEmail());
		memberVO.setAuthKey(authKey);
		System.out.println(3);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", memberVO.getEmail());
		map.put("authKey", memberVO.getAuthKey());
		System.out.println("��" + map.get("email") + "����Ű " + map.get("authKey"));
		
		//DB�� authKey������Ʈ
		memberService.updateAuthKey(map);
		return "member/email_check";
		
	}
	
	 @GetMapping("/signUpConfirm.me")
	 public ModelAndView signUpConfirm(@RequestParam HashMap<String, Integer> map, ModelAndView mav){
	    //email, authKey �� ��ġ�Ұ�� authStatus ������Ʈ
		System.out.println("����� email :" + map.get("email"));
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

//	@RequestMapping("/memberinfo.me")
//	public String selectMember(MemberVO memberVO, Model model) throws Exception {
//		MemberVO vo = memberService.selectMember(memberVO);
//		model.addAttribute("memberVO", vo);
//
//		return "member/member_info";
//	}

	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO, Model model) throws Exception {
		memberService.deleteMember(memberVO);

		return "redirect:/memberlist.me";
	}
}