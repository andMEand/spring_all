package com.spring.springfileupload1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping("fileUploadForm.bo")
	public String fileUploadForm( ) {
		
		
		return "fileUploadForm";
	}
	
	//커맨드 객체 (VO,DTO) 를 통ㅇ한 업로드 파일 접근
	//커맨드 클래스에 파라미터와 동일한 이름의 MultipartFile타입 프로퍼티를 추가해주기만 하면 
	//업로드 파일정보를 커맨드객체를 통해 전달 받을 수 있게 된다
	@RequestMapping("/fileUpload1.bo")
	public ModelAndView fileUpload1 (HttpServletRequest request, RequestModel model) throws Exception {
		String name = request.getParameter("name");
		String uploadPath ="C:\\Project\\upload\\";//직접 업로드될 위치 지정
		
		//뷰지정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");
		
		ArrayList<String> orgfile_list = new ArrayList<String>();//오리지널 파일이음
		ArrayList<String> storedfile_list =new ArrayList<String>();//실제저장될때 사용될 파일 이름
		ArrayList<Long> filesize_list = new ArrayList<Long>();
		for(MultipartFile mf : model.getFile()) {  //getFile 첨부한 파일을 리스트로 받아온다
			String originFileName = mf.getOriginalFilename();//원본 파일 명
			long fileSize =mf.getSize();//파일용량 사이즈
			
			//정보확인하는 작업
			System.out.println("originFileName :"+ originFileName);
			System.out.println("filesize :"+ fileSize);
			String storedFileName = System.currentTimeMillis() + originFileName; //새로운 파일이 생성하고 서버에 올릴때 동일한 중복된 파일이 없게된다
			System.out.println("storedFileName=" +storedFileName); //새롭게 바뀐 이름읋 확인 할 수 있다
			String safeFile = uploadPath + storedFileName; //저장할 위치(경로)와 파일명
			try {
				if(mf.getSize() != 0) {
					mf.transferTo(new File(safeFile));//transferTo() 실제 파일 업로드에 사용되는 메소드
					
				}
			}catch(IOException e) {
				System.out.println("업로드 에러!!!");
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		
		//뷰에 출력한 데이터 모델에 저장
		mav.addObject("name",  name);
		mav.addObject("orgfile_list", orgfile_list);
		mav.addObject("storedfile_list", storedfile_list);
		mav.addObject("filesize_list",filesize_list );
		mav.addObject("uploadPath",uploadPath);//업로드한 파일정보를 화면에 뿌려줄때, 경로가 필요하기 떄문이다.
		return mav; //다운로드 뷰로 이동
		
	}
	//MultipartHttpServletRequest 를 이용한 업로드 파일 접근
	@RequestMapping("/fileUpload2.bo")
	public ModelAndView fileUpload2(MultipartHttpServletRequest request) throws Exception{  //MultipartHttpServletRequest통해 파일처리
		
		String name = request.getParameter("name");
		List<MultipartFile> fileList = request.getFiles("file");	
		//                            request.getFiles("file");	 :파일 리스트를 읽어온다
		
		String uploadPath ="C:\\Project\\upload\\";//직접 업로드 위치 지정 /외부경로는 직접지정
		
		//뷰 지정
		ModelAndView mav= new ModelAndView();
		mav.setViewName("download"); //이동할 문서
		
		
		ArrayList<String> orgfile_list = new ArrayList<String>(); //오리지널 파일이음
		ArrayList<String> storedfile_list =new ArrayList<String>();//실제저장될때 사용될 파일 이름
		
		ArrayList<Long> filesize_list = new ArrayList<Long>();
		for(MultipartFile mf: fileList) {  //첨부된 파일 갯수만큼 루프
			String originFileName = mf.getOriginalFilename();//원본 파일
			long fileSize = mf.getSize();
			
			System.out.println("originFileName :"+ originFileName);
			System.out.println("filesize :"+ fileSize);
			String storedFileName = System.currentTimeMillis()+originFileName;
			System.out.println("storedFileNam :" +storedFileName);
			String safeFile = uploadPath + storedFileName;
			try {
				if(mf.getSize() != 0) {
					mf.transferTo(new File(safeFile));
					
				}
			}catch(IOException e) {
				System.out.println("업로드 에러!!!" + e.getMessage());
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		
		//뷰에 출력한 데이터 모델에 저장
				mav.addObject("name",  name);
				mav.addObject("orgfile_list", orgfile_list);
				mav.addObject("storedfile_list", storedfile_list);
				mav.addObject("filesize_list",filesize_list );
				mav.addObject("uploadPath",uploadPath);
				return mav;
	}
	//파일 다운로드 방식
	@RequestMapping("/fileDownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String of = request.getParameter("of"); //서버에 업로드된 변경된 살제 파일
		String of2 = request.getParameter("of2");//오리지날 파일명

	/*//웹사이트 루트디렉토리의 실제 디스크상의 경로 알아내기.
	 String uploadPath = request.getSession().getServletContext().getRea
	 String fullPath = uploadPath +"/"+of;
	  
	*/
		String uploadPath ="C:\\Project\\upload\\";
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		
		//파일 다운로드를 위해 컨텐츠 타입을 application/download설정
		response.setContentType("application/download; charset=utf-8");
		//파일 사이즈 지정
		response.setContentLength((int)downloadFile.length());
		//다운로드 창을 띄우기 위한 헤더 조작
		response.setHeader("Content-Disposition", "attachment; filename="+new String(of2.getBytes(),"ISO8859_1"));
		response.setHeader("Content-Trasfer-Encoding", "binary");
		/*
		 * Content-disposition 속석
		 * 1)"Content-disposition:attachment" 브라우저 인식 팡리확장자를 포함하여 모든 확장자 
		 * 파일들에 대해, 다운로드시 무조건" 파일 다운로드" 대화상자가 뜨도록 하는 헤더속성이다
		 * 2) Content-disposition:inline" 브라우저 인식 파일확장자를 거진 파일들에 대해서는 
		 * 웹브라우저 상에서 바로 파일을 열고, 그 외의 파일들에 대해서는 "파일 다운로드"대화상자가 뜨도록 하는 헤더 속성이다
		 */
		FileInputStream fin = new FileInputStream(downloadFile);	
		ServletOutputStream sout = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int size = -1;
		
		while((size = fin.read(buf,0,buf.length)) != -1) {
			sout.write(buf,0,size);
		}
		fin.close();
		sout.close();
		
		
	}
	
}

