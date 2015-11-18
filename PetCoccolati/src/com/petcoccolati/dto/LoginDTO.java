package com.petcoccolati.dto;



public class LoginDTO {
	
	private String Email;
	private String Password;
	
	public LoginDTO(String Email, String Password){
		this.setEmail(Email);
		this.setPassword(Password);
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	

}
