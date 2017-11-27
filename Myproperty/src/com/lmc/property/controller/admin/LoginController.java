package com.lmc.property.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController extends BaseController{
	
	@RequestMapping({"","/"})
	public String index(){
		return "redirect:login";
	}
	
	@RequestMapping("login")
	public String index(String id){
		return null;
	}

}
