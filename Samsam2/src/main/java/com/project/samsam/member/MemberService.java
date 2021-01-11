package com.project.samsam.member;

import java.util.ArrayList;
import java.util.Map;

public interface MemberService {
	public int userCheck(MemberVO memberVO);
	public MemberVO idCheck(String emailch);
	public ArrayList<MemberVO> getMemberlist();
	public int deleteMember(MemberVO memberVO);
	
	//인서트 멤버
	public int joinMember(MemberVO memberVO);
	//일반 회원가입 이메일 링크 인증
	public void updateAuthkey(Map<String, String> map);
	public void updateAuthStatus (Map<String, Integer> map);
	//īī�� ȸ������
	public int k_joinMember (MemberVO mvo);
	public MemberVO selectMember (String email);
	public Biz_memberVO selectBizMember (String biz_email);
}
