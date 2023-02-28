package com.cloth_management.Controller;

import com.cloth_management.Models.Cart;
import com.cloth_management.Models.Category;
import com.cloth_management.Models.Product;
import com.cloth_management.Repository.CartRepository;
import com.cloth_management.Repository.OrderRepository;
import com.cloth_management.Repository.ProductRepository;
import com.cloth_management.service.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private SecurityServices securityServices;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/cart")
    public String getCart( Model model){
        int user_id=securityServices.findLoggedInUser().getUser_id();
        List<Cart> cart = cartRepository.GetCart(user_id);
        Map<Object, Object> cartProducts = new HashMap<Object,Object>();
        int cart_total = 0;
        for (Cart cartProduct : cart) {
            Product product = productRepository.GetProductById(cartProduct.getProd_id());
            cartProducts.put(cartProduct, product);
            cart_total += product.getPrice() * cartProduct.getProd_quantity();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cart_total",cart_total);
        return "cart";
    }
    @GetMapping("/addToCart/{prod_id}")
    public String addToCart(@PathVariable int prod_id )
    {
        int user_id=securityServices.findLoggedInUser().getUser_id();
        int ct= cartRepository.AddToCart(prod_id,user_id);
        return "redirect:/cart";

    }

    @GetMapping("/cart/removeItem/{prod_id}")
    public String deleteProductById(@PathVariable int prod_id)
    {   int user_id=securityServices.findLoggedInUser().getUser_id();
        int pr= cartRepository.DeleteFromCart(prod_id,user_id);
        return "redirect:/cart";
    }
    @GetMapping("/cart/updateItem/{prod_id}")
    public String updateItemById(Model model,@PathVariable int prod_id)
    {

        int user_id=securityServices.findLoggedInUser().getUser_id();
        Cart cart= cartRepository.getCartById(user_id,prod_id);
        model.addAttribute("cart",cart);
        return "update_quantity";
    }
    @PostMapping("/cart/updateItem/{prod_id}")
    public String updateItemById(@ModelAttribute("cart") Cart cart, Model model, @PathVariable int prod_id )
    {
        int user_id=securityServices.findLoggedInUser().getUser_id();
        int c= cartRepository.UpdateItemQuantity(cart,user_id,prod_id);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String checkout()
    {   int user_id=securityServices.findLoggedInUser().getUser_id();


        List<Cart> productsInCart = cartRepository.GetCart(user_id);

        int no_of_items=0;

        for (Cart cartProduct : productsInCart) {

            int prod_quantity=productRepository.GetProductById(cartProduct.getProd_id()).getQuantity()  ;
            int cart_quantity=cartProduct.getProd_quantity()  ;

            if(cart_quantity>prod_quantity) {
                return "redirect:/cart?error=true";
            }

            no_of_items=no_of_items+1;
        }

        if(no_of_items==0)
        {
            return "redirect:/cart";
        }

        int o=orderRepository.addOrder(user_id);
        if(o==0)  return "error";

        for (Cart cartProduct : productsInCart) {
            productRepository.ReduceProductQuantity(cartProduct.getProd_quantity(),cartProduct.getProd_id());
        }
        int c= cartRepository.ClearCart(user_id);
        return "redirect:/cart?success=true";
    }
}
