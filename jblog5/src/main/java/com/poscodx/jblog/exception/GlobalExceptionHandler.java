package com.poscodx.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public String handler(Exception e, Model model) {
		// 1. logging
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());
		logger.error(errors.toString());
		// 2. apologize
		model.addAttribute("error", errors.toString());
		return "errors/exception";
		
		// 3. finish
		
		
	}
	
	
}
