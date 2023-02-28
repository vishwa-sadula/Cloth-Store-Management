package com.cloth_management.Repository;

import com.cloth_management.Models.Cart;
import com.cloth_management.Models.Order;
import com.cloth_management.Models.Product;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int AddToCart(int prod_id, int user_id) {


        try{
            return jdbcTemplate.update("INSERT INTO cart_details (user_id, prod_id) VALUES(?,?)"
                    ,user_id,prod_id ) ;
        }
        catch (Exception e)
        {
            return jdbcTemplate.update("UPDATE cart_details SET prod_quantity = prod_quantity +? WHERE user_id = ? AND prod_id = ?",
                    1,user_id, prod_id);
        }
    }
    @Override
    public int getQuantity(int prod_id, int user_id)
    {    int quantity = jdbcTemplate.queryForObject("SELECT prod_quantity FROM cart_details where prod_id=? AND user_id=?",
                  Integer.class,prod_id,user_id);
        return quantity;


    }

    @Override
    public int DeleteFromCart(int prod_id, int user_id) {
        try{
        return jdbcTemplate.update("DELETE FROM cart_details WHERE prod_id = ? AND user_id = ?", prod_id, user_id) ;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int UpdateFromCart(int prod_id,int user_id,int quantity) {
        try {
            return jdbcTemplate.update("UPDATE cart_details SET prod_quantity = ? WHERE user_id = ? AND prod_id = ?",
                   quantity, prod_id,user_id);
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public int UpdateItemQuantity( Cart cart, int user_id, int prod_id) {
        try {
            return jdbcTemplate.update("UPDATE cart_details SET prod_quantity=? WHERE user_id=? AND prod_id=?",
                    new Object[]{cart.getProd_quantity(),user_id,prod_id});
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int ClearCart(int user_id) {
        return jdbcTemplate.update("DELETE FROM cart_details WHERE user_id=?", user_id);
    }

    @Override
    public List<Cart> GetCart(int user_id) {

        return jdbcTemplate.query("SELECT * FROM cart_details WHERE user_id=?"
                ,new BeanPropertyRowMapper<Cart>(Cart.class),user_id);

    }

    @Override
    public Cart getCartById(int user_id,int prod_id) {
        return jdbcTemplate.queryForObject("SELECT * FROM cart_details WHERE user_id=? AND prod_id=?",
                new BeanPropertyRowMapper<Cart>(Cart.class),user_id,prod_id);
    }

    @Override
    public int deleteProductFromCarts(int prod_id){
        return jdbcTemplate.update("DELETE FROM cart_details where prod_id=?",prod_id);
    }



}
