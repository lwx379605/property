package com.lmc.property.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 李敏成
 *
 */
@Controller("memberLoginController")
@RequestMapping("/member")
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
