package com.example.web;

import com.example.domain.Member;
import com.example.domain.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//			produces = "application/json",
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
		return repository.findById(id).orElse(null);
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
	public String deleteById(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
