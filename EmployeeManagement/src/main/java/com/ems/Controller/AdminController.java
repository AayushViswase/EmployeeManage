package com.ems.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Exception.EmployeeNotFound;
import com.ems.Exception.InvalidInput;
import com.ems.Exception.UnauthoriseException;
import com.ems.Model.Department;
import com.ems.PlayLoad.Request.DepartmentRequest;
import com.ems.PlayLoad.Request.SignUpRequest;
import com.ems.PlayLoad.Response.UserResponse;
import com.ems.service.AdminService;

@RestController
@RequestMapping("ems/admin")
public class AdminController {
	private AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;

	}

	@GetMapping("/employeesa")
	public ResponseEntity<List<UserResponse>> getAllEmployee(@RequestParam String sessionKey)
			throws EmployeeNotFound, InvalidInput, UnauthoriseException {
		List<UserResponse> response = adminService.getAllEmployee(sessionKey);

		return new ResponseEntity<List<UserResponse>>(response, HttpStatus.OK);

	}

	@GetMapping("/admins")
	public ResponseEntity<List<UserResponse>> getAllAdmin(@RequestParam String sessionKey) throws InvalidInput,UnauthoriseException{
		List<UserResponse> responses=adminService.getAllAdmin(sessionKey);

		return new ResponseEntity<List<UserResponse>>(responses, HttpStatus.OK);
	}

	@GetMapping("/employeeByDep")
	public ResponseEntity<List<UserResponse>> getEmployeeByDepartment(@PathVariable Integer depID,
			@RequestParam String sessionKey) throws InvalidInput, UnauthoriseException {
		List<UserResponse> list=adminService.getEmployeeByDepartment(depID, sessionKey);
		return new ResponseEntity<List<UserResponse>>(list, HttpStatus.OK);

	}

	@PostMapping("/addDep")
	public ResponseEntity<Department> addNewDepartment(@RequestBody DepartmentRequest departmentRequest,
			@RequestParam String sessionKey) throws InvalidInput, UnauthoriseException {
		Department department = adminService.addNewDepartment(departmentRequest, sessionKey);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	@PostMapping("/addUser")
	public ResponseEntity<UserResponse> addNewUser(@RequestBody SignUpRequest request, @RequestParam String sessionKey)
			throws InvalidInput, UnauthoriseException {
		UserResponse response=adminService.addNewUser(request, sessionKey);
		return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
	}

}














