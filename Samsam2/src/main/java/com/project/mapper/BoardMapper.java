	package com.project.mapper;

import java.util.List;

import com.project.samsam.board.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> getSearch_commu_List(String keyword);
	public List<BoardVO> getSearch_adopt_List(String keyword);
	public List<BoardVO> getSearch_free_List(String keyword);
	
	public BoardVO getSDetail (int num);
	
}
