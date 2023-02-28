package com.cloth_management.Controller;

import com.cloth_management.Models.Category;
import com.cloth_management.Models.Employee;
import com.cloth_management.Models.Order;
import com.cloth_management.Models.Product;
import com.cloth_management.Repository.EmployeeRepository;
import com.cloth_management.Repository.OrderRepository;
import com.cloth_management.Repository.UserRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrdersController {

    @Autowired
    private SecurityServices securityServices;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/Orders")
    public String getOrders(Model model){
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Order> ord = orderRepository.findAllOrders();
        Map<Object, Object> employee= new HashMap<Object,Object>();
        Map<Object, Object> users= new HashMap<Object,Object>();
        for(Order eachOrder : ord) {
            String emp_name=employeeRepository.GetEmployee(eachOrder.getEmp_id()).getEmp_fname();
            employee.put(eachOrder,emp_name);
            String user_name=userRepository.getUser(eachOrder.getUser_id()).getUser_fname();
            users.put(eachOrder,user_name);
        }
        model.addAttribute("employee",employee);
        model.addAttribute("users", users);
        model.addAttribute("orders", ord);
        return "orders";
    }

    @GetMapping("/update_order/{order_id}")
    public String updateOrder(Model model, @PathVariable int order_id)
    {    String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Employee> emp=employeeRepository.ListEmployees();
        model.addAttribute("emps",emp);
        Order order= orderRepository.getOrderById(order_id);
        model.addAttribute("orders",order);
        return "update_order";
    }

    @PostMapping("/update_order/{order_id}")
    public String updateOrder(@ModelAttribute("orders") Order order, Model model, @PathVariable int order_id)
    {
        int o= orderRepository.updateOrder(order, order_id);
        if(o==0)  return "error";
        return "redirect:/admin/Orders";
    }

}
