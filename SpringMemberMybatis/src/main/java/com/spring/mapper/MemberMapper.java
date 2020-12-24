package com.spring.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.mybatis.MemberVO;

public interface MemberMapper {

	ArrayList<MemberVO> getMembers();
	//ArrayList<MemberVO> getMembers(String t);
	//MemberVO getMember(String id);
	MemberVO getMember(String id);// HashMap 이용시 추가
	//삽입후 삽입한 결과 상태 반환하기 위해 반환값을 int로 정해줌
	int insertMember(MemberVO member);
	void insertMember2(HashMap<String, String> map);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	
	
}
