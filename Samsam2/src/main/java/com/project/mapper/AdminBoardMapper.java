package com.project.mapper;

import java.util.List;

import com.project.samsam.board.ABoardVO;
import com.project.samsam.board.ABoardVOto;
import com.project.samsam.board.ADModalVO;
import com.project.samsam.board.CommentVO;
import com.project.samsam.board.WarningVO;
import com.project.samsam.member.MemberVO;

public interface AdminBoardMapper {
		
	public List<ABoardVOto> findList(ABoardVO abvo);
	public List<ABoardVOto> find_w_List(ABoardVO abvo);
	//어드민 게시글 검색
	
	public MemberVO ad_view_m (	ADModalVO mvo);
    public ABoardVOto ad_view_b (ADModalVO mvo);
    public List<CommentVO> ad_view_cList (ADModalVO mvo);
    public CommentVO ad_view_ccount (ADModalVO mvo);
    public List<WarningVO> ad_view_wList (ADModalVO mvo);
    public WarningVO ad_view_wcount (ADModalVO mvo);
	  //어드민 게시글 뷰 Modal
	

}

