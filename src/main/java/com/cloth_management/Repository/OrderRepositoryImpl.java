package com.cloth_management.Repository;

import com.cloth_management.Models.Employee;
import com.cloth_management.Models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements  OrderRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addOrder(int user_id) {
        try{
        return jdbcTemplate.update("INSERT INTO orders(user_id) VALUES (?)",user_id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int updateOrder(Order order, int order_id) {
        try{
        return jdbcTemplate.update("UPDATE orders SET emp_id=?,order_status=? WHERE order_id=?",order.getEmp_id(),
                order.getOrder_status(),order_id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Order> findAllOrders() {
        return jdbcTemplate.query("SELECT * FROM orders ", new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public Order getOrderById(int order_id) {
        return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE order_id=? ",
                new BeanPropertyRowMapper<Order>(Order.class),order_id);
    }
}
