package com.cloth_management.Controller;

import com.cloth_management.Repository.UserRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    SecurityServices securityServices;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminHome()
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }


        return "admin";
    }

    @GetMapping("/error")
    public String error()
    {
        return "error";
    }

    @GetMapping("/about")
    public String about()
    {
        return "about";
    }

//    @GetMapping("/profile")
//    public String profile(Model model)
//    {
//        int user_id=securityServices.findLoggedInUser().getUser_id();
//        User us= userRepository.getUser(user_id);
//        model.addAttribute("user", us);
//        List<String> phno=userRepository.getPhones(user_id);
//        model.addAttribute("phones",phno);
//        model.addAttribute("userphoneno",new UserPhoneNo());
//
//
//        return "profile";
//    }
////    @PostMapping("/profile/add")
//    @PostMapping("/add_phone")
//    public String addPhone(@ModelAttribute("userphoneno") UserPhoneNo userPhoneNo, Model model)
//    {    System.out.println(5);
//        int user_id=securityServices.findLoggedInUser().getUser_id();
//        userRepository.addPhone(userPhoneNo);
//        return "redirect:/profile";
//    }
//
//    @PostMapping("/profile")
//    public String profile(@ModelAttribute("user") User user, Model model )
//    {
//        System.out.println(7);
//        int user_id=securityServices.findLoggedInUser().getUser_id();
//        userRepository.updateUser(userRepository.getUser(user_id));
//        return "redirect:/profile";
//    }
//
//

}
