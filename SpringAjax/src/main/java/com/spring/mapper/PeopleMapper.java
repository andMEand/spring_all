package com.spring.mapper;

import java.util.List;

import com.spring.ajax.PeopleVO;

public interface PeopleMapper {
	List<PeopleVO> getPeoplelist();  //�������� �������� ������ �� �ִ� ������ �޾��ָ�ȴ� ��� ��ũ�� ��
	int insertPeople(PeopleVO vo); //�����͸� �޾ƿ� ���� ���� Ÿ���� �����ָ� �ȴ�
	void deletePeople(String id);

}
