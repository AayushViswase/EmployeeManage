package com.ems.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Eums.Role;
import com.ems.Model.Department;
import com.ems.Model.User;
import com.ems.PlayLoad.Response.UserResponse;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByMail(String mail);

	public List<User> findByRole(Role admin);

	public Collection<UserResponse> findByDepartment(Department department);

}
