package com.cloth_management.Repository;

import com.cloth_management.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int AddEmployee(Employee employee) {
        try{
        return jdbcTemplate.update("INSERT INTO employees (emp_fname,emp_lname,emp_email,emp_gender,emp_join_date)"+
                " VALUES (?,?,?,?,?)",new Object[] {employee.getEmp_fname(),employee.getEmp_lname(),
                employee.getEmp_email(),employee.getEmp_gender(),employee.getEmp_join_date()});
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int DeleteEmployee(int emp_id) {
       try {
           return jdbcTemplate.update("DELETE FROM employees where emp_id=?", emp_id);
       }catch (Exception e)
       {
           return 0;
       }

    }

    @Override
    public int UpdateEmployee(Employee employee, int emp_id) {
        try{
        return jdbcTemplate.update("UPDATE employees SET emp_fname=?,emp_lname=?,emp_email=?,"+
                "emp_gender=?,emp_join_date=?  WHERE emp_id=?", new Object[]{employee.getEmp_fname(),
                employee.getEmp_lname(),employee.getEmp_email(),employee.getEmp_gender(),employee.getEmp_join_date(),emp_id});
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Employee> ListEmployees() {
        return jdbcTemplate.query("SELECT * FROM employees",new BeanPropertyRowMapper<Employee>(Employee.class));
    }

    @Override
    public Employee GetEmployee(int emp_id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employees where emp_id=?",
                new BeanPropertyRowMapper<Employee>(Employee.class),emp_id);
    }

}
