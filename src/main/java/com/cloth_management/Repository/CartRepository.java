package com.cloth_management.Repository;


import com.cloth_management.Models.Cart;

import java.util.List;

public interface CartRepository {

    public int AddToCart(int prod_id, int user_id);

    public int DeleteFromCart(int prod_id, int user_id);
    public int deleteProductFromCarts(int prod_id);

    public int UpdateFromCart(int prod_id, int user_id,int quantity);
    public int UpdateItemQuantity(Cart cart,int user_id,int prod_id);
    public Cart getCartById(int user_id,int prod_id);

    public  int ClearCart(int user_id);

    public List<Cart> GetCart(int user_id);
    public int getQuantity(int prod_id,int user_id);



}
