package com.project.samsam.member;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.MemberMapper;
import com.project.samsam.member.MemberVO;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberVO memberVO) {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.insertMember(memberVO);
		return res;
	}

	@Override
	public int userCheck(MemberVO memberVO) {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.userCheck(memberVO);
		return res;
	}

	@Override
	public ArrayList<MemberVO> getMemberlist() {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		ArrayList<MemberVO> member_list =new ArrayList<MemberVO>();
		member_list = memberMapper.getMemberlist();
		return member_list;
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		MemberVO member  = memberMapper.selectMember(memberVO);
			
		return member;
	}

	@Override
	public int deleteMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.deleteMember(memberVO); 
		
		return res;
	}

}
