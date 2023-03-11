package com.ems.service;

import com.ems.Exception.InvalidInput;
import com.ems.PlayLoad.Request.LoginRequest;

public interface LoginService {
	public String userLogin(LoginRequest loginRequest) throws InvalidInput;

	public String userLogout(String sessionKey) throws InvalidInput;
}
