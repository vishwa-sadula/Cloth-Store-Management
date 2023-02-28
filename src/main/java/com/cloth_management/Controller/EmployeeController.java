package com.cloth_management.Controller;


import com.cloth_management.Models.Category;
import com.cloth_management.Models.Employee;
import com.cloth_management.Repository.EmployeeRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SecurityServices securityServices;

    @GetMapping("/admin/Employee")
    public String getEmployee(Model model)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        List<Employee> emp=employeeRepository.ListEmployees();
        model.addAttribute("Employees",emp);
        return "employee";
    }

    @GetMapping("/add_employee")
    public String addEmployee( Model model)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Employee> emp=employeeRepository.ListEmployees();
        model.addAttribute("employees",emp);
        model.addAttribute("employee",new Employee());
        return "add_employee";
    }


    @PostMapping("/add_employee")
    public String addEmployee(@ModelAttribute("employee") Employee employee, Model model, RedirectAttributes attributes)
    {
        int e= employeeRepository.AddEmployee(employee);
        if(e==0)  return "error";
        return "redirect:/admin/Employee";
    }
     @GetMapping("/admin/Employee/delete/{emp_id}")
     public String deleteEmployeeById(@PathVariable int emp_id)
     {
         String user_type=securityServices.findLoggedInUser().getUser_type();
         if(user_type.equals("USER"))
         {      return "login";  }

         int emp=employeeRepository.DeleteEmployee(emp_id);
         if( emp==0 ) return "redirect:/admin/Employee?error=true";

         return "redirect:/admin/Employee";
     }

    @GetMapping("/update_employee/{emp_id}")
    public String updateEmployee( Model model, @PathVariable int emp_id)
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        Employee employee= employeeRepository.GetEmployee(emp_id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }


    @PostMapping("/update_employee/{emp_id}")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, Model model,  @PathVariable int emp_id)
    {
        int e= employeeRepository.UpdateEmployee(employee,emp_id);
        if(e==0)  return "error";
        return "redirect:/admin/Employee";
    }

}
