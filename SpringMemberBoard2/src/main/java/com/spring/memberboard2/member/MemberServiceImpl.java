package com.spring.memberboard2.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired(required=false) //의존성 주입을 못하게 되더라도 에러발생하지 않는다
	private MemberDAO memberDAO=null;  //memberDAO를 사용하기 위해 빈으로 만들어줘야 하고 DAO에 레퍼지토리.
	
	@Override
	public int insertMember(MemberVO memberVO) {
		int res  = memberDAO.insertMember(memberVO); 
		
		return res;
	}

	@Override
	public int userCheck(MemberVO memberVO) {
		int res  = memberDAO.userCheck(memberVO); 
		
		return res;
	}

	@Override
	public ArrayList<MemberVO> getMemberlist() {
		ArrayList<MemberVO> member_list = new ArrayList<MemberVO>();
		member_list = memberDAO.getMemberlist();
	
		return member_list;
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		MemberVO vo  = memberDAO.selectMember(memberVO);
			
		return vo;
	}

	@Override
	public int deleteMember(MemberVO memberVO) {
		int res = memberDAO.deleteMember(memberVO); 
		
		return res;
	}

}
