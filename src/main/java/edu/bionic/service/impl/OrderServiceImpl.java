package edu.bionic.service.impl;

import edu.bionic.dao.OrderDao;
import edu.bionic.domain.Order;
import edu.bionic.domain.Product;
import edu.bionic.service.OrderService;
import edu.bionic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ProductService productService) {
        this.orderDao = orderDao;
        this.productService = productService;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void createNewOrder(Order order) {
        order.setDateTime(LocalDateTime.now());
        orderDao.save(order);
    }

    @Override
    public void addProductToOrder(Order order, Integer productId) {
        Product newProduct = productService.getById(productId);
        order.addProduct(newProduct);
        order.setTotalAmount(
                order.getProducts()
                        .stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
        );
    }

    @Override
    public void removeProductFromOrder(Order order, int indexOfProduct) {
        order.removeProduct(indexOfProduct);
        order.setTotalAmount(
                order.getProducts()
                        .stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
        );
    }
}
