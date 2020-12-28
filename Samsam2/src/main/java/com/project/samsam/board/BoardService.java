package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;

public interface BoardService {

	  public int getListCount();
	  public List<BoardVO> getBoardList(HashMap<String, Integer>hashmap);
	  public BoardVO getDetail(int num);
	  public int boardInsert(BoardVO board);
	  public int boardReply(BoardVO board);
	  public BoardVO boardModifyForm(int num);
	  public int boardModify(BoardVO modifyboard);
	  public int boardDelete(HashMap<String,String> hashmap);
	  
	  public List<BoardVO> getSearchList(String keyword);
	  public BoardVO getSearchDetaill (int num);
	  
}
