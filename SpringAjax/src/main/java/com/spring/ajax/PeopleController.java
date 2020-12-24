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
	
	//produces �Ӽ��� �̿��� Response�� Context-Type�� ������ �� �ִ�
	@RequestMapping(value="/getPeopleJson.do", 
			method=RequestMethod.POST ,
			produces="application/json;charset=utf-8")
	@ResponseBody //jsp�� ���� �並 �����ϴ°� �ƴ� �����͸� �����ϱ� ���� ���
	public List<PeopleVO> getPeopleJSONGET(){
		List<PeopleVO> list = peopleService.getPeoplejson();
		
	return list;
	}
	//�������� �� ���� / @ResponseBody������ ����Ÿ���� �䰡�ƴ� ������(���̵ȴ�/ ���� ��ȿ��� ó���� �����͸� �ط��� /�ʿ��Ѱ� ������
	
	@RequestMapping(value="/insertPeople.do", 
			method=RequestMethod.POST, 
			produces="application/json;charset=utf-8")  //���������� Ÿ�Ե����ؾ� �Ѵ�
	@ResponseBody 
	public Map<String, Object>  insertPeople(PeopleVO vo){  //����
		Map<String, Object>  retVal = new HashMap<String, Object>();//���ϰ����� //Object : �پ��� Ÿ���� �� �� �־ ���
		try {
			peopleService.insertPeople(vo);//Ȯ�ο��θ� ó��
			retVal.put("res", "OK");
		}
		catch(Exception e) {
			System.out.println("���� : "+ e.getMessage());
			retVal.put("res", "FAIL");  
			retVal.put("message", "Failure");
		}
		
	return retVal;
	}
	
	@RequestMapping(value="/deletePeople.do",
			produces="application/json;charset=utf-8")
	@ResponseBody 
	public Map<String, Object>  deletePeople(@RequestParam(value="id")String id){//@RequestParam(value="id")String id)�Ķ���� ���� id�ΰ��� �޾ƿͼ� ����
		Map<String, Object>  retVal = new HashMap<String, Object>();//���ϰ� 
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
