package com.project.samsam.member;

import java.util.ArrayList;
import java.util.Map;

public interface MemberService {
	public int joinMember(MemberVO memberVO);
	public int userCheck(MemberVO memberVO);
	public ArrayList<MemberVO> getMemberlist();
//	public MemberVO selectMember(MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	public void updateAuthKey(Map<String, String> map);
	public void updateAuthStatus (Map<String, Integer> map);
	
	public int k_joinMember (MemberVO mvo);
	public MemberVO selectMember (String email);
	public Biz_memberVO selectBizMember (String biz_email);
}
