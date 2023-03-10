package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	public Department findByDepID(Integer depID);
}
