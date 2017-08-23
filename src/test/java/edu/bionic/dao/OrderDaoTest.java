package edu.bionic.dao;

import edu.bionic.domain.Order;
import edu.bionic.testdata.OrderFactory;
import edu.bionic.testdata.UserFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.bionic.testdata.OrderFactory.getAllOrders;
import static edu.bionic.testdata.OrderFactory.getAllOrdersForUser;
import static edu.bionic.testdata.OrderFactory.getNewOrderForUser;

public class OrderDaoTest extends BaseDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void getAll() throws Exception {
        List<Order> expected = getAllOrders();
        List<Order> actual = orderDao.getAll();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllByUser() throws Exception {
        List<Order> expected = getAllOrdersForUser();
        List<Order> actual = orderDao.getAllByUser(UserFactory.getUser().getId());

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void save() throws Exception {
        Order newOrder = getNewOrderForUser();
        Order savedOrder = orderDao.save(newOrder);
        newOrder.setId(savedOrder.getId());

        List<Order> expected = OrderFactory.getAllOrdersForUser();
        expected.add(newOrder);

        List<Order> actual = orderDao.getAllByUser(UserFactory.getUser().getId());

        Assert.assertEquals(expected.toString(), actual.toString());
    }

}