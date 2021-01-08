package com.project.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.project.samsam.member.Biz_memberVO;
import com.project.samsam.member.MemberVO;

public interface MemberMapper {
	public ArrayList<MemberVO> getMemberlist();
	public int  JinsertMember(MemberVO memberVO);
//	public MemberVO selectMember(MemberVO memberVO);
	public int userCheck (MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	
	public int joinMember(MemberVO memberVO);
	public void updateAuthkey(Map<String,String> map);
	public void updateAuthStatus(Map<String,Integer> map);
	
	public int k_joinMember (MemberVO mvo);
	public MemberVO selectMember (String email);
	public Biz_memberVO selectBizMember (String biz_email);
	
}
