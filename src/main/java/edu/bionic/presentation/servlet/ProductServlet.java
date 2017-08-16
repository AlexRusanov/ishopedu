package edu.bionic.presentation.servlet;

import edu.bionic.domain.Product;
import edu.bionic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

//        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
//        productService = context.getBean("productServiceImpl",  ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        productService.getAll().forEach(product -> {
//            try {
//                resp.getWriter().println(product.printInfo());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/product-list.jsp").forward(req, resp);
    }
}
