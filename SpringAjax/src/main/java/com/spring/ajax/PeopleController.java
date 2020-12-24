package com.spring.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PeopleController {
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String home() {
	return "home";
	}
	
	//produces 속성을 이용해 Response의 Context-Type을 제어할 수 있다
	@RequestMapping(value="/getPeopleJson.do", 
			method=RequestMethod.POST ,
			produces="application/json;charset=utf-8")
	@ResponseBody //jsp와 같은 뷰를 저달하는게 아닌 데이터를 전달하기 위해 사용
	public List<PeopleVO> getPeopleJSONGET(){
		List<PeopleVO> list = peopleService.getPeoplejson();
		
	return list;
	}
	//기존에는 뷰 전달 / @ResponseBody붙으면 리턴타입은 뷰가아닌 데이터(값이된다/ 현재 뷰안에서 처리한 데이터를 쀼려줌 /필요한건 데이터
	
	@RequestMapping(value="/insertPeople.do", 
			method=RequestMethod.POST, 
			produces="application/json;charset=utf-8")  //에이젝스와 타입동일해야 한다
	@ResponseBody 
	public Map<String, Object>  insertPeople(PeopleVO vo){  //삽입
		Map<String, Object>  retVal = new HashMap<String, Object>();//리턴값지정 //Object : 다양한 타입을 줄 수 있어서 사용
		try {
			peopleService.insertPeople(vo);//확인여부를 처리
			retVal.put("res", "OK");
		}
		catch(Exception e) {
			System.out.println("에러 : "+ e.getMessage());
			retVal.put("res", "FAIL");  
			retVal.put("message", "Failure");
		}
		
	return retVal;
	}
	
	@RequestMapping(value="/deletePeople.do",
			produces="application/json;charset=utf-8")
	@ResponseBody 
	public Map<String, Object>  deletePeople(@RequestParam(value="id")String id){//@RequestParam(value="id")String id)파라메터 값이 id인것을 받아와서 저장
		Map<String, Object>  retVal = new HashMap<String, Object>();//리턴값 
		try {
			peopleService.deletePeople(id);
			retVal.put("res", "OK");
		}
		catch(Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		
	return retVal;
	}
	
	
}
