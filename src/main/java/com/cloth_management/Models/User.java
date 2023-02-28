package com.cloth_management.Models;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class User {
	
	private int  user_id  ;
    private String user_fname ;
    private String user_lname ;

	public User(int user_id, String user_fname, String user_lname, String username, String user_email,
				String user_password, String user_type, String city, String street, int pin) {
		this.user_id = user_id;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.username = username;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_type = user_type;
		this.city = city;
		this.street = street;
		this.pin = pin;
	}


    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	private String username ;
    private String user_email ;
	private String user_password ;
	private String user_type ;
	private String city ;
	private String street ;
	private int pin;


}
