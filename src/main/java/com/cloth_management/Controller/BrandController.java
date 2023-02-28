package com.cloth_management.Controller;

import com.cloth_management.Models.Brand;
import com.cloth_management.Models.Category;
import com.cloth_management.Repository.BrandRepository;
import com.cloth_management.Repository.CategoryRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller
public class BrandController {

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/admin/Brands")
    public String getBrand(Model model){
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Brand>  br=brandRepository.ListBrand();
        model.addAttribute("brands",br);
        return "Brands";

    }


    @GetMapping("/add_brand")

    public String addBrand( Model model)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

//        List<Brand> br=brandRepository.ListBrand();
//          model.addAttribute("brands",br);
          model.addAttribute("brand",new Brand());
        return "add_brand";
    }


    @PostMapping("/add_brand")
    public String addBrand(@ModelAttribute("brand") Brand brand, Model model, RedirectAttributes attributes)
    {
       int b= brandRepository.AddBrand(brand);
       if(b==0)  return "error";
        return "redirect:/admin/Brands";
    }


    @GetMapping("/admin/brand/delete/{brand_id}")
    public String deleteBrandById(@PathVariable int brand_id)
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        int br= brandRepository.DeleteBrand(brand_id);
        if( br==0 ) return "redirect:/admin/Brands?error=true";


        return "redirect:/admin/Brands";
    }


    @GetMapping("/update_brand/{brand_id}")
    public String updateBrand( Model model, @PathVariable int brand_id)
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        List<Brand> br=brandRepository.ListBrand();
        model.addAttribute("brands",br);
        Brand brand= brandRepository.GetBrand(brand_id);
        model.addAttribute("brand",brand);
        return "update_brand";
    }


    @PostMapping("/update_brand/{brand_id}")
    public String updateBrand(@ModelAttribute("brand") Brand brand, Model model, @PathVariable int brand_id )
    {
        int br= brandRepository.UpdateBrand(brand, brand_id);
        if(br==0)  return "error";
        return "redirect:/admin/Brands";
    }

}
