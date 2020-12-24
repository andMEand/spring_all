package com.spring.ajax;

import java.util.List;

public interface PeopleService {
	List<PeopleVO> getPeoplejson();  
	void insertPeople(PeopleVO vo); 
	void deletePeople(String id);

}

