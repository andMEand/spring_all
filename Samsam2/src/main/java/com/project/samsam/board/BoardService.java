package com.project.samsam.board;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
	  
	  public List<BoardVO> getSearchList(String keyword);
	  public BoardVO getSDetail(int num);
	  //Ȩ������ ���� �˻� �Լ�
	  
      public List<ABoardVOto> findList(ABoardVO abvo);
      public List<ABoardVOto> find_w_List(ABoardVO abvo);
      //���� �Խñ۰��� �˻� �Լ�
	  
	  
}
