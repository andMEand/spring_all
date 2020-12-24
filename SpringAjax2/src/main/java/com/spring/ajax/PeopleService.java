package com.spring.ajax;

import java.util.List;

public interface PeopleService {
	List<PeopleVO> getPeoplejson();  
	void insertPeople(PeopleVO vo); 
	PeopleVO getPeople(String id);
	void updatePeople(PeopleVO vo );
	void deletePeople(String id);

}

