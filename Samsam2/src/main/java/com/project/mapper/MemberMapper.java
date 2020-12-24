package com.project.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.project.samsam.member.MemberVO;

public interface MemberMapper {
	public ArrayList<MemberVO> getMemberlist();
	public int  insertMember(MemberVO memberVO);
	public MemberVO selectMember(MemberVO memberVO);
	public int userCheck (MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	public void updateAuthKey(Map<String,String> map);
	public void updateAuthStatus(Map<String,Integer> map);
	
}