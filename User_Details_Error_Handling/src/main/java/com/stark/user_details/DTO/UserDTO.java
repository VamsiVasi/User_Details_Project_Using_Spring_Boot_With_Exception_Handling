package com.stark.user_details.DTO;

import lombok.Data;

@Data
public class UserDTO {
	
	private String username;
	private String fullname;
	private String email;
	private String address;
	private long mobile;
	private String company;

}