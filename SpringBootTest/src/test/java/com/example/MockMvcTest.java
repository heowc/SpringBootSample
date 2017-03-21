package com.example;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.TestVo;
import com.example.service.BasicService;
import com.example.web.BasicController;

@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)
public class MockMvcTest {

	@Autowired MockMvc mvc;
	
	@MockBean BasicService service;
	
	@Test
	public void test() throws Exception {
		
		JSONObject json = new JSONObject();
		json.put("name", "wonchul");
		json.put("type", 0);
		
		given(service.jsonTest())
			.willReturn(new TestVo("wonchul", 0));
		
		mvc.perform(get("/jsonTest")
					.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().json(json.toString()));
	}
}
