package com.spring.springemail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	JavaMailSender mailSender; //메일 서비스르르 사용하기위해 의존성을 주입함
	
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public String home() {
		return "home";
	}
		
	//mailSending 코드
	@RequestMapping(value="/auth.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView mailSending(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Random r = new Random();
		int num = r.nextInt(999999); //이메일로 받는 인증코드 부분(난수)
		
		//String setform ="지메일 이메일 주소";//Gmail 사용시
		//String setform ="다음 이메일 주소"; //다음 사용시
		String setfrom ="081749@naver.com";//naver사용시(보내는 사람 이메일 주소)
		String tomail= request.getParameter("email");//받는 사람 이메일
		String title ="회원가입 인증 이메일 입니다.";//제목
		String content = System.getProperty("line.separator")
				+ "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
				+ System.getProperty("line.separator")
				+"인증번호는 "+ num +"입니다."
				+"받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다";//내용
		
		try {
			MimeMessage message =mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);//보내는 사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail);//받는사람 이메일
			messageHelper.setSubject(title);//메일 제목은 생락이 가능하다
			messageHelper.setText(content);//메일 내용
			mailSender.send(message); //루트의bean id의 메일센더를 주입받는다
		}
		catch(Exception e) {
			System.out.println("에러1");
			System.out.println(e);
		}
		
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("email_injeung");
		mv.addObject("num", num);  //전달하는 이유 :비교하기 위해서
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
				writer.flush();
				
				return mv;
		
		
	}
	
	//이메일로 받은 인증버호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드
	//내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
	//틀리면 다시 원래 페이지로 돌아오는 메소드
	
	@RequestMapping(value="/join_injeung.do",method = RequestMethod.POST)
	public ModelAndView join_injeung(@RequestParam(value="email_injeung")
	String email_injeung, @RequestParam(value="num")String num,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		if(email_injeung.equals(num)) {
			//인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함
			mv.setViewName("join");
			
			//만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을 한번더 입력할 필요가 없게ㄷ한다
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('인증번호가 일치하였습니다 회원가입창으로 이동합니다.');</script>");
					writer.flush();
					
			return mv;
			
		}
		else {
			ModelAndView mv2 = new ModelAndView();
			mv2.setViewName("email_injeung");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('인증번호가 일치하지 않습니다. 인증번호를 다시 입력해 주세요.');"
					+"history.go(-1);</script>");
					writer.flush();
					return mv2;
			
			
		}
		
	}
	
}
