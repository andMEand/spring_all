package com.project.samsam.board;

import java.util.List;

import com.project.samsam.member.MemberVO;

public interface BoardService {
	  
	  public List<BoardVO> getSearchList(String keyword);
	  public BoardVO getSDetail(int num);
	  //Ȩ������ ���� �˻� �Լ�
	  
      public List<ABoardVOto> findList(ABoardVO abvo);
      public List<ABoardVOto> find_w_List(ABoardVO abvo);
      //���� �Խñ۰��� �˻� �Լ�
      
      public MemberVO adModalView_m (ADModalVO membervo);
      public ABoardVOto adModalView_b (ADModalVO membervo);
      
      public List<CommentVO> adModalView_c (ADModalVO membervo);
      public CommentVO adModalView_ccount (ADModalVO mvo);
      
      public List<WarningVO> adModalView_w (ADModalVO membervo);
      public WarningVO adModalView_wcount (ADModalVO membervo);
	  //���� �Խñ� �� Modal
      
	  
}
