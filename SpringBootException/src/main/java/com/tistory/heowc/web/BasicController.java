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
    public void basicError() {
        throw new IllegalStateException("Basic Error");
    }

    @GetMapping("/extension")
    public void extensionError() {
        throw new ExtensionException("extension error");
    }

    @GetMapping("/extension2")
    public void extension2Error() throws Exception {
        throw new NotContextException();
    }

    @GetMapping("/extension3")
    public void extension3Error() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/extension4")
    public void extension4Error() {
        throw new RestClientException("restful error");
    }

    @GetMapping("/extension5")
    public void extension5Error() {
        throw new IndexOutOfBoundsException();
    }

    // Spring 5 추가
    @GetMapping("/extension6")
    public void extension6Error() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
    }
}
