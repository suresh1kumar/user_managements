package com.techouts.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.app.entity.User;
import com.techouts.app.repository.ConfirmationTokenRepository;
import com.techouts.app.repository.UserRepository;
import com.techouts.app.service.EmailSenderService;
import com.techouts.app.serviceImpl.UserServiceImpl;
import com.techouts.app.utils.ApiResponse;
import com.techouts.app.utils.ResponseDto;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;

	// https://stackabuse.com/password-encoding-with-spring-security/
	// to encode our password
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(consumes = "application/json", produces = "application/json", path = "/forgot-password1")  
    public ResponseEntity<?> forgotUserPasswordSendMails( @RequestBody User user) 
    {
		System.out.println("user ---------:"+user.toString());
		ResponseDto statusDto=userServiceImpl.forgotUserPasswordSendMail(user);
		System.out.println("statusDto:"+statusDto.toString());
		return ResponseEntity.status(statusDto.getStatus()?HttpStatus.OK:HttpStatus.UNPROCESSABLE_ENTITY).
				body(statusDto.getStatus()?
				new ApiResponse(true, "Request to reset password received. Check your inbox for the reset link."):
					new ApiResponse(statusDto.getStatus(), statusDto.getErrorMessage()));
    }
		

}
