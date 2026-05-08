package org.example.orderserver.controller;

import org.example.orderserver.dao.OrderDAO;
import org.example.orderserver.model.ApiResponse;
import org.example.orderserver.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping
    public ApiResponse<List<Order>> getAll() {
        List<Order> orders = orderDAO.getAllOrders();
        return new ApiResponse<>(true, "Success", orders);
    }

    @GetMapping("/{id}")
    public ApiResponse<Order> getById(@PathVariable int id) {
        Order order = orderDAO.getOrderById(id);
        return new ApiResponse<>(true, "Success", order);
    }

    @PostMapping
    public ApiResponse<Void> insert(@RequestBody Order order) {
        orderDAO.insertOrder(order);
        return new ApiResponse<>(true, "Inserted successfully", null);
    }

    @PutMapping
    public ApiResponse<Void> update(@RequestBody Order order) {
        orderDAO.updateOrder(order);
        return new ApiResponse<>(true, "Updated successfully", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        orderDAO.deleteOrder(id);
        return new ApiResponse<>(true, "Deleted successfully", null);
    }
}