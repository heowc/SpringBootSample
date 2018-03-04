package com.tistory.heowc.web;

import com.tistory.heowc.exception.ExtensionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.NotContextException;

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
	public String extension3Error() {
		throw new IllegalArgumentException();
	}

	@GetMapping("/extension4")
	public String extension4Error() {
		throw new RestClientException("restful error");
	}

	// Spring 5 추가
	@GetMapping("/extension5")
	public String extension5Error() {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
	}
}
