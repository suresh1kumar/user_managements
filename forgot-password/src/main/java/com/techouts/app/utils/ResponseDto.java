package com.techouts.app.utils;

/**
 * 
 * 
 * don't use this dto for UI response. use this dto inside java8-lamda expression
 *   as we can't assign value to variable out side lamda expression  
 *
 */
public class ResponseDto {
	
	public boolean status=true;
	public String errorCode;
	public String errorMessage;
	
	public ResponseDto() {
		
	}

	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	

}
