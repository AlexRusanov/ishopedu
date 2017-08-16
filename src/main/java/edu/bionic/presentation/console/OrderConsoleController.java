package edu.bionic.presentation.console;

import edu.bionic.domain.Order;
import edu.bionic.domain.Product;
import edu.bionic.service.OrderService;
import edu.bionic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderConsoleController {

    private OrderService orderService;
    private ProductService productService;

    private List<Product> basket = new ArrayList<>();

    @Autowired
    public OrderConsoleController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    public void addProductToBasket(int productId) {
        Product selectedProduct = productService.getById(productId);
        basket.add(selectedProduct);
    }

    public void printProductsInBasket() {
        if (!basket.isEmpty()) {
            System.out.println("В корзине:");
            basket.forEach(product -> System.out.println(product.printInfo()));
            System.out.println("");
        }
    }

    public void saveOrder() {
        Order newOrder = new Order(LocalDateTime.now(),
                basket.stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO),
                basket);
        orderService.createNewOrder(newOrder);
        basket = new ArrayList<>();
    }

    public void printPreviousOrders() {
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty()) {
            System.out.println("Нет ниодного заказа");
        } else {
            System.out.println("Предыдущие заказы:");
            orders.forEach(order -> {
                System.out.println(order.getDateTime().toString() + ":");
                order.getProducts().forEach(product -> System.out.println("\t" + product.printInfo()));
                System.out.println("Общая сумма: " + order.getTotalAmount());
            });
            System.out.println();
        }
    }
}
