package com.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ems.Eums.Role;
import com.ems.Exception.InvalidInput;
import com.ems.Exception.UnauthoriseException;
import com.ems.Model.CurerrentSession;
import com.ems.Model.Department;
import com.ems.Model.User;
import com.ems.PlayLoad.Request.DepartmentRequest;
import com.ems.PlayLoad.Request.SignUpRequest;
import com.ems.PlayLoad.Response.UserResponse;
import com.ems.repository.CurrentSessionRepository;
import com.ems.repository.UserRepository;

@Service
public class AdminServicelms implements AdminService {
	private UserRepository userRepository;
	private ModelMapper mapper;
	private CurrentSessionRepository currentSessionRepository;

	public AdminServicelms(UserRepository userRepository, ModelMapper mapper,
			CurrentSessionRepository currentSessionRepository) {

		this.userRepository = userRepository;
		this.mapper = mapper;
		this.currentSessionRepository = currentSessionRepository;
	}

	@Override
	public List<UserResponse> getAllEmployee(String sessionKey) throws InvalidInput, UnauthoriseException {
		CurerrentSession session = currentSessionRepository.findBySessionId(sessionKey)
				.orElseThrow(() -> new InvalidInput("invalid session"));
		List<UserResponse> responses = userRepository.findAll().stream()
				.map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<UserResponse> getAllAdmin(String sessionKey) throws InvalidInput, UnauthoriseException {
		// TODO Auto-generated method stub
		CurerrentSession session = currentSessionRepository.findBySessionId(sessionKey)
				.orElseThrow(() -> new InvalidInput("invalid session"));
		List<User> admins = userRepository.findByRole(Role.ADMIN);
		List<UserResponse> responses = admins.stream().map(user -> mapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return responses;

	}

	@Override
	public List<UserResponse> getEmployeeByDepartment(Integer depID, String sessionKey)
			throws InvalidInput, UnauthoriseException {

		// TODO Auto-generated method stub
		CurerrentSession session = currentSessionRepository.findBySessionId(sessionKey)
				.orElseThrow(() -> new InvalidInput("invalid session"));
		List<UserResponse> responses = userRepository.findByDepartment(null).stream()
				.map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
		return responses;
	}

	@Override
	public Department addNewDepartment(DepartmentRequest departmentRequest, String sessionKey)
			throws InvalidInput, UnauthoriseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse addNewUser(SignUpRequest request, String sessionKey) throws InvalidInput, UnauthoriseException {
		// TODO Auto-generated method stub
		return null;
	}

}
