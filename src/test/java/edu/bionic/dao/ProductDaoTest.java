package edu.bionic.dao;

import edu.bionic.domain.Product;
import edu.bionic.testdata.ProductFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoTest extends BaseDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void getAll() throws Exception {
        List<Product> expected = ProductFactory.getAllProducts();
        List<Product> actual = productDao.getAll();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getById() throws Exception {
        Product expected = ProductFactory.getProduct1();
        Product actual = productDao.getById(1).get();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void saveNew() throws Exception {
        Product newProduct = ProductFactory.newProduct();
        Product savedProduct = productDao.save(newProduct);
        newProduct.setId(savedProduct.getId());

        List<Product> expected = ProductFactory.getAllProducts();
        expected.add(newProduct);
        List<Product> actual = productDao.getAll();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void saveUpdate() throws Exception {
        Product productToUpdate = ProductFactory.getProduct2Updated();
        Product updatedProduct = productDao.save(productToUpdate);
        Assert.assertEquals(productToUpdate.toString(), updatedProduct.toString());

        Product updatedProductFromDB = productDao.getById(2).get();
        Assert.assertEquals(productToUpdate.toString(), updatedProductFromDB.toString());
    }

    @Test
    public void delete() throws Exception {
        boolean isDeleted = productDao.delete(3);
        Assert.assertEquals(isDeleted, true);

        List<Product> expected = ProductFactory.getAllProducts()
                .stream().filter(product -> product.getId() != 3).collect(Collectors.toList());
        List<Product> actual = productDao.getAll();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void deleteNotFound() throws Exception {
        boolean isDeleted = productDao.delete(1000);
        Assert.assertEquals(isDeleted, false);
    }

    @Test
    public void getAllSortedByName() throws Exception {
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByName(null, null, null, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllSortedByNameWithLimits() throws Exception {
        int offset = 2;
        int limit = 3;
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByName(null, null, null, false, offset, limit);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllSortedByNameDesc() throws Exception {
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByName(null, null, null, true, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllFilteredByNameSortedByName() throws Exception {
        String filterName = "Plus";
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .filter(product -> product.getName().contains(filterName))
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByName(filterName, null, null, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllFilteredByPriceSortedByName() throws Exception {
        BigDecimal filterMin = BigDecimal.valueOf(650);
        BigDecimal filterMax = BigDecimal.valueOf(700);
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .filter(product -> product.getPrice().compareTo(filterMin) >= 0
                        && product.getPrice().compareTo(filterMax) <= 0)
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByName(null, filterMin, filterMax, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllSortedByPrice() throws Exception {
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByPrice(null, null, null, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllSortedByPriceWithLimits() throws Exception {
        int offset = 2;
        int limit = 3;
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByPrice(null, null, null, false, offset, limit);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllSortedByPriceDesc() throws Exception {
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByPrice(null, null, null, true, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllFilteredByNameSortedByPrice() throws Exception {
        String filterName = "Plus";
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .filter(product -> product.getName().contains(filterName))
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByPrice(filterName, null, null, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void getAllFilteredByPriceSortedByPrice() throws Exception {
        BigDecimal filterMin = BigDecimal.valueOf(650);
        BigDecimal filterMax = BigDecimal.valueOf(700);
        List<Product> expected = ProductFactory.getAllProducts()
                .stream()
                .filter(product -> product.getPrice().compareTo(filterMin) >= 0
                        && product.getPrice().compareTo(filterMax) <= 0)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());

        List<Product> actual = productDao.getAllSortedByPrice(null, filterMin, filterMax, false, 0, 1000);

        Assert.assertEquals(expected.toString(), actual.toString());
    }
}