package com.cafe.entity;

public class AuthenticationResponse {
	
	private String  token;
	private boolean success;
    private String message;
    private Role role;
    
    public AuthenticationResponse(String token) {
    	this.token=token;
    }
	
	public AuthenticationResponse(String token,Role role) {
		this.token=token;
		
		this.role=role;
		
	}
	
	

	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Role getRole() {
		return role;
	}



//	public void setRole(Role role) {
//		this.role = role;
//	}



	public String getToken() {
		return token;
	}

}
