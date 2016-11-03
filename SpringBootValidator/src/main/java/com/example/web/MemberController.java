package com.example.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Member;

@RestController
public class MemberController {

	private static Logger logger = Logger.getLogger(MemberController.class);
	
	@GetMapping
	public String index(){
		return "Hello StringBoot Validator!!";
	}
	
	@GetMapping(value="/join")
	public String join(Member member){
		return "Error";
	}
	
	@PostMapping(value="/join")
	public String join(@Valid @RequestBody Member member, BindingResult bindingResult){
		logger.info(member);
		if(bindingResult.hasErrors()){
			for(ObjectError error : bindingResult.getAllErrors()){
				logger.info(error.getDefaultMessage());
			}
			return "Error";
		}
		return "Success";
	}
}
