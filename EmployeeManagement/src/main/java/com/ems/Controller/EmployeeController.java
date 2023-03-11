package com.ems.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Exception.EmployeeNotFound;
import com.ems.Exception.InvalidInput;
import com.ems.PlayLoad.Request.UpdatePasswordRequest;
import com.ems.PlayLoad.Response.UserResponse;
import com.ems.service.EmployeeService;

@RestController
@RequestMapping("ems/employee")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/{sessionKey}")
	public ResponseEntity<UserResponse> getDetails(@PathVariable String sessionKey)
			throws EmployeeNotFound, InvalidInput {
		UserResponse response = employeeService.getDeatils(sessionKey);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);

	}

	@PostMapping("/{sessionKey}/password")
	public ResponseEntity<String> updatePass(@PathVariable String sessionKey,
			@RequestBody UpdatePasswordRequest passwordRequest
			) throws EmployeeNotFound, InvalidInput {
		String message = employeeService.updatePass(passwordRequest, sessionKey);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}
}
