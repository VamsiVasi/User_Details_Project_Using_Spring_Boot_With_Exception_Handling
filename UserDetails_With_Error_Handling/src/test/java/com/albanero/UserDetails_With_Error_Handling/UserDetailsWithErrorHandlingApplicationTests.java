package com.albanero.UserDetails_With_Error_Handling;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.albanero.UserDetails_With_Error_Handling.Model.UserDetails_Model;
import com.albanero.UserDetails_With_Error_Handling.Repository.UserDetails_Repository_EH;
import com.albanero.UserDetails_With_Error_Handling.Service.UserDetails_Service_EH;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsWithErrorHandlingApplicationTests {

	@Autowired
	private UserDetails_Service_EH us;

	@MockBean
	private UserDetails_Repository_EH ur;

	@Test
	public void getAllUsersTest() {
		when(ur.findAll()).thenReturn(Stream.of(
				new UserDetails_Model("Balu453", "Balu Prakash", "balu73@gmail.com", "Guntur", 987453121,
						"Albanero"),
				new UserDetails_Model("Venky112", "Venkatesh", "venky8564@gmail.com", "VSP", 897456312,
						"Albanero"))
				.collect(Collectors.toList()));
		assertEquals(2, us.getAllUsers().size());
	}

	@Test
	public void saveUserTest() {
		UserDetails_Model u = new UserDetails_Model("SK2729", "Skrishna", "skrishna2904@mail.com", "Delhi",
				982368346, "AIIMS");
		when(ur.save(u)).thenReturn(u);
		assertEquals(u, us.saveUser(u));
	}

}
