package com.example.web;

import com.example.domain.ErrorMessage;
import com.example.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("member")
public class MemberController {

	private static final int ZERO = 0;

	@GetMapping
	public String find() {
		return "Error";
	}

	@PostMapping
	public ResponseEntity<?> add(@Valid @RequestBody Member member, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getAllErrors().get(ZERO).getDefaultMessage();
			return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMessage));
		}

		return ResponseEntity.ok(member);
	}
}
