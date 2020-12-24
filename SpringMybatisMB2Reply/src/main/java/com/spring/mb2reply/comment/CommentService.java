package com.spring.mb2reply.comment;

import java.util.List;


public interface CommentService {
	public int commentCount() throws Exception; //´ñ±Û °³¼ö
	public List<CommentVO> commentListService(int bno) throws Exception; //´ñ±Û ¸ñ·Ï
	public int commentInsertService(CommentVO comment) throws Exception; //´ñ±Û Ãß°¡
	public int commentUpdateService(CommentVO comment) throws Exception; //´ñ±Û ¼öÁ¤
	public int commentDeleteService(int cno) throws Exception; //´ñ±Û »èÁ¦

}
