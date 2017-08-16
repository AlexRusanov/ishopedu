package edu.bionic.dao.mock;

import edu.bionic.dao.OrderDao;
import edu.bionic.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMock implements OrderDao {

    private List<Order> orderStorage;

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orderStorage);
    }

    @Override
    public Order save(Order order) {
        orderStorage.add(order);
        return order;
    }

    public void initOrderStorage() {
        orderStorage = new ArrayList<>();
    }
}
