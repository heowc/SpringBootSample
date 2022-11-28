package com.tistory.heowc.advice;

import com.tistory.heowc.domain.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.NotContextException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalRestControllerAdvicer {

    private static final Logger logger = LoggerFactory.getLogger(GlobalRestControllerAdvicer.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotContextException.class)
    public ModelAndView handleNotContent() {
        logger.info("NotContextException 입니다.");
        // 커스텀 뷰 표현..
        // error 같은 경우, 필요한 값이 있기 때문에 이하 생략...
        return new ModelAndView("error");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleConflict() {
        logger.info("handleConflict");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestClientException.class)
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

    @ExceptionHandler(Exception.class)
    public ErrorInfo handleEtcException(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}
