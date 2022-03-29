package com.albanero.UserDetails_With_Error_Handling.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albanero.UserDetails_With_Error_Handling.Model.UserDetails_Model;
import com.albanero.UserDetails_With_Error_Handling.Repository.UserDetails_Repository_EH;

@Service
public class UserDetails_Service_EH {

	@Autowired
	private UserDetails_Repository_EH ur;

	public List<UserDetails_Model> getAllUsers() {
		return ur.findAll();
	}

	public Optional<UserDetails_Model> getUserByUsername(String username) {
		return ur.findUserByUsername(username);
	}

	public UserDetails_Model saveUser(UserDetails_Model u) {
		return ur.save(u);
	}

	public void deleteUser(String username) {
		ur.delete(ur.findUserByUsername(username).get());
	}
}
