package edu.bionic.dao.mock;

import edu.bionic.dao.ProductDao;
import edu.bionic.domain.Color;
import edu.bionic.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoMock implements ProductDao {

    private List<Product> productStorage;


    @Override
    public List<Product> getAll() {
        return new ArrayList<>(productStorage);
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
        return productStorage.size();
    }

    @Override
    public Optional<Product> getById(int productId) {
        return productStorage.stream().filter(product -> product.getId() == productId).findAny();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public boolean delete(int productId) {
        return false;
    }

    public void initProductStorage() {
        productStorage = new ArrayList<>();

        productStorage.add(new Product(
                1,
                "iPhone 7",
                BigDecimal.valueOf(700),
                Color.BLACK,
                64,
                "4,7 inch",
                null
        ));
        productStorage.add(new Product(
                2,
                "iPhone 7",
                BigDecimal.valueOf(700),
                Color.WHITE,
                128,
                "4,7 inch",
                null
        ));
        productStorage.add(new Product(
                3,
                "iPhone 7",
                BigDecimal.valueOf(700),
                Color.GRAY,
                256,
                "4,7 inch",
                null
        ));
        productStorage.add(new Product(
                4,
                "iPhone 7 Plus",
                BigDecimal.valueOf(800),
                Color.BLACK,
                256,
                "5,5 inch",
                null
        ));
        productStorage.add(new Product(
                5,
                "iPhone 7 Plus",
                BigDecimal.valueOf(800),
                Color.GRAY,
                64,
                "5,5 inch",
                null
        ));
        productStorage.add(new Product(
                6,
                "iPhone 7 Plus",
                BigDecimal.valueOf(800),
                Color.WHITE,
                128,
                "5,5 inch",
                null
        ));
    }
}
