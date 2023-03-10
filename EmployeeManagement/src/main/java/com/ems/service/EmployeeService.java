package com.ems.service;

import com.ems.Exception.EmployeeNotFound;
import com.ems.Exception.InvalidInput;
import com.ems.PlayLoad.Request.UpdatePasswordRequest;
import com.ems.PlayLoad.Response.UserResponse;

public interface EmployeeService {

	public UserResponse getDeatils(String sessionKey) throws EmployeeNotFound, InvalidInput;

	public String updatePass(UpdatePasswordRequest passwordRequest, String sessionKey) throws InvalidInput;


}
