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
	
	//카카오로그인
	@RequestMapping(value = "/kkoLogin.me")
	public String kko_Join(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
		System.out.println("이메일: " + mvo.getEmail() + "닉네임 : " + mvo.getNick());
		
		if(memberService.selectMember(mvo.getEmail()) == null) {
			mvo.setGrade("카카오");
			model.addAttribute("MemberVO", mvo);
			return "member/k_joinForm";
		}
		else {
			redi_attr.addAttribute("email", mvo.getEmail());
			return "redirect:/login.me";
			}
	}
	//네이버 콜백
		@RequestMapping(value = "/callback.me")
		public String nid_callback(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
			return "member/callBack";
		}
	//네이버
	@RequestMapping(value = "/nidLogin.me")
	public String nid_Join(MemberVO mvo, Model model, RedirectAttributes redi_attr) {
		System.out.println("네이버이메일: " + mvo.getEmail() + "닉네임 : " + mvo.getNick());
		
		if(memberService.selectMember(mvo.getEmail()) == null) {
			mvo.setGrade("네이버");
			model.addAttribute("MemberVO", mvo);
			return "member/k_joinForm";
		}
		else {
			redi_attr.addAttribute("email", mvo.getEmail());
			return "redirect:/login.me";
			}
	}
	
	//소셜계정 회원가입
	@RequestMapping(value = "/kkoJoin.me")
	public String kko_joinProcess(MemberVO mvo) {
		if(mvo.getGrade().equals("카카오")) {
			System.out.println("카카오회원가입" + mvo.getGrade());
		}else if(mvo.getGrade().equals("네이버")) {
			System.out.println("네이버회원가입" + mvo.getGrade());
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
		System.out.println("로그인 이메일 "+vo.getEmail());
		System.out.println("로그인 비밀번호 "+vo.getPw());
		
		//어드민
		if(vo.getEmail().equals("admin")) {
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("email", vo.getEmail());
			
			return "redirect:/home.me";  //어드민 페이지로 변경 필요
		}
		//카카오
		MemberVO res = memberService.selectMember(vo.getEmail());
		if(res.getGrade().equals("카카오")) {
			session.setAttribute("email", res.getEmail());
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do";//사업자 마이페이지로 변경 필요
				}
			}
			return "redirect:/home.me";//마이페이지로 변경 필요
		}
		//네이버
		if(res.getGrade().equals("네이버")) {
			session.setAttribute("email", res.getEmail());
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do";//사업자 마이페이지로 변경 필요
				}
			}
			return "redirect:/home.me";//마이페이지로 변경 필요
		}
		//일반
		if(res.getPw().equals(vo.getPw())) {
			
			session.setAttribute("id", res.getEmail());
			session.setAttribute("email", res.getEmail());
			System.out.println("session id :" +session.getAttribute("id"));
			System.out.println("session email :" +session.getAttribute("email"));
			//사업자회원인지 확인
			Biz_memberVO bo = memberService.selectBizMember(vo.getEmail());
			if(bo != null) {
				if(bo.getStatus() == 0) {
					return "redirect:/cominfo_main.do"; //사업자 마이페이지로 변경 필요
				}
			}
			return "redirect:/home.me";  //마이페이지로 변경 필요
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
		 // DB에 기본정보 insert
		int res= memberService.joinMember(memberVO);
		System.out.println("인서트완료"+res);
		//임의의 authKey생성 & 이메일 발송
		String authkey = mss.sendAuthMail(memberVO.getEmail());
		memberVO.setAuthkey(authkey);
		System.out.println(3);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", memberVO.getEmail());
		map.put("authkey", memberVO.getAuthkey());
		System.out.println("맵" + map.get("email") + "인증키 " + map.get("authkey"));
		
		//DB에 authKey업데이트
		memberService.updateAuthkey(map);
		return "member/email_check";
		
	}
	
	 @GetMapping("/signUpConfirm.me")
	 public ModelAndView signUpConfirm(@RequestParam HashMap<String, Integer> map, ModelAndView mav){
	    //email, authKey 가 일치할경우 authStatus 업데이트
		System.out.println("연결된 email :" + map.get("email"));
	    memberService.updateAuthStatus(map);
	    System.out.println("연결된 email2 :" + map.get("email"));
	    mav.addObject("display", "/member/loginForm.jsp");
	    mav.setViewName("/member/loginForm");
	    return mav;
	}
///////////////////////////////////
//네이버 로그인 컨트롤러 시작
	 private NaverLoginBO naverBO;
	 private String apiResult =null;
	 
	   //로그인 첫 화면 요청 메소드
	    @RequestMapping(value = "/naver", method = { RequestMethod.GET, RequestMethod.POST })
	    public String naver_login(Model model, HttpSession session) {
	        
	        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
	        String naverAuthUrl = naverBO.getAuthorizationUrl(session);
	        
	        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
	        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
	        System.out.println("네이버:" + naverAuthUrl);
	        
	        //네이버 
	        model.addAttribute("url", naverAuthUrl);
	 
	        /* 생성한 인증 URL을 View로 전달 */
	        return "login";
	    }
	 
	  //네이버 로그인 성공시 callback호출 메소드
		@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
		public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
			
			System.out.println("여기는 callback");
			OAuth2AccessToken oauthToken;
	        oauthToken = naverBO.getAccessToken(session, code, state);
	 
	        //1. 로그인 사용자 정보를 읽어온다.
			apiResult = naverBO.getUserProfile(oauthToken);  //String형식의 json데이터
			
			/** apiResult json 구조
			{"resultcode":"00",
			 "message":"success",
			 "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
			**/
			
			//2. String형식인 apiResult를 json형태로 바꿈
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(apiResult);
			JSONObject jsonObj = (JSONObject) obj;
			
			//3. 데이터 파싱 
			//Top레벨 단계 _response 파싱
			JSONObject response_obj = (JSONObject)jsonObj.get("response");
			//response의 nickname값 파싱
			String nickname = (String)response_obj.get("nickname");
	 
			System.out.println(nickname);
			
			//4.파싱 닉네임 세션으로 저장
			session.setAttribute("sessionId",nickname); //세션 생성
			
			model.addAttribute("result", apiResult);
		     
			return "login";
		}
		
		//로그아웃
		@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
		public String logout(HttpSession session)throws IOException {
				System.out.println("여기는 logout");
				session.invalidate();
	 
		        
				return "redirect:/home.me";
			}
	
}