package com.talenthunt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talenthunt.api.repository.AssessmentRepository;

/**
 * The type Application.
 *
 * @author Harmohan
 */
@SpringBootApplication
public class Application {

	/**
	 * The entry point of application.
	 *
	 * @param args
	 *            the input arguments
	 */
	@Autowired
	static AssessmentRepository assessmentRepository;

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Application.class, args);
	}
}
