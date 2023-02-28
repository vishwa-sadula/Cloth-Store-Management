package com.cloth_management.Models;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
	
	private int order_id ;
    private int user_id ;
    private int emp_id ;
    private String order_status ;
    
    public Order(int order_id, int user_id, int emp_id, String order_status) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.emp_id = emp_id;
		this.order_status = order_status;
	}
    
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	

}
