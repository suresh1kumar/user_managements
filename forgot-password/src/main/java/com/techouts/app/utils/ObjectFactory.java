package com.techouts.app.utils;

import org.springframework.stereotype.Component;

@Component
public class ObjectFactory implements IppeFactory {

	
	public ResponseDto getResponseDto() {
		
		return new ResponseDto();
	}

}
