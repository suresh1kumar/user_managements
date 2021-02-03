package com.techouts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techouts.app.entity.User;

@Repository
public interface UserRepositorys extends JpaRepository<User, String>{

	User findByEmailIdIgnoreCase(String emailId);
}
