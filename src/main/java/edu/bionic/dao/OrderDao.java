package edu.bionic.dao;

import edu.bionic.domain.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getAll();

    Order save(Order order);
}
