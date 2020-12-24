package com.spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.MemberMapper;
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired //Mybatis(ibatis) 라이브러리가 제공하는 클래스
	private SqlSession sqlSession;
	
	
	
	@Override
	public ArrayList<MemberVO> getMembers() {
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		//getMembers()의 메소드명과 mapper.xml의 id는 동일해야한다.
		memberList = memberMapper.getMembers();
		//memberList = memberMapper.getMembers(tab_mybatis);
		
		return memberList;
	}
	

	@Override
	public MemberVO getMember(String id) {
		MemberVO member = new MemberVO();
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		//member = memberMapper.getMember(id);
		member = memberMapper.getMember(id);
		System.out.println("member.id = " + member.getId());
		
		return member;
	}

	@Override
	public void insertMember(MemberVO member) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember(member);
		
	}

	@Override
	public void insertMember2(HashMap<String, String> map) {
		System.out.println("hashmap");
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember2(map);
	}

	@Override
	public void updateMember(MemberVO member) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.updateMember(member);

	}

	@Override
	public void deleteMember(String id) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.deleteMember(id);
	}

}
