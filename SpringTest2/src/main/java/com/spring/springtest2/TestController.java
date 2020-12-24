package com.spring.springtest2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping(value ="input_form.bo")
	//input_from.bo" 이런 url이 들어오묜 메소드 호출
	public String input() {
		return "input_form";
	}
	
	/*1
	@RequestMapping(value ="input.bo", method= RequestMethod.POST)
	public String res(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "res";
	}
	*/
	
	/*2
	@RequestMapping(value ="input.bo", method= RequestMethod.POST)
	public String res(loginVO vo, Model model) {
		String id = vo.getId();
		String pw = vo.getPw();
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		
		return "res";
	}
	*/
	
	/* 3 */
	@RequestMapping(value ="input.bo", method= RequestMethod.POST)
	public ModelAndView res(HttpServletRequest request, ModelAndView mav) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		mav.addObject("id",id);
		mav.addObject("pw",pw);
		mav.setViewName("res");
		
		return mav;
	}

	
}
