package com.cloth_management.Controller;

import com.cloth_management.Models.Brand;
import com.cloth_management.Models.Category;
import com.cloth_management.Repository.CategoryRepository;
import com.cloth_management.Repository.UserRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/admin/category")
    public String  getCategories(Model model){
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Category>  cat=categoryRepository.ListCategories();
        model.addAttribute("categories",cat);
        return "category";

    }

    @GetMapping("/category/{cat_id}")
    public Category getCategoryById(@PathVariable int cat_id){

        return categoryRepository.GetCategory(cat_id);
    }

    @GetMapping("/add_category")
    public String addCategory( Model model)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        List<Category> cat=categoryRepository.ListCategories();
        model.addAttribute("categories",cat);
        model.addAttribute("category",new Category());
        return "add_category";
    }


    @PostMapping("/add_category")
    public String addCategory(@ModelAttribute("category") Category category, Model model, RedirectAttributes attributes)
    {
        int c= categoryRepository.AddCategory(category);
        if(c==0)  return "error";
        return "redirect:/admin/category";
    }


    @GetMapping("/admin/category/delete/{cat_id}")
    public String deleteCategoryById(@PathVariable int cat_id)
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        int ct= categoryRepository.DeleteCategory(cat_id);
        if( ct==0 ) return "redirect:/admin/category?error=true";
        return "redirect:/admin/category";
    }



    @GetMapping("/update_category/{cat_id}")
    public String updateCategory( Model model, @PathVariable int cat_id)
    {
        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        List<Category> cat=categoryRepository.ListCategories();
        model.addAttribute("categories",cat);
        Category category= categoryRepository.GetCategory(cat_id);
        model.addAttribute("category",category);
        return "update_category";
    }


    @PostMapping("/update_category/{cat_id}")
    public String updateCategory(@ModelAttribute("category") Category category, Model model, @PathVariable int cat_id )
    {
        int c= categoryRepository.UpdateCategory(category, cat_id);
        if(c==0)  return "error";
        return "redirect:/admin/category";
    }


}
