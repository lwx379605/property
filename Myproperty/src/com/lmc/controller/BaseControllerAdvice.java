package com.lmc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lmc.exception.BaseException;

@ControllerAdvice
public class BaseControllerAdvice {
	
	@InitBinder
	public void initBinder(){
		
	}
	
	@ExceptionHandler
	public void exceptionHandler(BaseException execption){
		
	}
	
	@ModelAttribute
	public void modelAttribute(){
		
	}
	 

}
