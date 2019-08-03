package com.talenthunt.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthunt.api.bo.KeyValue;
import com.talenthunt.api.enums.QuestionTypeCD;
import com.talenthunt.api.exception.ResourceNotFoundException;
import com.talenthunt.api.model.QuestionFormat;
import com.talenthunt.api.model.Questions;
import com.talenthunt.api.model.TempQuestions;
import com.talenthunt.api.repository.QuestionsRepository;
import com.talenthunt.api.repository.TempQuestionsRepository;

@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin
public class QuestionsController {
	@Autowired
	private QuestionsRepository questionsRepository;

	@Autowired
	private TempQuestionsRepository tempQuestionsRepository;

	
	@PostMapping("/create")
	public Questions createQuestion(@Valid @RequestBody Questions questions) {
		// questions.setCreatedAt(new Date());
		System.out.println("QuestionsController.createQuestion()1");
		try {
			return questionsRepository.save(questions);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Questions> getQuestionById(@PathVariable(value = "id") Long questionId)
			throws ResourceNotFoundException {
		Questions questions = questionsRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found on :: " + questionId));
		return ResponseEntity.ok().body(questions);
	}

	@GetMapping("/get")
	public List<Questions> getByQuestionType(@RequestParam(value = "subjectId", required = false) Long subjectId,
			@RequestParam(value = "topicId", required = false) Long topicId,
			@RequestParam(value = "languageId", required = false) Long languageId) throws ResourceNotFoundException {
		if (subjectId == null) {
			subjectId = 0l;
		}
		if (topicId == null) {
			topicId = 0l;
		}
		if (languageId == null) {
			languageId = 0l;
		}

		List<Questions> questions = questionsRepository.getByQuestionType(subjectId, topicId, languageId);
		return questions;
	}

	@PostMapping("/update")
	public Questions updateQuestion(@Valid @RequestBody Questions questions) {
		// questions.setCreatedAt(new Date());
		Questions updateQuestions = questionsRepository.getOne(questions.getId());
		/*
		 * updateQuestions.setQuestionHintText(questions.getQuestionHintText());
		 * updateQuestions.setAnswerExplanation(questions.getQuestionHintText())
		 * ;
		 */
		updateQuestions.setQuestionText(questions.getQuestionText());
		return questionsRepository.save(updateQuestions);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<String> updateQuestion(@PathVariable(value = "id") Long questionId) {
		try {
			questionsRepository.deleteById(questionId);
			return ResponseEntity.ok("Delete Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Delete Failed");
		}

	}

	@GetMapping("questionType/get")
	public List<KeyValue> getByQuestionType() throws ResourceNotFoundException {
		List<KeyValue> keyValues = new ArrayList<>();
		for (QuestionTypeCD questionTypeCD : QuestionTypeCD.values()) {
			KeyValue keyValue = new KeyValue();
			String key = questionTypeCD.getValue();
			keyValue.setKey(key);
			keyValue.setValue(key.replaceAll("_", " "));
			keyValues.add(keyValue);
		}
		return keyValues;
	}

	@PostMapping("/uploadQuestion")
	public Object uploadQuestion(@RequestParam("file") MultipartFile file) throws Exception {
		try {
			if(file!=null || !file.isEmpty()){
				if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())){
		            Workbook workbook = new XSSFWorkbook(file.getInputStream());
		            System.out.println("QuestionsController.uploadQuestion()" +workbook.getNumberOfSheets());
		            Sheet sheet = workbook.getSheet("Java");
		            Row row;
		            int i = 1;
		            while((row =  sheet.getRow(i)) != null ){
		            	i++;
		            	TempQuestions tempQuestions = new TempQuestions();
		            	//tempQuestions.setSubject(new Subject((long) row.getCell(2).getNumericCellValue()));
		            	tempQuestions.setSubject(null);
		            	//tempQuestions.setTopic(new Topic((long) row.getCell(3).getNumericCellValue()));
		            	tempQuestions.setTopic(null);
		        	  	ObjectMapper mapperObj = new ObjectMapper();
		        	  	
		            	QuestionFormat questionFormat = new QuestionFormat();
						try {
							questionFormat.setQue(row.getCell(3).getStringCellValue());
							questionFormat.getOpt().add(row.getCell(7).getStringCellValue());
							questionFormat.getOpt().add(row.getCell(8).getStringCellValue());
							questionFormat.getOpt().add(row.getCell(9).getStringCellValue());
							questionFormat.getOpt().add(row.getCell(10).getStringCellValue());
							questionFormat.getAns().add(row.getCell(12).getStringCellValue());
							tempQuestions.setQuestionText(mapperObj.writeValueAsString(questionFormat));

							tempQuestions
									.setQuestionTypeCD(QuestionTypeCD.valueOf(row.getCell(4).getStringCellValue()));
							if (!"".equals(row.getCell(3).getStringCellValue())) {
								tempQuestionsRepository.save(tempQuestions);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
			           
			            System.out.println(row.getCell(3).getStringCellValue());
		            }
		            System.out.println("QuestionsController.uploadQuestion()"+i);  
					return new String("Total No of Sheet : "+ workbook.getNumberOfSheets()+"\n"
							+ "Last Cell No :"+ sheet.getRow(0).getLastCellNum());
				}
			}
			return new String("Test-false");
			/*System.out.println("QuestionsController.uploadQuestion()"+file.getOriginalFilename());
			System.out.println("QuestionsController.uploadQuestion()"+file.getContentType());*/
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
