package com.project.samsam.member;

import java.util.ArrayList;
import java.util.Map;

public interface MemberService {
	public int userCheck(MemberVO memberVO);
	public ArrayList<MemberVO> getMemberlist();
//	public MemberVO selectMember(MemberVO memberVO);
	public int deleteMember(MemberVO memberVO);
	
	//�������� �μ�Ʈ
	public int joinMember(MemberVO memberVO);
	//�Ϲ�ȸ������ ����Ű �Լ�
	public void updateAuthkey(Map<String, String> map);
	public void updateAuthStatus (Map<String, Integer> map);
	//īī�� ȸ������
	public int k_joinMember (MemberVO mvo);
	public MemberVO selectMember (String email);
	public Biz_memberVO selectBizMember (String biz_email);
}
