package com.prashant21tube.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prashant21tube.model.Tutorial;

@Repository
public class JdbcTutorialRepository implements TutorialRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Tutorial tutorial) {
		String insertQuery = "insert into tutorials(title,description,published) values (?,?,?)";
		return jdbcTemplate.update(insertQuery,
				new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished() });
	}

	@Override
	public int update(Tutorial tutorial) {
		String updateQuery = "update tutorials set title=?, description=?, published=? where id=?";
		return jdbcTemplate.update(updateQuery, new Object[] { tutorial.getTitle(), tutorial.getDescription(),
				tutorial.isPublished(), tutorial.getId() });
	}

	@Override
	public Tutorial findById(Long id) {

		try {
			Tutorial tutorial = jdbcTemplate.queryForObject("select * from tutorials where id = ?",
					BeanPropertyRowMapper.newInstance(Tutorial.class), id);
			return tutorial;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteBy(Long id) {
		return jdbcTemplate.update("delete from tutorials where id = ?", id);
	}

	@Override
	public List<Tutorial> findAll() {
		return jdbcTemplate.query("select * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
	}

	@Override
	public List<Tutorial> findByPublished(boolean published) {
		return jdbcTemplate.query("select * from tutorials where published=?",
				BeanPropertyRowMapper.newInstance(Tutorial.class), published);
	}

	@Override
	public List<Tutorial> findByTitleContaining(String title) {
		String query = "select * from tutorials where title like '%" +title+ "%'";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Tutorial.class), title);
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("delete from tutorials");
	}

}
