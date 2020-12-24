package com.spring.jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	 
	   

//	  @RequestMapping(value = "/home.do", method = RequestMethod.GET)
//	  public String home() {
//	  
//	    return "home";
//	  }
	  @RequestMapping(value = "/crawl.do", method = RequestMethod.GET)
	  public ModelAndView crawl(ModelAndView model) {
	    //Jsoup : https://jsoup.org/
	    //Jsoup�� �̿��ؼ� ���̹� ������ ũ�Ѹ�
	    String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=cnt";
	    Document doc = null;
	    
	    try {
	      doc = Jsoup.connect(url).get();
	    }catch(IOException e) {
	      e.printStackTrace();
	    }
	    //�ֿ� ������ ������ �±׸� ã�Ƽ� ���������� �Ѵ�
	    Elements element = doc.select("table.list_ranking");
	    System.out.println("####################### div.list_ranking #######################");
	    System.out.println(element);
	    
	    ArrayList<String> list_text = new ArrayList<String>();  //��� ����
	    ArrayList<String> list_link = new ArrayList<String>();		//��� ��ũ
	    
	    //2.���� ���� ������ for�� ���鼭 ���
	    for(Element el : element.select("a")) {
	      String text = el.text().toString();
	      String link ="https://movie.naver.com"+ el.select("a").attr("href");
	      System.out.println(text);
	      System.out.println(link);
	      System.out.println("-------------------------------------------------------------------------------------------------------");
	      list_text.add(text);
	      list_link.add(link);
	  
	    }
	    model.addObject("list_text", list_text);
	    model.addObject("list_link", list_link);
	    model.setViewName("crawl");
	    
	    return model;
	  }
	  
	  @RequestMapping(value = "/crawl1.do", method = RequestMethod.GET)
	  public ModelAndView crawl1(ModelAndView model) {
	    //Jsoup : https://jsoup.org/
	    //Jsoup�� �̿��ؼ� ���̹� ������ ũ�Ѹ�
	    String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=cur";
	    Document doc = null;
	    
	    try {
	      doc = Jsoup.connect(url).get();
	    }catch(IOException e) {
	      e.printStackTrace();
	    }
	    //�ֿ� ������ ������ �±׸� ã�Ƽ� ���������� �Ѵ�
	    Elements element = doc.select("table.list_ranking");
	    System.out.println("####################### div.list_ranking #######################");
	    System.out.println(element);
	    
	    ArrayList<String> list_text = new ArrayList<String>();  //��� ����
	    ArrayList<String> list_link = new ArrayList<String>();		//��� ��ũ
	    ArrayList<String> list_pj = new ArrayList<String>();		//��� ��ũ
	    
	    //2.���� ���� ������ for�� ���鼭 ���
	    for(Element el : element.select("a")) {
	      String text = el.text().toString();
	      String link ="https://movie.naver.com"+ el.select("a").attr("href");
	      System.out.println(text);
	      System.out.println(link);
	      System.out.println("-------------------------------------------------------------------------------------------------------");
	      list_text.add(text);
	      list_link.add(link);
	  
	    }
	    for(Element ej :element.select("td.point")) {
	    	String pj = ej.text().toString();
	    	System.out.println(pj);
	    	list_pj.add(pj);

	    }
	    model.addObject("list_text", list_text);
	    model.addObject("list_link", list_link);
	    model.addObject("list_pj", list_pj);
	    model.setViewName("crawl1");
	    
	    return model;
	  }
	  
	  @RequestMapping(value = "/crawl2.do", method = RequestMethod.GET)
	  public ModelAndView crawl2(ModelAndView model) {
	    //Jsoup : https://jsoup.org/
	    //Jsoup�� �̿��ؼ� ���̹� ������ ũ�Ѹ�
	    String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt";
	    Document doc = null;
	    
	    try {
	      doc = Jsoup.connect(url).get();
	    }catch(IOException e) {
	      e.printStackTrace();
	    }
	    //�ֿ� ������ ������ �±׸� ã�Ƽ� ���������� �Ѵ�
	    Elements element = doc.select("table.list_ranking");
	    System.out.println("####################### div.list_ranking #######################");
	    System.out.println(element);
	    
	    ArrayList<String> list_text = new ArrayList<String>();  //��� ����
	    ArrayList<String> list_link = new ArrayList<String>();		//��� ��ũ
	    ArrayList<String> list_pj = new ArrayList<String>();		//��� ��ũ
	    
	    //2.���� ���� ������ for�� ���鼭 ���
	    for(Element el : element.select("a")) {
	      String text = el.text().toString();
	      String link ="https://movie.naver.com"+ el.select("a").attr("href");
	      System.out.println(text);
	      System.out.println(link);
	      System.out.println("-------------------------------------------------------------------------------------------------------");
	      list_text.add(text);
	      list_link.add(link);
	  
	    }
	    for(Element ej :element.select("td.point")) {
	    	String pj = ej.text().toString();
	    	System.out.println(pj);
	    	list_pj.add(pj);

	    }
	    model.addObject("list_text", list_text);
	    model.addObject("list_link", list_link);
	    model.addObject("list_pj", list_pj);
	    model.setViewName("crawl2");
	    
	    return model;
	  }
	  
	
	  
	}
