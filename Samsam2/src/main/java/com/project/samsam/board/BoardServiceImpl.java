package com.project.samsam.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminBoardMapper;
import com.project.mapper.BoardMapper;
import com.project.samsam.member.MemberVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired // Mybatis(ibatis) ���̺귯���� �����ϴ� Ŭ����
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> getSearchList(String keyword) {
		BoardMapper boardMapper =sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> searchList = new ArrayList<BoardVO>();
		try {
//			System.out.println(vo2.getB_subject());

		 searchList = boardMapper.getSearchList(keyword);
		 System.out.println("searchList ="+searchList.size());
		for(BoardVO vo : searchList) {
			System.out.println("�˻� ��� " + vo.getSubject());
		}
		}
		catch(Exception e) {
			System.out.println("�˻� ����(��) : " + e.getMessage());
		}
		return searchList;
	}
	//Ȩ������ �˻�����Ʈ ��

	@Override
	public BoardVO getSDetail(int num) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = boardMapper.getSDetail(num);
		return board;
	}
	// Ȩ������ �˻� �� ��
	//getSDetail ������ �ʿ����� : �ش� �����۷� �̵�
	
	
	
	
	@Override
	public List<ABoardVOto> findList(ABoardVO abvo){
		AdminBoardMapper adminMapper =sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVOto> list =adminMapper.findList(abvo);
		List<ABoardVOto> newlist = new ArrayList<ABoardVOto>();
		
		for(ABoardVOto vo :list) {
			vo.setCategory(abvo.getKategorie());
			newlist.add(vo);
			System.out.println("��������"+vo.getNick() + "ī�װ� : " + vo.getCategory());
		}
		return newlist;
	}
	@Override
    public List<ABoardVOto> find_w_List(ABoardVO abvo){
		AdminBoardMapper adminMapper =sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVOto> Wlist =adminMapper.find_w_List(abvo);
		for(ABoardVOto vo :Wlist) {
			System.out.println("��������"+vo.getW_doc_no());
		}
		return Wlist;
    }
	//���� �Խñ� ���� 
	
	public MemberVO adModalView_m (Object obj){
		AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
		List<MemberVO> mList =(List<MemberVO>) adminMapper.ad_view_m(obj);
		return mList;
		
	}
     public ABoardVOto adModalView_b (Object obj);
     public List<WarningVO adModalView_w (Object obj);
     public List<CommentVO> adModalView_c (Object obj);
	  //���� �Խñ� �� Modal


}
