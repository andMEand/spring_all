package com.spring.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.mybatis.MemberVO;

public interface MemberMapper {

	ArrayList<MemberVO> getMembers();
	//ArrayList<MemberVO> getMembers(String t);
	//MemberVO getMember(String id);
	MemberVO getMember(String id);// HashMap �̿�� �߰�
	//������ ������ ��� ���� ��ȯ�ϱ� ���� ��ȯ���� int�� ������
	int insertMember(MemberVO member);
	void insertMember2(HashMap<String, String> map);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	
	
}