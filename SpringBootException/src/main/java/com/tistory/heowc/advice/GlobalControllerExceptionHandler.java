package com.tistory.heowc.advice;

import javax.naming.NotContextException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.tistory.heowc.domain.ErrorInfo;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class);

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
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex);
	}
}
