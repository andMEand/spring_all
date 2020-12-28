package com.project.samsam.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO2 {
	private int b_no;
	private String b_subject;
	private String b_nick;
	private String b_date;
	
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_subject() {
		return b_subject;
	}
	public void setB_subject(String b_subject) {
		this.b_subject = b_subject;
	}
	public String getB_nick() {
		return b_nick;
	}
	public void setB_nick(String b_nick) {
		this.b_nick = b_nick;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	
	
}
