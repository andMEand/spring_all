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
      
      public MemberVO adModalView_m (Object obj);
      public ABoardVOto adModalView_b (Object obj);
      public List<WarningVO> adModalView_w (Object obj);
      public List<CommentVO> adModalView_c (Object obj);
	  //���� �Խñ� �� Modal
      
	  
}
