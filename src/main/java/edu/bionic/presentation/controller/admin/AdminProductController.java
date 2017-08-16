package edu.bionic.presentation.controller.admin;

import edu.bionic.domain.Product;
import edu.bionic.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/products")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("products",  productService.getAll());
        return "admin/product-list";
    }

    @GetMapping("{productId}")
    public String editProductPage(@PathVariable("productId") Integer productId, Model model) {
        model.addAttribute("product", productService.getById(productId));
        return "admin/product-edit";
    }

    @PostMapping("{productId}")
    public String editProduct(@Valid @ModelAttribute Product product,
                              BindingResult bindingResult,
                              @PathVariable("productId") Integer productId,
                              RedirectAttributes redirectAttributes) {
        if  (bindingResult.hasErrors()) {
            return "admin/product-edit";
        }
        product.setId(productId);
        productService.update(product);
        redirectAttributes.addFlashAttribute("updateIsSuccessful", true);
        return "redirect:/admin/products/" + productId;
    }

    @GetMapping("new")
    public String newProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isNew", true);
        return "admin/product-edit";
    }

    @PostMapping("new")
    public String editProduct(@Valid @ModelAttribute Product product,
                              BindingResult bindingResult,
                              Model model) {
        if  (bindingResult.hasErrors()) {
            model.addAttribute("isNew", true);
            return "admin/product-edit";
        }
        Product createdProduct = productService.create(product);
        return "redirect:/admin/products/" + createdProduct.getId();
    }

    @PostMapping("{productId}/delete")
    public String deleteProduct(@PathVariable("productId") Integer productId) {
        productService.delete(productId);
        return "redirect:/admin/products/";
    }
}
