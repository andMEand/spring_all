package com.project.samsam.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bhdoc_no;
	private String bhdoc_nick;
	private String bhdoc_subject;
	private String bhdoc_content;
	private Date bhdoc_date;
	public int getBhdoc_no() {
		return bhdoc_no;
	}
	public void setBhdoc_no(int bhdoc_no) {
		this.bhdoc_no = bhdoc_no;
	}
	public String getBhdoc_nick() {
		return bhdoc_nick;
	}
	public void setBhdoc_nick(String bhdoc_nick) {
		this.bhdoc_nick = bhdoc_nick;
	}
	public String getBhdoc_subject() {
		return bhdoc_subject;
	}
	public void setBhdoc_subject(String bhdoc_subject) {
		this.bhdoc_subject = bhdoc_subject;
	}
	public String getBhdoc_content() {
		return bhdoc_content;
	}
	public void setBhdoc_content(String bhdoc_content) {
		this.bhdoc_content = bhdoc_content;
	}
	public Date getBhdoc_date() {
		return bhdoc_date;
	}
	public void setBhdoc_date(Date bhdoc_date) {
		this.bhdoc_date = bhdoc_date;
	}
	
}
