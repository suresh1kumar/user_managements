package com.techouts.app.service;

import com.techouts.app.entity.User;
import com.techouts.app.utils.ResponseDto;

public interface UserService {

	public ResponseDto forgotUserPasswordSendMail(User user);
}
