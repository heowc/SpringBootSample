package com.tistory.heowc.web;

import javax.naming.NotContextException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.tistory.heowc.exception.ExtensionException;

@RestController
public class BasicController {

	@GetMapping("/")
	public String basicError() {
		throw new IllegalStateException("Basic Error");
	}
	
	@GetMapping("/extension")
	public String extensionError() {
		throw new ExtensionException("extension error");
	}
	
	@GetMapping("/extension2")
	public String extension2Error() throws Exception {
		throw new NotContextException();
	}
	
	@GetMapping("/extension3")
	public String extension3Error() throws Exception {
		throw new IllegalArgumentException();
	}
	
	@GetMapping("/extension4")
	public String extension4Error() throws Exception {
		throw new RestClientException("restful error");
	}
}
