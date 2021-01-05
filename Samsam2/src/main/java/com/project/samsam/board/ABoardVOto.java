package com.project.samsam.board;

import java.util.Date;

public class ABoardVOto {
	private int num; 
	private String email;
	private String nick;
	private String subject;
	private String c_content;
	private String c_date;
	private int readcount;
	private String category;
	private int c_count;  //
	//어드민 게시글 리스트로 보내는 VO
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private int w_no;
	private String w_email;
	private int w_co_no;
	private int w_doc_no;
	private String w_date;
	private String w_a_reason;
	private String w_status;
	private String w_reason;
	//어드민 신고 리스트 보내는 VO
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getW_no() {
		return w_no;
	}
	public void setW_no(int w_no) {
		this.w_no = w_no;
	}
	public String getW_email() {
		return w_email;
	}
	public void setW_email(String w_email) {
		this.w_email = w_email;
	}
	public int getW_co_no() {
		return w_co_no;
	}
	public void setW_co_no(int w_co_no) {
		this.w_co_no = w_co_no;
	}
	public int getW_doc_no() {
		return w_doc_no;
	}
	public void setW_doc_no(int w_doc_no) {
		this.w_doc_no = w_doc_no;
	}
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
		this.w_date = w_date;
	}
	public String getW_a_reason() {
		return w_a_reason;
	}
	public void setW_a_reason(String w_a_reason) {
		this.w_a_reason = w_a_reason;
	}
	public String getW_status() {
		return w_status;
	}
	public void setW_status(String w_status) {
		this.w_status = w_status;
	}
	public String getW_reason() {
		return w_reason;
	}
	public void setW_reason(String w_reason) {
		this.w_reason = w_reason;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	
	
	
	
	
}
