package org.example.orderserver.dao;

import org.example.orderserver.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> new Order(
            rs.getInt("id"),
            rs.getString("customer_name"),
            rs.getString("phone_number"),
            rs.getDouble("total_price"),
            rs.getString("status")
    );

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query("SELECT * FROM `Order`", orderRowMapper);
    }

    @Override
    public Order getOrderById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM `Order` WHERE id = ?", orderRowMapper, id);
    }

    @Override
    public int insertOrder(Order order) {
        return jdbcTemplate.update(
                "INSERT INTO `Order`(customer_name, phone_number, total_price, status) VALUES (?,?,?,?)",
                order.getCustomerName(), order.getPhoneNumber(),
                order.getTotalPrice(), order.getStatus());
    }

    @Override
    public int updateOrder(Order order) {
        return jdbcTemplate.update(
                "UPDATE `Order` SET customer_name=?, phone_number=?, total_price=?, status=? WHERE id=?",
                order.getCustomerName(), order.getPhoneNumber(),
                order.getTotalPrice(), order.getStatus(), order.getId());
    }

    @Override
    public int deleteOrder(int id) {
        return jdbcTemplate.update("DELETE FROM `Order` WHERE id=?", id);
    }
}