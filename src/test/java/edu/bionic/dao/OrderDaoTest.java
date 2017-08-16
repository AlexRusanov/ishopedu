package edu.bionic.dao;

import edu.bionic.domain.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.bionic.testdata.OrderFactory.getAllOrders;
import static edu.bionic.testdata.OrderFactory.getNewOrder;

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
    public void save() throws Exception {
        Order newOrder = getNewOrder();
        Order savedOrder = orderDao.save(newOrder);
        newOrder.setId(savedOrder.getId());

        List<Order> expected = getAllOrders();
        expected.add(newOrder);

        List<Order> actual = orderDao.getAll();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

}