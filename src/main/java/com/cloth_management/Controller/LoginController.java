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
public class LoginController {
    @Autowired
    private SecurityServices securityServices;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "login";

    }
    @PostMapping("/login")
    public String login(@ModelAttribute("username") String username, @ModelAttribute("user_password") String user_password,
                        BindingResult result, WebRequest request, Model model, RedirectAttributes attributes)
    {
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome( Model model){
        User user = securityServices.findLoggedInUser();
       model.addAttribute(user);
        String[] roles = user.getUser_type().split(" ");
        for(int i=0;i< roles.length;i++){
            if(roles[i].equals("ADMIN"))return "redirect:/admin";
        }
       // model.addAttribute("username",securityService.findLoggedInUsername());
        return "redirect:/shop";
//        if(userType.equals("USER")) {
//            System.out.println(2);
//            return "redirect:/admin";
//        }
//        else {
//            System.out.println(3);
//            return "redirect:/shop";
//        }

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

}
