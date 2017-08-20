package edu.bionic.dao.jpa;

import edu.bionic.dao.OrderDao;
import edu.bionic.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOrderDao implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }
}
