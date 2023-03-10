package com.ems.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ems.Exception.EmployeeNotFound;
import com.ems.Exception.InvalidInput;
import com.ems.Model.CurerrentSession;
import com.ems.Model.User;
import com.ems.PlayLoad.Request.UpdatePasswordRequest;
import com.ems.PlayLoad.Response.UserResponse;
import com.ems.repository.CurrentSessionRepository;
import com.ems.repository.UserRepository;

@Service
public class EmployeeServicelms implements EmployeeService {
	private UserRepository userRepository;
	private ModelMapper mapper;

	private CurrentSessionRepository currentSessionRepository;


	public EmployeeServicelms(UserRepository userRepository, ModelMapper mapper,
			CurrentSessionRepository currentSessionRepository) {

		this.userRepository = userRepository;
		this.mapper = mapper;
		this.currentSessionRepository = currentSessionRepository;
	}
	@Override
	public UserResponse getDeatils(String sessionKey) throws EmployeeNotFound, InvalidInput {
		// TODO Auto-generated method stub
		CurerrentSession session = currentSessionRepository.findBySessionId(sessionKey)
				.orElseThrow(() -> new InvalidInput("invalid session"));
		User user = userRepository.findByMail(session.getMail());
		UserResponse response = mapper.map(user, UserResponse.class);
		return response;
	}

	@Override
	public String updatePass(UpdatePasswordRequest passwordRequest, String sessionKey) throws InvalidInput {
		// TODO Auto-generated method stub
		CurerrentSession session = currentSessionRepository.findBySessionId(sessionKey)
				.orElseThrow(() -> new InvalidInput("invalid session"));
		User user = userRepository.findByMail(session.getMail());
		if (user.getPassword().equals(passwordRequest.getCurrentPass())) {
			if (passwordRequest.getNewPass().equals(passwordRequest.getConfirmPass())) {
				user.setPassword(passwordRequest.getNewPass());
				userRepository.save(user);
			}else {
				throw new InvalidInput("Password did not match");
			}

		}
		else {
			throw new InvalidInput("Invalid current password");
		}



		return "password update succesfully";
	}



}
