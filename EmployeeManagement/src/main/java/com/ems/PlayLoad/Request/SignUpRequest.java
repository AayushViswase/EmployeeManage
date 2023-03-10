package com.ems.PlayLoad.Request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ems.Eums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

	private String firstName;
	private String lastName;
	private String userMail;
	private String password;
	private Integer depID;
	@Enumerated(EnumType.STRING)
	private Role role;
}
