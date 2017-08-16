package edu.bionic.service.impl;

import edu.bionic.dao.ProductDao;
import edu.bionic.domain.Product;
import edu.bionic.domain.ProductSort;
import edu.bionic.service.ProductService;
import edu.bionic.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> getAll(String name, BigDecimal min, BigDecimal max, ProductSort productSort, int offset, int limit) {
        List<Product> result = new ArrayList<>();
        switch (productSort) {
            case NAME_ASC:
            case NAME_DESC:
                result = this.productDao.getAllSortedByName(name, min, max, productSort == ProductSort.NAME_DESC, offset, limit);
                break;
            case PRICE_ASC:
            case PRICE_DESC:
                result = this.productDao.getAllSortedByPrice(name, min, max, productSort == ProductSort.PRICE_DESC, offset, limit);
                break;
        }
        return result;
    }

    @Override
    public int getCount(String name, BigDecimal min, BigDecimal max) {
        return productDao.getCount(name, min, max);
    }

    @Override
    public Product getById(int productId) {
        return productDao.getById(productId).
                orElseThrow(() -> new NotFoundException(String.format("Продукт с id=%d не найден", productId)));
    }

    @Override
    public Product create(Product product) {
        return productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Integer productId) {
        productDao.delete(productId);
    }
}
