package com.project.mapper;

import java.util.List;

import com.project.samsam.board.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> getSearchList(String keyword);
	public BoardVO getSDetail (int num);
	//Ȩ������ �˻� �Լ�
	
}
