package edu.bionic.presentation.controller;

import edu.bionic.domain.Order;
import edu.bionic.service.OrderService;
import edu.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;

@Controller
@SessionAttributes("currentOrder")
@RequestMapping("orders")
public class OrderController {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm");

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String showOrdersForUser(Model model) {

        model.addAttribute("orders", orderService.getAllByAuthUser());
        model.addAttribute("dateTimeFormatter", dateTimeFormatter);

        return "order/order-list";
    }

    @GetMapping("newOrder")
    public String showNewOrderPage(@ModelAttribute("currentOrder") Order basket) {
        userService.getAuthenticatedUser().ifPresent(user -> {
            basket.setName(user.getName());
            basket.setEmail(user.getEmail());
        });

        return "order/newOrder";
    }

    @PostMapping("newOrder")
    public String submitNewOrder(@Valid @ModelAttribute("currentOrder") Order newOrder,
                                 BindingResult bindingResult,
                                 SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "order/newOrder";
        }
        orderService.createNewOrder(newOrder);
        sessionStatus.setComplete();

        return "redirect:/orders/success";
    }

    @PostMapping("newOrder/removeProduct")
    public String removeItemFromOrder(@SessionAttribute("currentOrder") Order currentOrder,
                                      @RequestParam("index") Integer index) {
        orderService.removeProductFromOrder(currentOrder, index);

        return "redirect:/orders/newOrder";
    }

    @GetMapping("success")
    public String successCreatedOrderPage() {
        return "order/orderCreated";
    }
}
