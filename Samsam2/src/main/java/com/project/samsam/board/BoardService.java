package com.project.samsam.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BoardService {

	  public int getListCount();
	  public List<BoardVO> getBoardList(HashMap<String, Integer>hashmap);
	  public int boardInsert(BoardVO board);
	  public int boardReply(BoardVO board);
	  public BoardVO boardModifyForm(int num);
	  public int boardModify(BoardVO modifyboard);
	  public int boardDelete(HashMap<String,String> hashmap);
	  public BoardVO getDetail(int num);
	  
	  public List<BoardVO2> getSearchList(String keyword);
	  public BoardVO2 getSDetail(int num);
	  //홈페이지 메인 검색 함수
	  
//	  public List<BoardVO2> getMyBList(String email);
	  
	  
}
