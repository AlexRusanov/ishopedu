package edu.bionic.dao.jpa;

import edu.bionic.dao.ProductDao;
import edu.bionic.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class JpaProductDao implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public List<Product> getAllSortedByName(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit) {
        return null;
    }

    @Override
    public List<Product> getAllSortedByPrice(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit) {
        return null;
    }

    @Override
    public int getCount(String name, BigDecimal min, BigDecimal max) {
        return 0;
    }

    @Override
    public Optional<Product> getById(int productId) {
        Product product = entityManager.find(Product.class, productId);
        return Optional.ofNullable(product);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
            return product;
        } else {
           return entityManager.merge(product);
        }
    }

    @Override
    @Transactional
    public boolean delete(int productId) {
        Query query = entityManager.createQuery("DELETE FROM Product p WHERE p.id = :product_id");
        query.setParameter("product_id", productId);

        return query.executeUpdate() != 0;
    }
}
