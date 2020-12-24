package com.spring.springmember;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")  //@Service 컨트롤러와 dao 연결/ memberService 생성될 객체 이름 명시적기재
public class MemberServiceImpl implements MemberService {
	
	@Autowired(required=false) // 대입할 클 차입을 못찾아도 에러발생하지 않는 장치 //하지만 아래 memberDAO. 를 통해 호출할 수 가 없다.
	private MemberDAO memberDAO= null;

	@Override
	public int insertMember(MemberVO memberVO) {
		int res = memberDAO.insertMember(memberVO);
		return res;
	}

	@Override
	public int usercheck(MemberVO memberVO) {
		int res = memberDAO.userCheck(memberVO);
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
		MemberVO vo = memberDAO.selectMember(memberVO);
		return vo;
	}

	@Override
	public int deleteMember(MemberVO memberVO) {
		int res  = memberDAO.deleteMember(memberVO);
		return res;
	}
	@Override
	public int updateMember(MemberVO memberVO) {
		int res  = memberDAO.updateMember(memberVO);
		return res;
	}

}
