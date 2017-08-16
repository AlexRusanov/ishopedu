package edu.bionic.dao;

import edu.bionic.domain.Product;
import edu.bionic.testdata.ProductFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}