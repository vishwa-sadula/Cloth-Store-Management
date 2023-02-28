package com.cloth_management.Models;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
	
	private int emp_id ;
    private String emp_fname ;
    private String emp_lname ;
    private String emp_email ;
    private String emp_gender ;
    private Date emp_join_date ;
    
	public Employee(int emp_id, String emp_fname, String emp_lname, String emp_email, String emp_gender,
			Date emp_join_date) {
		super();
		this.emp_id = emp_id;
		this.emp_fname = emp_fname;
		this.emp_lname = emp_lname;
		this.emp_email = emp_email;
		this.emp_gender = emp_gender;
		this.emp_join_date = emp_join_date;
	}
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_fname() {
		return emp_fname;
	}
	public void setEmp_fname(String emp_fname) {
		this.emp_fname = emp_fname;
	}
	public String getEmp_lname() {
		return emp_lname;
	}
	public void setEmp_lname(String emp_lname) {
		this.emp_lname = emp_lname;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	public Date getEmp_join_date() {
		return emp_join_date;
	}
	public void setEmp_join_date(Date emp_join_date) {
		this.emp_join_date = emp_join_date;
	}

}
