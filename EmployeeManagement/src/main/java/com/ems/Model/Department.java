package com.ems.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	private Integer depID;
	private String depName;
	private String depLocation;
	private Integer totalEmp;
	List<User> employe = new ArrayList<>();
}
