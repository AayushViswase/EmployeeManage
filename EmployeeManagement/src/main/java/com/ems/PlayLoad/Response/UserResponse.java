package com.ems.PlayLoad.Response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ems.Eums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private String firstName;
	private String lastName;
	private String userMail;
	private Integer depID;
	@Enumerated(EnumType.STRING)
	private Role role;
}
