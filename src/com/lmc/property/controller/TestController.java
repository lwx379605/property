package com.lmc.property.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.lmc.property.entity.Renter;
import com.lmc.property.service.TestService;
import com.lmc.property.utils.FreeMarkerUtils;
import com.lmc.property.utils.SystemUtils;
import com.lmc.property.service.MemberService;

/**
 * 
 * @author 李敏成
 *
 */
@Controller
@RequestMapping("")
public class TestController {
	@Inject
	MemberService ms;
	@Inject
	TestService  bs;
	@Inject
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping({"","/"})
	public ResponseEntity<String> hander(@ModelAttribute(binding=false) Renter abs){
		System.out.println(freeMarkerConfigurer.getConfiguration().getSharedVariable("base"));
		System.out.println(abs);
		bs.publishEvent();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping("test")
	public String hander1(@ModelAttribute(binding=true) Renter abs){
		System.out.println(abs);
		return "/index";
	}
	@RequestMapping("test1")
	public String hande(){
		System.out.println(freeMarkerConfigurer.getConfiguration().getSharedVariable("base"));
//		Renter member = new Renter();
//		member.setUsername("admin");
//		member.setPassword("123456");
//		member.setMobile("17682344359");
//		member.setGender(Renter.Gender.male);
//		member.setAmount(new BigDecimal(0));
//		member.setIsEnabled(false);
//		member.setIsLocked(false);
//		ms.save(member);
		return "/index";
	}
	
//	@ModelAttribute
//	public Member modelAttribute(){
//		//return new Member("limincheng",new Value("123"));
//	}
}
