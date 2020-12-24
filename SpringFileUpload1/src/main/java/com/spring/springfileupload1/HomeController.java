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
	
	//Ŀ�ǵ� ��ü (VO,DTO) �� �뤷�� ���ε� ���� ����
	//Ŀ�ǵ� Ŭ������ �Ķ���Ϳ� ������ �̸��� MultipartFileŸ�� ������Ƽ�� �߰����ֱ⸸ �ϸ� 
	//���ε� ���������� Ŀ�ǵ尴ü�� ���� ���� ���� �� �ְ� �ȴ�
	@RequestMapping("/fileUpload1.bo")
	public ModelAndView fileUpload1 (HttpServletRequest request, RequestModel model) throws Exception {
		String name = request.getParameter("name");
		String uploadPath ="C:\\Project\\upload\\";//���� ���ε�� ��ġ ����
		
		//������
		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");
		
		ArrayList<String> orgfile_list = new ArrayList<String>();//�������� ��������
		ArrayList<String> storedfile_list =new ArrayList<String>();//��������ɶ� ���� ���� �̸�
		ArrayList<Long> filesize_list = new ArrayList<Long>();
		for(MultipartFile mf : model.getFile()) {  //getFile ÷���� ������ ����Ʈ�� �޾ƿ´�
			String originFileName = mf.getOriginalFilename();//���� ���� ��
			long fileSize =mf.getSize();//���Ͽ뷮 ������
			
			//����Ȯ���ϴ� �۾�
			System.out.println("originFileName :"+ originFileName);
			System.out.println("filesize :"+ fileSize);
			String storedFileName = System.currentTimeMillis() + originFileName; //���ο� ������ �����ϰ� ������ �ø��� ������ �ߺ��� ������ ���Եȴ�
			System.out.println("storedFileName=" +storedFileName); //���Ӱ� �ٲ� �̸��� Ȯ�� �� �� �ִ�
			String safeFile = uploadPath + storedFileName; //������ ��ġ(���)�� ���ϸ�
			try {
				if(mf.getSize() != 0) {
					mf.transferTo(new File(safeFile));//transferTo() ���� ���� ���ε忡 ���Ǵ� �޼ҵ�
					
				}
			}catch(IOException e) {
				System.out.println("���ε� ����!!!");
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		
		//�信 ����� ������ �𵨿� ����
		mav.addObject("name",  name);
		mav.addObject("orgfile_list", orgfile_list);
		mav.addObject("storedfile_list", storedfile_list);
		mav.addObject("filesize_list",filesize_list );
		mav.addObject("uploadPath",uploadPath);//���ε��� ���������� ȭ�鿡 �ѷ��ٶ�, ��ΰ� �ʿ��ϱ� �����̴�.
		return mav; //�ٿ�ε� ��� �̵�
		
	}
	//MultipartHttpServletRequest �� �̿��� ���ε� ���� ����
	@RequestMapping("/fileUpload2.bo")
	public ModelAndView fileUpload2(MultipartHttpServletRequest request) throws Exception{  //MultipartHttpServletRequest���� ����ó��
		
		String name = request.getParameter("name");
		List<MultipartFile> fileList = request.getFiles("file");	
		//                            request.getFiles("file");	 :���� ����Ʈ�� �о�´�
		
		String uploadPath ="C:\\Project\\upload\\";//���� ���ε� ��ġ ���� /�ܺΰ�δ� ��������
		
		//�� ����
		ModelAndView mav= new ModelAndView();
		mav.setViewName("download"); //�̵��� ����
		
		
		ArrayList<String> orgfile_list = new ArrayList<String>(); //�������� ��������
		ArrayList<String> storedfile_list =new ArrayList<String>();//��������ɶ� ���� ���� �̸�
		
		ArrayList<Long> filesize_list = new ArrayList<Long>();
		for(MultipartFile mf: fileList) {  //÷�ε� ���� ������ŭ ����
			String originFileName = mf.getOriginalFilename();//���� ����
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
				System.out.println("���ε� ����!!!" + e.getMessage());
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		
		//�信 ����� ������ �𵨿� ����
				mav.addObject("name",  name);
				mav.addObject("orgfile_list", orgfile_list);
				mav.addObject("storedfile_list", storedfile_list);
				mav.addObject("filesize_list",filesize_list );
				mav.addObject("uploadPath",uploadPath);
				return mav;
	}
	//���� �ٿ�ε� ���
	@RequestMapping("/fileDownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String of = request.getParameter("of"); //������ ���ε�� ����� ���� ����
		String of2 = request.getParameter("of2");//�������� ���ϸ�

	/*//������Ʈ ��Ʈ���丮�� ���� ��ũ���� ��� �˾Ƴ���.
	 String uploadPath = request.getSession().getServletContext().getRea
	 String fullPath = uploadPath +"/"+of;
	  
	*/
		String uploadPath ="C:\\Project\\upload\\";
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		
		//���� �ٿ�ε带 ���� ������ Ÿ���� application/download����
		response.setContentType("application/download; charset=utf-8");
		//���� ������ ����
		response.setContentLength((int)downloadFile.length());
		//�ٿ�ε� â�� ���� ���� ��� ����
		response.setHeader("Content-Disposition", "attachment; filename="+new String(of2.getBytes(),"ISO8859_1"));
		response.setHeader("Content-Trasfer-Encoding", "binary");
		/*
		 * Content-disposition �Ӽ�
		 * 1)"Content-disposition:attachment" ������ �ν� �θ�Ȯ���ڸ� �����Ͽ� ��� Ȯ���� 
		 * ���ϵ鿡 ����, �ٿ�ε�� ������" ���� �ٿ�ε�" ��ȭ���ڰ� �ߵ��� �ϴ� ����Ӽ��̴�
		 * 2) Content-disposition:inline" ������ �ν� ����Ȯ���ڸ� ���� ���ϵ鿡 ���ؼ��� 
		 * �������� �󿡼� �ٷ� ������ ����, �� ���� ���ϵ鿡 ���ؼ��� "���� �ٿ�ε�"��ȭ���ڰ� �ߵ��� �ϴ� ��� �Ӽ��̴�
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

