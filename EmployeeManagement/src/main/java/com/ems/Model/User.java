package com.ems.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ems.Eums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private Integer userID;
	private String firstName;
	private String lastName;
	private  String userMail;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	private Department department;
}
