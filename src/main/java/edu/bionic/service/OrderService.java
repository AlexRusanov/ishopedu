package edu.bionic.service;

import edu.bionic.domain.Order;
import edu.bionic.domain.Product;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    void createNewOrder(Order order);

    void addProductToOrder(Order order, Integer productId);

    void removeProductFromOrder(Order order, int indexOfProduct);
}
