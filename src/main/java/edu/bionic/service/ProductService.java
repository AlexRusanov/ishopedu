package edu.bionic.service;

import edu.bionic.domain.Product;
import edu.bionic.domain.ProductSort;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> getAll();

    List<Product> getAll(String name, BigDecimal min, BigDecimal max, ProductSort productSort, int offset, int limit);

    int getCount(String name, BigDecimal min, BigDecimal max);

    Product getById(int productId);

    Product create(Product product);

    void update(Product product);

    void delete(Integer productId);
}
