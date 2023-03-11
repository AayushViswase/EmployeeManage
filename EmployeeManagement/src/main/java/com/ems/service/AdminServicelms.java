package com.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Eums.Role;
import com.ems.Exception.InvalidInput;
import com.ems.Exception.UnauthoriseException;
import com.ems.Model.Department;
import com.ems.Model.User;
import com.ems.PlayLoad.Request.DepartmentRequest;
import com.ems.PlayLoad.Request.SignUpRequest;
import com.ems.PlayLoad.Response.UserResponse;
import com.ems.repository.CurrentSessionRepository;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.UserRepository;

@Service
public class AdminServicelms implements AdminService {
	@Autowired
	private UserRepository userRepository;
	private ModelMapper mapper;
	private CurrentSessionRepository currentSessionRepository;
	private DepartmentRepository departmentRepository;

	public AdminServicelms(UserRepository userRepository, ModelMapper mapper,
			CurrentSessionRepository currentSessionRepository, DepartmentRepository departmentRepository) {

		this.userRepository = userRepository;
		this.mapper = mapper;
		this.currentSessionRepository = currentSessionRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<UserResponse> getAllEmployee(String sessionKey) throws InvalidInput, UnauthoriseException {
		currentSessionRepository.findBySessionId(sessionKey)
		.orElseThrow(() -> new InvalidInput("invalid session"));
		List<UserResponse> responses = userRepository.findAll().stream()
				.map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<UserResponse> getAllAdmin(String sessionKey) throws InvalidInput, UnauthoriseException {
		currentSessionRepository.findBySessionId(sessionKey)
		.orElseThrow(() -> new InvalidInput("invalid session"));
		List<User> admins = userRepository.findByRole(Role.ADMIN);
		List<UserResponse> responses = admins.stream().map(user -> mapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return responses;

	}

	@Override
	public List<UserResponse> getEmployeeByDepartment(Integer depID, String sessionKey)
			throws InvalidInput, UnauthoriseException {

		currentSessionRepository.findBySessionId(sessionKey)
		.orElseThrow(() -> new InvalidInput("invalid session"));
		List<UserResponse> responses = userRepository.findByDepartment(depID).stream()
				.map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());

		return responses;
	}

	@Override
	public Department addNewDepartment(DepartmentRequest departmentRequest, String sessionKey)
			throws InvalidInput, UnauthoriseException {
		currentSessionRepository.findBySessionId(sessionKey).orElseThrow(() -> new InvalidInput("Invalid session"));
		Department department = new Department();
		department.setDepName(departmentRequest.getDepName());
		department.setDepLocation(departmentRequest.getDepLocation());

		return departmentRepository.save(department);
	}

	@Override
	public UserResponse addNewUser(SignUpRequest request, String sessionKey) throws InvalidInput, UnauthoriseException {
		// TODO Auto-generated method stub
		currentSessionRepository.findBySessionId(sessionKey).orElseThrow(() -> new InvalidInput("Invalid session"));
		if (request.getUserMail() == null || request.getUserMail().isEmpty() || request.getPassword() == null
				|| request.getPassword().isEmpty()) {
			throw new InvalidInput("Invalid Signup Request");
		}
		if (userRepository.findByMail(request.getUserMail()) != null) {
			throw new InvalidInput("User with same email already exists");
		}
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPassword(request.getPassword());
		user.setUserMail(request.getUserMail());
		// how to take departmetn

		userRepository.save(user);

		UserResponse response = new UserResponse();

		response.setUserMail(user.getUserMail());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());


		return null;
	}

}
