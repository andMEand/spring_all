package com.project.samsam.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminBoardMapper;
import com.project.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired // Mybatis(ibatis) 라이브러리가 제공하는 클래스
	private SqlSession sqlSession;

	@Override
	public List<BoardVO2> getSearchList(String keyword) {
		BoardMapper boardMapper =sqlSession.getMapper(BoardMapper.class);
		List<BoardVO2> searchList = new ArrayList<BoardVO2>();
		try {
//			System.out.println(vo2.getB_subject());

		 searchList = boardMapper.getSearchList(keyword);
		 System.out.println("searchList ="+searchList.size());
		for(BoardVO2 vo : searchList) {
			System.out.println("검색 결과 " + vo.getB_subject());
		}
		}
		catch(Exception e) {
			System.out.println("검색 에러(서) : " + e.getMessage());
		}
		return searchList;
	}
	//홈페이지 검색리스트 끝

	@Override
	public BoardVO2 getSDetail(int num) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO2 board = boardMapper.getSDetail(num);
		return board;
	}
	// 홈페이지 검색 뷰 끝
	//getSDetail 수정할 필요있음 : 해당 원본글로 이동
	
	
	
	
	@Override
	public List<ABoardVOto> findList(ABoardVO abvo){
		AdminBoardMapper adminMapper =sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVOto> list =adminMapper.findList(abvo);
		for(ABoardVOto vo :list) {
			System.out.println("서비스임플"+vo.getNick());
		}
		return list;
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

////////////////////
	
	@Override
	public int getListCount() {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardMapper.getListCount();
		return res;
	}

	@Override
	public List<BoardVO> getBoardList(HashMap<String, Integer> hashmap) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> boardlist = boardMapper.getBoardList(hashmap);
		return boardlist;
	}

	@Override
	public BoardVO getDetail(int num) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.setReadCountUpdate(num);
		BoardVO board = boardMapper.getDetail(num);
		return board;
	}

	@Override
	public int boardInsert(BoardVO board) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardMapper.boardInsert(board);
		return res;
	}

	@Override
	public int boardReply(BoardVO board) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.boardReplyupdate(board);
//		board.setRe_seq(board.getRe_seq()+1);
//		board.setRe_lev(board.getRe_lev()+1);
		int res = boardMapper.boardInsert(board);
		return res;
	}

	@Override
	public BoardVO boardModifyForm(int num) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = boardMapper.getDetail(num);
		return board;
	}

	@Override
	public int boardModify(BoardVO modifyboard) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardMapper.boardModify(modifyboard);
		return res;
	}

	@Override
	public int boardDelete(HashMap<String, String> hashmap) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardMapper.isBoardWriter(hashmap);
		int num = Integer.parseInt(hashmap.get("num"));

		if (res == 1) {
			res = boardMapper.boardDelete(num);
		}
		return res;
	}
}
