package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;

import com.project.samsam.member.MemberVO;

public interface BoardService {
	  
	  public List<BoardVO> getSearch_commu_List(String keyword);
	  public List<BoardVO> getSearch_adopt_List(String keyword);
	  public List<BoardVO> getSearch_free_List(String keyword);
	  
	  
	  public BoardVO getSDetail(int num);
	  //홈페이지 메인 검색 함수
	  
      public List<ABoardVOto> findList(ABoardVO abvo);
      public List<ABoardVOto> find_w_List(ABoardVO abvo);
      //어드민 게시글관리 검색 함수
      
      
      
      public MemberVO adModalView_m (ADModalVO membervo);
      public ABoardVOto adModalView_b (ADModalVO membervo);
      
      public List<CommentVO> adModalView_c (ADModalVO membervo);
      public CommentVO adModalView_ccount (ADModalVO mvo);
      
      public List<WarningVO> adModalView_w (ADModalVO membervo);
      public WarningVO adModalView_wcount (ADModalVO membervo);
	  //어드민 게시글 뷰 Modal
      
	  
}
