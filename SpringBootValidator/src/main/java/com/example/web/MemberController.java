package com.example.web;

import com.example.domain.Member;
import com.example.validation.Phone;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@RestController
@RequestMapping("member")
public class MemberController {

	@PostMapping
	public ResponseEntity<?> postAdd(@Valid @RequestBody Member member) {
		return ResponseEntity.ok(member);
	}

	@GetMapping
	public ResponseEntity<?> getAdd(@NotNull String name,
									@NotNull @Min(14) Integer age,
									@Phone String phone) {
		return ResponseEntity.ok(new Member(name, age, phone));
	}
}
