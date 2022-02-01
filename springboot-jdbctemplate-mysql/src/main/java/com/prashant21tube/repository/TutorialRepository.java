package com.prashant21tube.repository;

import java.util.List;

import com.prashant21tube.model.Tutorial;

public interface TutorialRepository {
	
	int save(Tutorial book);
	
	int update(Tutorial book);
	
	Tutorial findById(Long id);
	
	int deleteBy(Long id);
	
	List<Tutorial> findAll();
	
	List<Tutorial> findByPublished(boolean published);
	
	List<Tutorial> findByTitleContaining(String title);
	
	int deleteAll();

}
