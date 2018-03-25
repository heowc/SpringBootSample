package com.tistory.heowc.web;

import com.tistory.heowc.domain.Programming;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("programming")
public class ProgrammingController {

	@GetMapping()
	public List<Programming> findAll() {
		return Arrays.asList(
				new Programming("Java", "oracle.com"),
				new Programming("go", "golang.org/"),
				new Programming("kotlin", "kotlinlang.org")
		);
	}
}
