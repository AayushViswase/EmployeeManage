package com.ems.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Exception.InvalidInput;
import com.ems.PlayLoad.Request.LoginRequest;
import com.ems.service.LoginService;

@RestController
@RequestMapping("/authenticate")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws InvalidInput {

		String sessionKey = loginService.userLogin(loginRequest);
		return new ResponseEntity<String>(sessionKey, HttpStatus.OK);
	}
	@GetMapping("/logout")
	public ResponseEntity<String> logout(@PathVariable String sessionKey) throws InvalidInput {
		String message = loginService.userLogout(sessionKey);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}
}
