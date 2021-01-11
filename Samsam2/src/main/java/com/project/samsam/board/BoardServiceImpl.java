package com.project.samsam.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminBoardMapper;
import com.project.mapper.BoardMapper;
import com.project.samsam.member.MemberVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired // Myb atis(ibatis) ���̺귯���� �����ϴ� Ŭ����
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> getSearch_commu_List(String keyword) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> c_list = boardMapper.getSearch_commu_List(keyword);
		return c_list;
		
	}
	@Override
	  public List<BoardVO> getSearch_adopt_List(String keyword) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> a_list = boardMapper.getSearch_adopt_List(keyword);
		return a_list;
		  
	  }
	@Override
	  public List<BoardVO> getSearch_free_List(String keyword) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> f_list = boardMapper.getSearch_free_List(keyword);
		return f_list;
		
	  }
	  

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
			System.out.println("서비스임플"+vo);
		}
		return Wlist;
    }
	//어드민 게시글 관리 
	
	public MemberVO adModalView_m (ADModalVO movo){
		AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
		MemberVO mvo = adminMapper.ad_view_m(movo);
		return mvo;
		
	}
     public ABoardVOto adModalView_b (ADModalVO movo) {
    	 AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
    	 ABoardVOto bvo = adminMapper.ad_view_b(movo);
 		return  bvo;
     }
     
     public List<CommentVO> adModalView_c (ADModalVO movo){
    	 AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
    	 List<CommentVO> cList =(List<CommentVO>) adminMapper.ad_view_cList(movo);
    	 return  cList;
     }
     public CommentVO adModalView_ccount(ADModalVO movo){
    	 AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
    	 CommentVO co_count = adminMapper.ad_view_ccount(movo);
 		return  co_count;
     }
     
     public List<WarningVO> adModalView_w (ADModalVO movo){
    	 AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
    	 List<WarningVO> wList =(List<WarningVO>) adminMapper.ad_view_wList(movo);
 		return  wList;
     }
     public WarningVO adModalView_wcount(ADModalVO movo){
    	 AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
    	 WarningVO w_count = adminMapper.ad_view_wcount(movo);
 		return  w_count;
     }
	  //어드민 게시글 뷰 Modal


}
