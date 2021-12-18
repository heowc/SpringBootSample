package com.example.errorhandler;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSource messageSource;

    GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<List<ErrorMessage>> constraintViolationException(ConstraintViolationException exception,
                                                                    WebRequest request) {
        logger.warn("path: {}, locale: {}, principal: {}", request.getContextPath(), request.getLocale(),
                                                          request.getUserPrincipal());

        final List<ErrorMessage> body = exception.getConstraintViolations().stream()
                .map(cv -> ErrorMessage.of(property(cv).toString(), cv.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<ErrorMessage>> methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                       WebRequest request) {
        logger.warn("path: {}, locale: {}, principal: {}", request.getContextPath(), request.getLocale(),
                                                          request.getUserPrincipal());

        final List<ErrorMessage> body = exception.getBindingResult().getAllErrors().stream()
                .map(oe -> oe.unwrap(ConstraintViolation.class))
                .map(cv -> ErrorMessage.of(property(cv).toString(), cv.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(body);
    }

    private static Path.Node property(ConstraintViolation<?> constraintViolation) {
        return ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<List<ErrorMessage>> emptyBodyException(HttpMessageNotReadableException exception,
                                                          WebRequest request) {
        logger.warn("path: {}, locale: {}, principal: {}", request.getContextPath(), request.getLocale(),
                                                          request.getUserPrincipal());

        final String message = messageSource.getMessage("body.incorrect.message", new Object[]{ }, Locale.getDefault());
        return ResponseEntity.badRequest().body(Collections.singletonList(ErrorMessage.of("body", message)));
    }
}
