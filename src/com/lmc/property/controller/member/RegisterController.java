package com.lmc.property.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 李敏成
 *
 */
@Controller
@RequestMapping("/member/register")
public class RegisterController extends BaseController{
	
	@GetMapping("")
	public String register(){
		return null;
	}
	
	@PostMapping("save")
	public String save(){
		return null;
	}
}
