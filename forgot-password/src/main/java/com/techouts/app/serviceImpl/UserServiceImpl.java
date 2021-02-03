package com.techouts.app.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.techouts.app.entity.ConfirmationToken;
import com.techouts.app.entity.User;
import com.techouts.app.repository.ConfirmationTokenRepository;
import com.techouts.app.repository.UserRepository;
import com.techouts.app.service.EmailSenderService;
import com.techouts.app.service.UserService;
import com.techouts.app.utils.DobConstants;
import com.techouts.app.utils.ObjectFactory;
import com.techouts.app.utils.ResponseDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ObjectFactory objectFactoy;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	//@Override
	public ResponseDto forgotUserPasswordSendMail(User user)  {
		
		ResponseDto internalDto=objectFactoy.getResponseDto();
		Optional<User> existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
		if (existingUser.isPresent()  ) {
			internalDto.setStatus(false);
			internalDto.setErrorMessage(DobConstants.EMAIL_DOES_NOT_EXIST); 
		}else{
			Optional.ofNullable(existingUser).ifPresent(x -> {
				ConfirmationToken confirmationToken = new ConfirmationToken(existingUser.get());
			});		
			ConfirmationToken confirmationToken = new ConfirmationToken(existingUser.get());
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.get().getEmailId());
			mailMessage.setSubject(DobConstants.COMPLATE_PASSWORD_RESET);
			mailMessage.setText(DobConstants.COMPLETE_PASSWORD_RESET_PROCESS+"http://localhost:2021/confirm-reset?token="+confirmationToken.getConfirmationToken());
			emailSenderService.sendEmail(mailMessage);
			
		}
		return  internalDto;     
	}
	
	/*public ResponseDto validateResetPasswordToken(String confirmationToken) {
		ResponseDto internalDto=objectFactoy.getResponseDto();
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if(token != null) {
			Optional<User> user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.addObject("user", user);
			modelAndView.addObject("emailId", user.getEmailId());
			modelAndView.setViewName("resetPassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		return  internalDto; 
	}*/
}
