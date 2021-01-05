package com.project.mapper;

import java.util.List;

import com.project.samsam.board.ABoardVO;
import com.project.samsam.board.ABoardVOto;
import com.project.samsam.board.CommentVO;
import com.project.samsam.board.WarningVO;
import com.project.samsam.member.MemberVO;

public interface AdminBoardMapper {
		
	public List<ABoardVOto> findList(ABoardVO abvo);
	public List<ABoardVOto> find_w_List(ABoardVO abvo);
	//¾îµå¹Î °Ô½Ã±Û °Ë»ö
	
	public MemberVO ad_view_m (Object obj);
    public ABoardVOto ad_view_b (Object obj);
    public List<WarningVO> ad_view_w (Object obj);
    public List<CommentVO> ad_view_c (Object obj);
	  //¾îµå¹Î °Ô½Ã±Û ºä Modal
	

}

