package com.sparta.myselectshop.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leeseo.lecturesite.security.UserDetailsImpl;
import com.leeseo.lecturesite.service.UserService;
import com.leeseo.lecturesite.user.SignupRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@PostMapping("/user/signup")
	public void signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
		// Validation 예외처리
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if(fieldErrors.size() > 0) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
			}
		}
		userService.signup(requestDto);

	}


}