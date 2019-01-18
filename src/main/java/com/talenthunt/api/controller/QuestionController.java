package com.talenthunt.api.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.bo.OptionsBo;
import com.talenthunt.api.bo.QuestionBo;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.Category;
import com.talenthunt.api.model.Question;
import com.talenthunt.api.model.Subject;
import com.talenthunt.api.model.Topic;
import com.talenthunt.api.repository.CategoryRepository;
import com.talenthunt.api.repository.QuestionRepository;
import com.talenthunt.api.repository.SubjectRepository;

/**
 * Controller for question
 * for the time being category and topic are here if require we will move them 
 * to different controller
 * @author Harmohan
 *
 */

@RestController
@RequestMapping("/api/v1")
public class QuestionController 
{
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	QuestionRepository questionRepository; 
	
	
	/**
	 * Get all category
	 * @return list category
	 */
	@GetMapping("/category")
	  public List<Category> getAllCategory() {
	    return categoryRepository.findAll();
	  }
	
	
	/**
	 * Get all subject
	 * @return list subject
	 */
	@GetMapping("/subject")
	  public List<Subject> getAllSubject() {
	    return subjectRepository.findAll();
	  }

	
	/**
	   * Get all Question list.
	   *
	   * @return the list
	   */
	  @GetMapping("/questions")
	  public List<Question> getAllQuestions() {
	    return questionRepository.findAll();
	  }

	  /**
	   * Gets question by id.
	   *
	   * @param queId the question id
	   * @return the question by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/questions/{id}")
	  public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long queId)
	      throws ResourceNotFoundException {
		  Question question =
	    		questionRepository
	            .findById(queId)
	            .orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + queId));
	    return ResponseEntity.ok().body(question);
	  }

	  /**
	   * Create Question Question.
	   *
	   * @param Question 
	   * @return the Question
	   */
	  @PostMapping("/question")
	  public Question createQuestion(@Valid @RequestBody QuestionBo questionBo) 
	  {
		  Question question=new Question();
		  Subject subject=new Subject();
		  Topic topic=new Topic();
		  Category category=new Category();
		  subject.setId(questionBo.getSubjectId());
		  topic.setId(questionBo.getTopicId());
		  category.setId(questionBo.getCategoryId());
		  question.setAnswer(questionBo.getAnswer());
		  question.setHint(questionBo.getHint());
		  question.setQuestion(questionBo.getQuestion());
		  question.setOptionOne(questionBo.getOptionBo().getOption1());
		  question.setOptionTwo(questionBo.getOptionBo().getOption2());
		  question.setOptionThree(questionBo.getOptionBo().getOption3());
		  question.setOptionFour(questionBo.getOptionBo().getOption4());
		  question.setSubject(subject);
		  question.setCategory(category);
		  question.setTopic(topic);
		  return questionRepository.save(question);
	  }
	  
	  /**
	   * Gets question by id.
	   *
	   * @param queId the question id
	   * @return the question by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/questions/{categoryid}/{subjectid}/{topicid}/{questioncount}")
	  public List<Question> getAssessment(@PathVariable(value = "categoryid") Long categoryid,@PathVariable(value = "subjectid") Long subjectid,
			  @PathVariable(value = "topicid") Long topicid,@PathVariable(value = "questioncount") Long questioncount)
	      throws ResourceNotFoundException {
		  return questionRepository.getAssesment(categoryid,subjectid,topicid,questioncount);
	  }
	  
	  @GetMapping("/assesment/{categoryid}/{subjectid}/{topicid}/{questioncount}")
	  public List<QuestionBo> getAssessmentbo(@PathVariable(value = "categoryid") Long categoryid,@PathVariable(value = "subjectid") Long subjectid,
			  @PathVariable(value = "topicid") Long topicid,@PathVariable(value = "questioncount") Long questioncount)
	      throws ResourceNotFoundException {
		  List<Question> questionList=new ArrayList<Question>();
		  List<QuestionBo> questionboList=new ArrayList<QuestionBo>();
		  questionList=questionRepository.getAssesment(categoryid,subjectid,topicid,questioncount);
		  if (questionList.size()>0)
		  {
			  Iterator<Question> iterator=questionList.iterator();
			  Question question=iterator.next();
			  QuestionBo bo=new QuestionBo();
			  OptionsBo optionsBo=new OptionsBo();
			  optionsBo.setOption1(question.getOptionOne());
			  optionsBo.setOption2(question.getOptionTwo());
			  optionsBo.setOption3(question.getOptionThree());
			  optionsBo.setOption4(question.getOptionFour());
			  bo.setQuestion(question.getQuestion());
			  bo.setId(question.getId());
			  bo.setOptionBo(optionsBo);
			  questionboList.add(bo);
		  }
		  return questionboList;
	  }

	  @RequestMapping(value = "/AnswerStatus", params = { "id", "answer" }, method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> getAnswerStatus(@RequestParam Long id, @RequestParam String answer)
				throws ResourceNotFoundException {
			Question question = questionRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Question not found  "));
			System.out.println("Question : " + question);

			if (question.getAnswer().equals(answer)) {
				return ResponseEntity.ok("Correct Answer");
			} else {
				return ResponseEntity.ok("Wrong Answer");
			}
		}
}
