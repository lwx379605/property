package com.lmc.property.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 李敏成
 *
 */
@Controller("adminIndex")
@RequestMapping("/admin")
public class IndexController extends BaseController{
	
	@RequestMapping("/index")
	public String index(){
		return null;
	}
}
