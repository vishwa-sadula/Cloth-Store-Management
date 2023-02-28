package com.cloth_management.Controller;

import com.cloth_management.FileUploadUtil;
import com.cloth_management.Models.Brand;
import com.cloth_management.Models.Category;
import com.cloth_management.Models.Product;
import com.cloth_management.Repository.BrandRepository;
import com.cloth_management.Repository.CartRepository;
import com.cloth_management.Repository.CategoryRepository;
import com.cloth_management.Repository.ProductRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/admin/Products")
    public String getProduct(Model model){

        String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Product> pr = productRepository.ListAllProducts();
        Map<Object, Object> products= new HashMap<Object,Object>();
        Map<Object, Object> brands= new HashMap<Object,Object>();
        for(Product eachProduct : pr) {
            String cat_name=categoryRepository.GetCategory(eachProduct.getCat_id()).getCat_name();
            products.put(eachProduct,cat_name);
            String brand_name=brandRepository.GetBrand(eachProduct.getBrand_id()).getBrand_name();
            brands.put(eachProduct,brand_name);
        }
        model.addAttribute("category",products);
        model.addAttribute("product", pr);
        model.addAttribute("brand",brands);
        return "Products";
    }

    @GetMapping("/shop/view_product/{prod_id}")
    public String getViewProduct(@PathVariable(value = "prod_id") int prod_id, Model model){

        Product pr= productRepository.GetProductById(prod_id);
        model.addAttribute("product", pr);
        String cat= categoryRepository.GetCategory(pr.getCat_id()).getCat_name();
        model.addAttribute("cat_name", cat);
        String br= brandRepository.GetBrand(pr.getBrand_id()).getBrand_name();
        model.addAttribute("brand_name", br);
        return "view_product";
    }

    @GetMapping("/add_products")
    public String addProd( Model model)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        List<Product> pr=productRepository.ListProducts();
        List<Category> cat=categoryRepository.ListCategories();
        List<Brand>   br=brandRepository.ListBrand();
        model.addAttribute("categories",cat);
        model.addAttribute("brands",br);
        model.addAttribute("products",pr);
        model.addAttribute("product",new Product());
        return "add_products";
    }


    @PostMapping("/add_products")
    public String addProductPOSTHandler(@ModelAttribute("product") Product product,
                                        @RequestParam("productImage") MultipartFile multipartFile, Model model) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImage_path(fileName);

        int pr=productRepository.AddProduct(product);
        if(pr==0)  return "error";
        String uploadDir = "productImages/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/admin/Products";
    }

    @GetMapping("/admin/product/delete/{prod_id}")
    public String deleteProductById(@PathVariable int prod_id)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }

        int pr= productRepository.DeleteProduct(prod_id);
        cartRepository.deleteProductFromCarts(prod_id);

        return "redirect:/admin/Products";
    }

    @GetMapping("/update_product/{prod_id}")
    public String updateProduct( Model model, @PathVariable int prod_id)
    {   String user_type=securityServices.findLoggedInUser().getUser_type();
        if(user_type.equals("USER"))
        {      return "login";  }
        List<Product> pr=productRepository.ListProducts();
        List<Category> cat=categoryRepository.ListCategories();
        List<Brand>   br=brandRepository.ListBrand();
        model.addAttribute("categories",cat);
        model.addAttribute("brands",br);
        model.addAttribute("products",pr);
        Product product= productRepository.GetProductById(prod_id);
        model.addAttribute("product",product);
        return "update_product";
    }


    @PostMapping("/update_product/{prod_id}")
    public String updateProductPOSTHandler(@ModelAttribute("product") Product product, @PathVariable int prod_id,
                                        @RequestParam("productImage") MultipartFile multipartFile, Model model) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImage_path(fileName);

        int pr=productRepository.UpdateProduct(product, prod_id);
        if(pr==0)  return "error";
        String uploadDir = "productImages/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/admin/Products";
    }


}
