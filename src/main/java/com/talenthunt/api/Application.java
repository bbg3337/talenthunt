package com.talenthunt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthunt.api.model.Questions;

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
   * @param args the input arguments
   */
  public static void main(String[] args) throws JsonProcessingException {
	  	ObjectMapper mapperObj = new ObjectMapper();
	  	System.out.println(mapperObj.writeValueAsString(new Questions()));
		SpringApplication.run(Application.class, args);
	}
}
