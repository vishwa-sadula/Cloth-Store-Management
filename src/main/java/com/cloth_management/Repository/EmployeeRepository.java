package com.cloth_management.Repository;


import com.cloth_management.Models.Employee;

import java.util.List;

public interface EmployeeRepository {
    public int AddEmployee(Employee employee);
    public int DeleteEmployee(int emp_id);
    public int UpdateEmployee(Employee employee, int emp_id);

    public List<Employee> ListEmployees();
    public Employee GetEmployee(int emp_id);
}
