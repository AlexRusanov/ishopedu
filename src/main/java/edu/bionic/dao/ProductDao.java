package edu.bionic.dao;

import edu.bionic.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAll();

    List<Product> getAllSortedByName(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit);

    List<Product> getAllSortedByPrice(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit);

    int getCount(String name, BigDecimal min, BigDecimal max);

    Optional<Product> getById(int productId);

    Product save(Product product);

    boolean delete(int productId);
}
