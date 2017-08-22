package edu.bionic.presentation.controller;

import edu.bionic.domain.Comment;
import edu.bionic.domain.Order;
import edu.bionic.dto.ProductSort;
import edu.bionic.service.CommentService;
import edu.bionic.service.OrderService;
import edu.bionic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("products")
@SessionAttributes("currentOrder")
public class ProductController {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm");
    private final int PAGE_SIZE = 5;

    private ProductService productService;
    private CommentService commentService;
    private OrderService orderService;

    @Autowired
    public ProductController(ProductService productService, CommentService commentService, OrderService orderService) {
        this.productService = productService;
        this.commentService = commentService;
        this.orderService = orderService;
    }

    @GetMapping
    public String showProducts(Model model,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "min", required = false) BigDecimal min,
                               @RequestParam(value = "max", required = false) BigDecimal max,
                               @RequestParam(value = "sort", required = false) ProductSort sort,
                               @RequestParam(value = "page", defaultValue = "1") int page) {
        int offset = (page - 1) * PAGE_SIZE;
        int limit = PAGE_SIZE;
        if (sort == null) sort = ProductSort.NAME_ASC;
        model.addAttribute("products", productService.getAll(name, min, max, sort, offset, limit));
        model.addAttribute("productCount", productService.getCount(name, min, max));
        model.addAttribute("pageSize", PAGE_SIZE);
        return "product/product-list";
    }

    @GetMapping("{productId}")
    public String showProduct(Model model,
                              @PathVariable("productId") Integer productId) {
        model.addAttribute(productService.getById(productId));
        model.addAttribute("comments", commentService.getByProduct(productId));
        if (!model.containsAttribute("newComment")) {
            model.addAttribute("newComment", new Comment());
        }
        model.addAttribute("dateTimeFormatter", dateTimeFormatter);

        return "product/product";
    }

    @PostMapping("{productId}/addToBasket")
    public String addToBasket(@PathVariable("productId") Integer productId,
                              @SessionAttribute("currentOrder") Order currentOrder) {

        orderService.addProductToOrder(currentOrder, productId);

        return "redirect:/products/" + productId;
    }

}
