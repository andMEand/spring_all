package com.project.mapper;

import java.util.List;

import com.project.samsam.board.ABoardVO;
import com.project.samsam.board.ABoardVOto;

public interface AdminBoardMapper {
		
	public List<ABoardVOto> findList(ABoardVO abvo);
	public List<ABoardVOto> find_w_List(ABoardVO abvo);
	

}
