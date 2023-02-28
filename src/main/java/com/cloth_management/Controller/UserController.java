package com.cloth_management.Controller;

import com.cloth_management.Models.User;
import com.cloth_management.Repository.UserRepositoryImpl;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private SecurityServices securityServices;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new User());
        model.addAttribute("error_msg", "errorrr!!");
        return "register";
    }
    @RequestMapping(path="/register",method= RequestMethod.POST)
    public String registerUser(User user, Model model){

        int u=userRepositoryImpl.addUser(user);
        if(u==0)
        {
            return "redirect:/register?error=true";
        } else if (u==-1) {
            return "error";

        }
        return "redirect:/login";
    }

}
