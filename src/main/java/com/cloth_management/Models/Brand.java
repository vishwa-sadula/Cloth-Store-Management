package com.cloth_management.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Brand {
	
	private int brand_id ;
	private String brand_name ;
    private String brand_email ;
    
	public Brand(int brand_id, String brand_name, String brand_email) {
		super();
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.brand_email = brand_email;
	}
	
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBrand_email() {
		return brand_email;
	}
	public void setBrand_email(String brand_email) {
		this.brand_email = brand_email;
	}
	

}
