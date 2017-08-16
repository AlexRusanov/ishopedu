package edu.bionic.config;

import edu.bionic.dao.CommentDao;
import edu.bionic.dao.OrderDao;
import edu.bionic.dao.ProductDao;
import edu.bionic.dao.mock.CommentDaoMock;
import edu.bionic.dao.mock.OrderDaoMock;
import edu.bionic.dao.mock.ProductDaoMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"edu.bionic.service", "edu.bionic.presentation"})
public class AppConfig {

    @Bean
    public OrderDao orderDaoMock() {
        OrderDaoMock orderDaoMock = new OrderDaoMock();
        orderDaoMock.initOrderStorage();
        return orderDaoMock;
    }

    @Bean
    public ProductDao productDaoMock() {
        ProductDaoMock productDaoMock = new ProductDaoMock();
        productDaoMock.initProductStorage();;
        return productDaoMock;
    }

    @Bean
    public CommentDao commentDaoMock() {
        CommentDaoMock commentDaoMock = new CommentDaoMock();
        commentDaoMock.initCommentStorage();;
        return commentDaoMock;
    }
}
