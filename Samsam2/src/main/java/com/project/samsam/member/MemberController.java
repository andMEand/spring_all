package com.project.samsam.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class MemberController {
	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private MailSendService mss;
	@Autowired
	private void setNaverLiginBO(NaverLoginBO naverBO) {
		this.naverBO=naverBO;
	}

	
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
	//���̹� �ݹ�
		@RequestMapping(value = "/callback.me")
		public String nid_callback(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
			return "member/callBack";
		}
	//���̹�
	@RequestMapping(value = "/nidLogin.me")
	public String nid_Join(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
		System.out.println("���̹��̸���: " + mvo.getEmail() + "�г��� : " + mvo.getNick());
		
		if(memberService.selectMember(mvo.getEmail()) == null) {
			mvo.setGrade("���̹�");
			model.addAttribute("MemberVO", mvo);
			return "member/k_joinForm";
		}
		else {
			redi_attr.addAttribute("email", mvo.getEmail());
			return "redirect:/login.me";
			}
	}
	
	//�ҼȰ��� ȸ������
	@RequestMapping(value = "/kkoJoin.me")
	public String kko_joinProcess(MemberVO mvo) {
		if(mvo.getGrade().equals("īī��")) {
			System.out.println("īī��ȸ������" + mvo.getGrade());
		}else if(mvo.getGrade().equals("���̹�")) {
			System.out.println("���̹�ȸ������" + mvo.getGrade());
		}
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
		
		//����
		if(vo.getEmail().equals("admin")) {
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("email", vo.getEmail());
			
			return "redirect:/home.me";  //���� �������� ���� �ʿ�
		}
		//īī��
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
		//���̹�
		if(res.getGrade().equals("���̹�")) {
			session.setAttribute("email", res.getEmail());
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do";//����� ������������ ���� �ʿ�
				}
			}
			return "redirect:/home.me";//������������ ���� �ʿ�
		}
		//�Ϲ�
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
			return "redirect:/loginform.me";
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
		int res= memberService.joinMember(memberVO);
		System.out.println("�μ�Ʈ�Ϸ�"+res);
		//������ authKey���� & �̸��� �߼�
		String authkey = mss.sendAuthMail(memberVO.getEmail());
		memberVO.setAuthkey(authkey);
		System.out.println(3);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", memberVO.getEmail());
		map.put("authkey", memberVO.getAuthkey());
		System.out.println("��" + map.get("email") + "����Ű " + map.get("authkey"));
		
		//DB�� authKey������Ʈ
		memberService.updateAuthkey(map);
		return "member/email_check";
		
	}
	
	 @GetMapping("/signUpConfirm.me")
	 public ModelAndView signUpConfirm(@RequestParam HashMap<String, Integer> map, ModelAndView mav){
	    //email, authKey �� ��ġ�Ұ�� authStatus ������Ʈ
		System.out.println("����� email :" + map.get("email"));
	    memberService.updateAuthStatus(map);
	    System.out.println("����� email2 :" + map.get("email"));
	    mav.addObject("display", "/member/loginForm.jsp");
	    mav.setViewName("/member/loginForm");
	    return mav;
	}
///////////////////////////////////
//���̹� �α��� ��Ʈ�ѷ� ����
	 private NaverLoginBO naverBO;
	 private String apiResult =null;
	 
	   //�α��� ù ȭ�� ��û �޼ҵ�
	    @RequestMapping(value = "/naver", method = { RequestMethod.GET, RequestMethod.POST })
	    public String naver_login(Model model, HttpSession session) {
	        
	        /* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
	        String naverAuthUrl = naverBO.getAuthorizationUrl(session);
	        
	        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
	        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
	        System.out.println("���̹�:" + naverAuthUrl);
	        
	        //���̹� 
	        model.addAttribute("url", naverAuthUrl);
	 
	        /* ������ ���� URL�� View�� ���� */
	        return "login";
	    }
	 
	  //���̹� �α��� ������ callbackȣ�� �޼ҵ�
		@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
		public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
			
			System.out.println("����� callback");
			OAuth2AccessToken oauthToken;
	        oauthToken = naverBO.getAccessToken(session, code, state);
	 
	        //1. �α��� ����� ������ �о�´�.
			apiResult = naverBO.getUserProfile(oauthToken);  //String������ json������
			
			/** apiResult json ����
			{"resultcode":"00",
			 "message":"success",
			 "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
			**/
			
			//2. String������ apiResult�� json���·� �ٲ�
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(apiResult);
			JSONObject jsonObj = (JSONObject) obj;
			
			//3. ������ �Ľ� 
			//Top���� �ܰ� _response �Ľ�
			JSONObject response_obj = (JSONObject)jsonObj.get("response");
			//response�� nickname�� �Ľ�
			String nickname = (String)response_obj.get("nickname");
	 
			System.out.println(nickname);
			
			//4.�Ľ� �г��� �������� ����
			session.setAttribute("sessionId",nickname); //���� ����
			
			model.addAttribute("result", apiResult);
		     
			return "login";
		}
		
		//�α׾ƿ�
		@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
		public String logout(HttpSession session)throws IOException {
				System.out.println("����� logout");
				session.invalidate();
	 
		        
				return "redirect:/home.me";
			}
	
}