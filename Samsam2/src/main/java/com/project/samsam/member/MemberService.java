package com.project.samsam.member;

import java.util.ArrayList;
import java.util.Map;

public interface MemberService {
	public int userCheck(MemberVO memberVO);
	public ArrayList<MemberVO> getMemberlist();
//	public MemberVO selectMember(MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	
	//사입정보 인서트
	public int joinMember(MemberVO memberVO);
	//일반회원가입 인증키 함수
	public void updateAuthkey(Map<String, String> map);
	public void updateAuthStatus (Map<String, Integer> map);
	//카카오 회원가입
	public int k_joinMember (MemberVO mvo);
	public MemberVO selectMember (String email);
	public Biz_memberVO selectBizMember (String biz_email);
}
