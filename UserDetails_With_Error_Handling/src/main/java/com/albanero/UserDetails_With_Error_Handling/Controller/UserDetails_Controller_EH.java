package com.albanero.UserDetails_With_Error_Handling.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.albanero.UserDetails_With_Error_Handling.Exceptions.Resource_Not_Found_Exception;
import com.albanero.UserDetails_With_Error_Handling.Model.ResponseDetails_Model;
import com.albanero.UserDetails_With_Error_Handling.Model.UserDetails_Model;
import com.albanero.UserDetails_With_Error_Handling.Repository.UserDetails_Repository_EH;
import com.albanero.UserDetails_With_Error_Handling.Service.UserDetails_Service_EH;

@RestController
public class UserDetails_Controller_EH {

	@Autowired
	public UserDetails_Service_EH us;

	@Autowired
	private UserDetails_Repository_EH ur;

	@GetMapping(value = "/getallusers")
	public List<UserDetails_Model> findAllUsers() {
		return us.getAllUsers();
	}

	@GetMapping(value = "/getuserbyname/{username}")
	public Optional<UserDetails_Model> findUserByUsername(@PathVariable String username)
			throws Resource_Not_Found_Exception {
		UserDetails_Model u = ur.findUserByUsername(username).orElseThrow(
				() -> new Resource_Not_Found_Exception("No user with username :- '" + username + "' was found."));
		return us.getUserByUsername(username);
	}

	@GetMapping(value = "/getuser/cv/cs/{username}")
	public ResponseDetails_Model getUsers(@PathVariable String username) throws Resource_Not_Found_Exception {
		UserDetails_Model u = ur.findUserByUsername(username).orElseThrow(
				() -> new Resource_Not_Found_Exception("No user with username :- '" + username + "' was found."));
		int cv1 = 0, cs1 = 0;
		Pattern p = Pattern.compile("[aeiouAEIOU]");
		Matcher m = p.matcher(username);
		while (m.find()) {
			cv1++;
		}
		Pattern p1 = Pattern.compile("[ !\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]");
		Matcher m1 = p1.matcher(username);
		while (m1.find()) {
			cs1++;
		}
		return new ResponseDetails_Model(username + " has " + cv1 + " Vowels",
				username + " has " + cs1 + " Special Characters.");
	}

	@PostMapping(value = "/createuser")
	public String addUser(@RequestBody UserDetails_Model u) {
		us.saveUser(u);
		return u.getUsername() + " Details are successfully added.";
	}

	@PutMapping(value = "/updateuser/{username}")
	public Set<String> updateUser(@PathVariable String username, @Validated @RequestBody UserDetails_Model ud)
			throws Resource_Not_Found_Exception {
		UserDetails_Model u = ur.findUserByUsername(username).orElseThrow(
				() -> new Resource_Not_Found_Exception("No user with username :- '" + username + "' was found."));
		String temp = u.getFullname();
		String s = u.getFullname();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'a':
				s = s.replace('a', '/');
			case 'e':
				s = s.replace('e', '@');
			case 'i':
				s = s.replace('i', '#');
			case 'o':
				s = s.replace('o', '%');
			case 'u':
				s = s.replace('u', '*');
			case 'A':
				s = s.replace('A', '<');
			case 'E':
				s = s.replace('E', '>');
			case 'I':
				s = s.replace('I', '}');
			case 'O':
				s = s.replace('O', '&');
			case 'U':
				s = s.replace('U', '?');
			}
		}
		u.setFullname(s);
		ur.save(u);
		Set<String> response = new HashSet<>();
		response.add("Full Name of " + temp + " was updated as " + s);
		return response;
	}

	@DeleteMapping(value = "/deleteuser/{username}")
	public String deleteByUser(@PathVariable String username) throws Resource_Not_Found_Exception {
		UserDetails_Model u = ur.findUserByUsername(username).orElseThrow(
				() -> new Resource_Not_Found_Exception("No user with username :- '" + username + "' was found."));
		us.deleteUser(username);
		return username + " was removed";
	}
}
