package com.lmc.property.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmc.property.service.BaseService;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Inject
	BaseService  bs;
	
	@RequestMapping({"","/"})
	public ResponseEntity<String> hander(){
		bs.publishEvent();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
