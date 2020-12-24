package com.spring.springfileupload1;

import org.springframework.web.multipart.MultipartFile;

public class RequestModel {
	private String name;
	private MultipartFile file[];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile[] getFile() {  //업로드할 파일 리스드가 저장될 곳
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	
}
