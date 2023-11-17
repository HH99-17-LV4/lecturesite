// package com.leeseo.lecturesite.exception;
//
// import org.springframework.stereotype.Controller;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j(topic = "@Valid Exception")
// @ControllerAdvice
// @Controller
// public class ValidExceptionHandler {
// 	@ExceptionHandler(MethodArgumentNotValidException.class)
// 	public String processValidationError(MethodArgumentNotValidException exception) {
// 		BindingResult bindingResult = exception.getBindingResult();
//
// 		StringBuilder sb = new StringBuilder();
// 		for (FieldError fieldError : bindingResult.getFieldErrors()) {
// 			sb.append(fieldError.getField()).append("는(은) ");
// 			sb.append(fieldError.getDefaultMessage());
// 			sb.append("잘못된 입력값 : ").append(fieldError.getRejectedValue());
// 		}
// 		log.info("sb.toString() : " + sb.toString());
// 		return sb.toString();
// 	}
// }
