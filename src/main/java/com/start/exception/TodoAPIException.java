package com.start.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8385331779444802009L;
	private HttpStatus status;
	private String	message;
	
	

}
