package com.project.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.samsam.board.BoardVO;
import com.project.samsam.board.BoardVO2;

public interface BoardMapper {
	
	public int getListCount();
	public List<BoardVO> getBoardList(HashMap<String, Integer> hashmap);
	public BoardVO getDetail (int num);
	public int boardInsert(BoardVO board);//���� insert�۾�����
	public int boardReplyupdate(BoardVO board); //��ۿ� ����re_seq update�۾�
	public int boardReply(BoardVO board);//��� insert�۾�����
	public int boardModify(BoardVO modifyboard);
	public int boardDelete(int num);
	public void setReadCountUpdate(int num);
	public int isBoardWriter(HashMap<String, String> hashmap);
	
	public List<BoardVO2> getSearchList(String keyword);
	
	
}
