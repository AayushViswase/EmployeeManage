package com.ems.service;

import java.util.List;

import com.ems.Exception.InvalidInput;
import com.ems.Exception.UnauthoriseException;
import com.ems.Model.Department;
import com.ems.PlayLoad.Request.DepartmentRequest;
import com.ems.PlayLoad.Request.SignUpRequest;
import com.ems.PlayLoad.Response.UserResponse;

public interface AdminService {
	public List<UserResponse> getAllEmployee(String sessionKey)
			throws InvalidInput, UnauthoriseException;

	public List<UserResponse> getAllAdmin(String sessionKey) throws InvalidInput, UnauthoriseException;

	public List<UserResponse> getEmployeeByDepartment(Integer depID, String sessionKey)
			throws InvalidInput, UnauthoriseException;

	public Department addNewDepartment(DepartmentRequest departmentRequest, String sessionKey)
			throws InvalidInput, UnauthoriseException;

	public UserResponse addNewUser(SignUpRequest request, String sessionKey) throws InvalidInput, UnauthoriseException;
}
