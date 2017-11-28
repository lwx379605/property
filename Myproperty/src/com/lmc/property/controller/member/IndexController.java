package com.lmc.property.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 李敏成
 *
 */
@Controller("memberIndex")
@RequestMapping("/member")
public class IndexController extends BaseController{
	
	@RequestMapping("/index")
	public String index(){
		return null;
	}
}
