package org.example.orderserver.dao;

import org.example.orderserver.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    int insertOrder(Order order);
    int updateOrder(Order order);
    int deleteOrder(int id);

}
