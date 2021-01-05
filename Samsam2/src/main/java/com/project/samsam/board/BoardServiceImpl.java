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

	@Autowired // Mybatis(ibatis) 라이브러리가 제공하는 클래스
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
			System.out.println("검색 결과 " + vo.getSubject());
		}
		}
		catch(Exception e) {
			System.out.println("검색 에러(서) : " + e.getMessage());
		}
		return searchList;
	}
	//홈페이지 검색리스트 끝

	@Override
	public BoardVO getSDetail(int num) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = boardMapper.getSDetail(num);
		return board;
	}
	// 홈페이지 검색 뷰 끝
	//getSDetail 수정할 필요있음 : 해당 원본글로 이동
	
	
	
	
	@Override
	public List<ABoardVOto> findList(ABoardVO abvo){
		AdminBoardMapper adminMapper =sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVOto> list =adminMapper.findList(abvo);
		List<ABoardVOto> newlist = new ArrayList<ABoardVOto>();
		
		for(ABoardVOto vo :list) {
			vo.setCategory(abvo.getKategorie());
			newlist.add(vo);
			System.out.println("서비스임플"+vo.getNick() + "카테고리 : " + vo.getCategory());
		}
		return newlist;
	}
	@Override
    public List<ABoardVOto> find_w_List(ABoardVO abvo){
		AdminBoardMapper adminMapper =sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVOto> Wlist =adminMapper.find_w_List(abvo);
		for(ABoardVOto vo :Wlist) {
			System.out.println("서비스임플"+vo.getW_doc_no());
		}
		return Wlist;
    }
	//어드민 게시글 관리 
	
	public MemberVO adModalView_m (Object obj){
		AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
		List<MemberVO> mList =(List<MemberVO>) adminMapper.ad_view_m(obj);
		return mList;
		
	}
     public ABoardVOto adModalView_b (Object obj);
     public List<WarningVO adModalView_w (Object obj);
     public List<CommentVO> adModalView_c (Object obj);
	  //어드민 게시글 뷰 Modal


}
