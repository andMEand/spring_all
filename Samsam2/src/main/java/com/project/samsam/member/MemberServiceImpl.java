package com.project.samsam.member;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.MemberMapper;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public int k_joinMember (MemberVO mvo) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res =memberMapper.k_joinMember(mvo);
		return res;
		//카카오 회원가입
	}
	@Override
	public int joinMember(MemberVO memberVO) {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.joinMember(memberVO);
		return res;
	} //회원가입
	
	@Override
	public MemberVO selectMember (String email) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		MemberVO mvo = memberMapper.selectMember(email);
		return mvo;
	}
	@Override
	public Biz_memberVO selectBizMember (String biz_email) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		Biz_memberVO bmvo =memberMapper.selectBizMember(biz_email);
		return bmvo;
	}
	
	
	//

	@Override
	public int userCheck(MemberVO memberVO) {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.userCheck(memberVO);
		return res;
	}
	@Override
	public MemberVO idCheck(String emailch) {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		MemberVO membercheck = memberMapper.idCheck(emailch);
		return membercheck;

	}

	@Override
	public ArrayList<MemberVO> getMemberlist() {
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		ArrayList<MemberVO> member_list =new ArrayList<MemberVO>();
		member_list = memberMapper.getMemberlist();
		return member_list;
	}

//	@Override
//	public MemberVO selectMember(MemberVO memberVO) {
//		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
//		MemberVO member  = memberMapper.selectMember(memberVO);
//			
//		return member;
//	}

	@Override
	public int deleteMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.deleteMember(memberVO); 
		
		return res;
	}
	
	@Override
	public void updateAuthkey ( Map<String, String> map){
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		memberMapper.updateAuthkey(map);
		
	}
	@Override
	public void updateAuthStatus ( Map<String, Integer> map){
		MemberMapper memberMapper =sqlSession.getMapper(MemberMapper.class);
		memberMapper.updateAuthStatus(map);
	}

}
