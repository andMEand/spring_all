package com.project.mapper;

import java.util.ArrayList;

import com.project.samsam.member.MemberVO;

public interface MemberMapper {
	public ArrayList<MemberVO> getMemberlist();
	public int  insertMember(MemberVO memberVO);
	public MemberVO selectMember(MemberVO memberVO);
	public int userCheck (MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	
}
