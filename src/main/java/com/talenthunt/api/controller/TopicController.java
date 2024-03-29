package com.talenthunt.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Subject;
import com.talenthunt.api.model.Topic;
import com.talenthunt.api.repository.TopicRepository;

/**
 * Controller for Topic
 * 
 * @author Harmohan
 *
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class TopicController 
{
	
	@Autowired
	TopicRepository topicRepository;
	
	
	/**
	 * Get all topics
	 * @return list topics
	 */
	@GetMapping("/topics")
	  public List<Topic> getAllTopics() {
	    return topicRepository.findAll();
	  }
	
	
	/**
	   * Gets topic by id.
	   *
	   * @param topicId the topic id
	   * @return the Topic by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/topics/{id}")
	  public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") Long topicId)
	      throws ResourceNotFoundException {
	    Topic topic =
	    		topicRepository
	            .findById(topicId)
	            .orElseThrow(() -> new ResourceNotFoundException("topic not found on :: " + topicId));
	    return ResponseEntity.ok().body(topic);
	  }
	  
	  /**
	   * Gets topic by Subject id.
	   *
	   * @param subjectId the subject id
	   * @return the topic by subjectId
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/topics/subject/{id}")
	  public List<Topic>  getTopicBySubjectId(@PathVariable(value = "id") Long subjectId)
	      throws ResourceNotFoundException {
		  List<Topic>  topic =
	    		topicRepository
	            .findBySubjectId(subjectId);
		return topic;
	           
	  }
	  
	  @PostMapping("/topics/create")
	  public Topic createTopic(@Valid @RequestBody Topic topic){
		  return topicRepository.save(topic);
	  }
	  
	  @PostMapping("/topics/update")
	  public Topic updateTopic(@Valid @RequestBody Topic topic){
		  Topic orgTopic =  topicRepository.getOne(topic.getId());
		  orgTopic.setName(topic.getName());
		  orgTopic.setSubject(topic.getSubject());
		  orgTopic.setTopicDesc(topic.getTopicDesc());
		  return topicRepository.save(orgTopic);
	  }
}
