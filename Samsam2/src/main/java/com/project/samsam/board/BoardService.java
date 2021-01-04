package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
	  
	  public List<BoardVO> getSearchList(String keyword);
	  public BoardVO getSDetail(int num);
	  //홈페이지 메인 검색 함수
	  
      public List<ABoardVOto> findList(ABoardVO abvo);
      public List<ABoardVOto> find_w_List(ABoardVO abvo);
      //어드민 게시글관리 검색 함수
	  
	  
}
