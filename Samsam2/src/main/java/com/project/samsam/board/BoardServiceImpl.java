package com.project.samsam.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminBoardMapper;
import com.project.mapper.BoardMapper;

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
		for(ABoardVOto vo :list) {
			System.out.println("��������"+vo.getNick());
		}
		return list;
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


}
