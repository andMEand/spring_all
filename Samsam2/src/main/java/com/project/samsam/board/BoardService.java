package com.project.samsam.board;

import java.util.List;

import com.project.samsam.member.MemberVO;

public interface BoardService {
	  
	  public List<BoardVO> getSearchList(String keyword);
	  public BoardVO getSDetail(int num);
	  //홈페이지 메인 검색 함수
	  
      public List<ABoardVOto> findList(ABoardVO abvo);
      public List<ABoardVOto> find_w_List(ABoardVO abvo);
      //어드민 게시글관리 검색 함수
      
      public MemberVO adModalView_m (Object obj);
      public ABoardVOto adModalView_b (Object obj);
      public List<WarningVO> adModalView_w (Object obj);
      public List<CommentVO> adModalView_c (Object obj);
	  //어드민 게시글 뷰 Modal
      
	  
}
