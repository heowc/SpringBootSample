package com.example.java.web;

import com.example.java.domain.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BasicController {

	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);
	@GetMapping("/se")
	public List<Model> index() {
		List<Model> list = new ArrayList<>();
		list.add(new Model("abc", 1));
		list.add(new Model("def", 2));
		list.add(new Model("ghi", 3));
		list.add(new Model("jkl", 4));
		list.add(new Model("nmo", 5));
		list.add(new Model("pqr", 6));
		list.add(new Model("stu", 7));
		list.add(new Model("vwx", 8));
		list.add(new Model("yz", 9));

		return list;
	}

	@PostMapping("/de")
	public List<Model> deIndex(@RequestBody List<Model> deList) {
		logger.info(deList.toString());
		return deList;
	}
}
