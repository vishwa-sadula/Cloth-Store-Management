package com.cloth_management.Models;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
	
	private int user_id  ;
    private int prod_id ;
    private int prod_quantity ;
    
	public Cart(int user_id, int prod_id, int prod_quantity) {
		super();
		this.user_id = user_id;
		this.prod_id = prod_id;
		this.prod_quantity = prod_quantity;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public int getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}

}
