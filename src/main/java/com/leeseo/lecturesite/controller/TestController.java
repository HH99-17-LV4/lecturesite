package com.leeseo.lecturesite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leeseo.lecturesite.dto.user.LoginRequestDto;
import com.leeseo.lecturesite.security.UserDetailsImpl;

@RestController
public class TestController {

	@GetMapping("/api/admin/test")
	public String forAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		System.out.println("\n\n\nadmin api 입장\n\n\n");
		return userDetails.getUsername();
	}

	@GetMapping("/normal")
	public String forNormal(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		System.out.println("\n\n\nuser api 입장\n\n\n");
		return userDetails.getUsername();
	}

	@GetMapping("/api/user/info")
	public LoginRequestDto payLoadTypeTest(@AuthenticationPrincipal UserDetailsImpl userDetails) {

		return new LoginRequestDto(userDetails.getUsername(), userDetails.getPassword());
	}

	@GetMapping("/")
	public String home() {
		return "index.html";
	}

	@GetMapping("/api/user/login")
	public String login() {
		return "login.html";
	}

}

