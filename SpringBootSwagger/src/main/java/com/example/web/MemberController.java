package com.example.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Member;
import com.example.domain.MemberRepository;

@Api(value = "Member for API")
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberRepository repository;

	@ApiOperation(
			value = "getId",
			notes = "아이디 조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			responseHeaders = {
				// Headers ...
			})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "No param")
		// Other Http Status Code ...
	})
	@GetMapping("/{id}")
	public Member getId(@PathVariable("id") String id) {
		return repository.getOne(id);
	}
	
	@PostMapping
	public Member createMember(@RequestBody Member member) {
		return repository.save(member);
	}
	
	@PutMapping
	public Member updateMember(@RequestBody Member member) {
		return repository.save(member);
	}
	
	@DeleteMapping("/{id}")
	public String deleteId(@PathVariable("id") String id) {
		try {
			repository.delete(id);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
