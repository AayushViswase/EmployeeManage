package com.ems.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Exception.InvalidInput;
import com.ems.Model.CurerrentSession;
import com.ems.Model.User;
import com.ems.PlayLoad.Request.LoginRequest;
import com.ems.repository.CurrentSessionRepository;
import com.ems.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CurrentSessionRepository currentSessionRepository;
	@Override
	public String userLogin(LoginRequest loginRequest) throws InvalidInput {
		Optional<CurerrentSession> session = currentSessionRepository.findById(loginRequest.getUserName());
		if (session.isPresent()) {
			throw new InvalidInput("User already loggedin");
		}
		User user = userRepository.findByUserMail(loginRequest.getUserName())
				.orElseThrow(() -> new InvalidInput("USer not found"));
		if (!(user.getPassword().equals(loginRequest.getPassword()))) {
			throw new InvalidInput();
		}

		String sessionKey = RandomString.make(8);
		CurerrentSession currentSession=new CurerrentSession();
		currentSession.setMail(user.getUserMail());
		currentSession.setSessionId(sessionKey);
		currentSessionRepository.save(currentSession);

		return sessionKey;
	}

	@Override
	public String userLogout(String sessionKey) throws InvalidInput {
		Optional<CurerrentSession> session = currentSessionRepository.findBySessionId(sessionKey);
		if (!session.isPresent()) {
			throw new InvalidInput("Invalid Session key");
		}
		currentSessionRepository.deleteById(session.get().getMail());
		return "USer succesfully logged out";

	}


}
