package com.prashant21tube.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prashant21tube.model.Tutorial;
import com.prashant21tube.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class TutorialController {

	private Logger LOG = LoggerFactory.getLogger(TutorialController.class);

	@Autowired
	private TutorialRepository repository;

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {

		try {
			List<Tutorial> tutorials = new ArrayList<>();
			if (title == null) {
				repository.findAll().forEach(tutorials::add);
			} else {
				repository.findByTitleContaining(title).forEach(tutorials::add);
			}

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Not able to fetch tutorials {[]}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable(required = true, name = "id") long id) {

		Tutorial tutorial = repository.findById(id);

		if (tutorial != null) {
			return new ResponseEntity<>(tutorial, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/tutorials")
	public ResponseEntity<String> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			repository.save(tutorial);
			return new ResponseEntity<>("Tutorial was created successfully!!!", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<String> updateTutorial(@PathVariable(name = "id") long id, @RequestBody Tutorial tutorial) {
		Tutorial _tutorial = repository.findById(id);

		if (_tutorial != null) {
			_tutorial.setId(id);
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			repository.update(_tutorial);
			return new ResponseEntity<>("Tutorial is updated ", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No tutorial find for given id", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/tutorial/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		
		int result = repository.deleteBy(id);
		if(result == 0) {
			return new ResponseEntity<>("No tutorial for given id", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>("Tutorial was sucessfully deleted!", HttpStatus.OK);
		}	
	}
	

}
