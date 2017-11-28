package com.lmc.property.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmc.property.entity.Member;
import com.lmc.property.service.TestService;
import com.lmc.property.service.MemberService;

@Controller
@RequestMapping("/base")
public class TestController {
	@Inject
	MemberService ms;
	@Inject
	TestService  bs;
	
	@RequestMapping({"","/"})
	public ResponseEntity<String> hander(@ModelAttribute(binding=false) Member abs){
		System.out.println(abs);
		bs.publishEvent();
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping("test")
	public String hander1(@ModelAttribute(binding=true) Member abs){
		System.out.println(abs);
		return "/index";
	}
	@RequestMapping("test1")
	public String hande(){
		Member member = new Member();
		member.setUsername("admin");
		member.setPassword("123456");
		member.setMobile("17682344359");
		member.setGender(Member.Gender.male);
		member.setAmount(new BigDecimal(0));
		member.setBalance(new BigDecimal(0));
		member.setIsEnabled(false);
		member.setIsLocked(false);
		ms.save(member);
		return "/index";
	}
	
//	@ModelAttribute
//	public Member modelAttribute(){
//		//return new Member("limincheng",new Value("123"));
//	}
}
