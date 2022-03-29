package com.albanero.UserDetails_With_Error_Handling.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.albanero.UserDetails_With_Error_Handling.Model.UserDetails_Model;

@Repository
public interface UserDetails_Repository_EH extends MongoRepository<UserDetails_Model, String> {

	Optional<UserDetails_Model> findUserByUsername(String username);

}