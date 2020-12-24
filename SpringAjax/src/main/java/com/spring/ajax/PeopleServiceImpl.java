package com.spring.ajax;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.PeopleMapper;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PeopleVO> getPeoplejson() {
		List<PeopleVO> peopleList = null;
		PeopleMapper memberMapper= sqlSession.getMapper(PeopleMapper.class);
		peopleList = memberMapper.getPeoplelist();
		return peopleList;
	}

	@Override
	public void insertPeople(PeopleVO vo) {
		PeopleMapper memberMapper = sqlSession.getMapper(PeopleMapper.class);
		memberMapper.insertPeople(vo);
		return;
	}

	@Override
	public void deletePeople(String id) {
		PeopleMapper memberMapper = sqlSession.getMapper(PeopleMapper.class);
		memberMapper.deletePeople(id);
		return;
	}

}
