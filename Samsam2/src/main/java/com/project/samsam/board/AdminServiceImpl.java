package com.project.samsam.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminBoardMapper;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ABoardVO> FindList() {
		AdminBoardMapper adminMapper = sqlSession.getMapper(AdminBoardMapper.class);
		List<ABoardVO> findlist = adminMapper.findList();
		return findlist;
	}

}
