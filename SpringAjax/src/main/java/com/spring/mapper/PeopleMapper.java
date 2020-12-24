package com.spring.mapper;

import java.util.List;

import com.spring.ajax.PeopleVO;

public interface PeopleMapper {
	List<PeopleVO> getPeoplelist();  //여러개의 데이터을 저장할 수 있는 구조로 받아주면된다 어레이 린크드 등
	int insertPeople(PeopleVO vo); //데이터를 받아올 경우는 리턴 타입을 정해주면 된다
	void deletePeople(String id);

}
