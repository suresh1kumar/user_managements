package com.techouts.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techouts.app.entity.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {

	 //User findByEmailIdIgnoreCase(String emailId);

	@Query("SELECT u FROM User u WHERE u.firstName = :firstName")
	public User getUserByUsername(@Param("firstName") String firstName);

	Optional<User> findByEmailIdIgnoreCase(String emailId);
	
	
}