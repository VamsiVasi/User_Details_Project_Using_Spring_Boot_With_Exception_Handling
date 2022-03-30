package com.stark.user_details.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Users")
@Data
public class User {
	
	@Id
	private String id;
	private String username;
	private String fullname;
	private String email;
	private String address;
	private long mobile;
	private String company;

}
