package com.ems.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Eums.Role;
import com.ems.Model.User;
import com.ems.PlayLoad.Response.UserResponse;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUserMail(String mail);

	public List<User> findByRole(Role admin);

	public Collection<UserResponse> findByDepartment(Integer depID);


}
