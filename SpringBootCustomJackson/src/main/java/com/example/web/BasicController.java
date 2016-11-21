package com.example.web;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Model;

@RestController
public class BasicController {

	@GetMapping("/se")
	public ArrayList<Model> index() {
		ArrayList<Model> list = new ArrayList<Model>();
		list.add(new Model("abc",1));
		list.add(new Model("def",2));
		list.add(new Model("ghi",3));
		list.add(new Model("jkl",4));
		list.add(new Model("nmo",5));
		list.add(new Model("pqr",6));
		list.add(new Model("stu",7));
		list.add(new Model("vwx",8));
		list.add(new Model("yz",9));
		
		return list; 
	}
	
	@PostMapping("/de")
	public ArrayList<Model> deIndex(@RequestBody ArrayList<Model> deList) {
		System.out.println(deList);
		return deList; 
	}
}
