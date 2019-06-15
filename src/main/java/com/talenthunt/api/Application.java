package com.talenthunt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The type Application.
 *
 * @author Harmohan
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer  {

	/**
	 * The entry point of application.
	 *
	 * @param args
	 *            the input arguments
	 */

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Application.class, args);
	}
}
