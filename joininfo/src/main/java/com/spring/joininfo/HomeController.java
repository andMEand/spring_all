package com.spring.joininfo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value ="/input_form.bo")
	public String input() {
		return "join_form";
	}
	
	@RequestMapping(value ="/input.bo", method= RequestMethod.POST)
	public ModelAndView res(HttpServletRequest request, ModelAndView mav) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String jumin1 = request.getParameter("jumin1");
		String jumin2 = request.getParameter("jumin2");
		String gender = request.getParameter("gender");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String hobby = request.getParameter("hobby");
		String intro = request.getParameter("intro");
		mav.addObject("id",id);
		mav.addObject("pw",pw);
		mav.addObject("jumin1",jumin1);
		mav.addObject("jumin2",jumin2);
		mav.addObject("gender",gender);
		
		mav.addObject("tel1",tel1);
		mav.addObject("tel2",tel2);
		mav.addObject("tel3",tel3);
		mav.addObject("hobby",hobby);
		mav.addObject("intro",intro);
		mav.setViewName("joinprint");
		
		return mav;
	}
	
}
