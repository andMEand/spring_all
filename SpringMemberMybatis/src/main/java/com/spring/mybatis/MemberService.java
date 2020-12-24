package com.spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberService {
	public ArrayList<MemberVO> getMembers();
	public MemberVO getMember(String id);
	public void insertMember(MemberVO member);
	public void insertMember2(HashMap<String,String> map);
	public void updateMember(MemberVO member);
	public void deleteMember(String id);

	// 컨트롤러 와 dao 분리 / 그 사이 연결을 서비스로 한다
}