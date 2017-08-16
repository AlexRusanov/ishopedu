package edu.bionic.dao.jdbc;

import edu.bionic.dao.ProductDao;
import edu.bionic.domain.Color;
import edu.bionic.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Transactional
public class JdbcProductDao implements ProductDao {

    private RowMapper<Product> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Product.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert productInsert;


    @Autowired
    public JdbcProductDao(JdbcTemplate jdbcTemplate,
                          NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        productInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    @Override
    public List<Product> getAllSortedByName(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit) {
        String sql = "SELECT * FROM products WHERE name LIKE :name " +
                (min == null ? "" : "AND price >= :min ") +
                (max == null ? "" : "AND price <= :max ") +
                "ORDER BY name " + (desc ? "DESC " : "ASC ") +
                "OFFSET :offset LIMIT :limit";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", StringUtils.isEmpty(name) ? "%" : "%" + name + "%" )
                .addValue("max", max)
                .addValue("min", min)
                .addValue("offset", offset)
                .addValue("limit", limit);

        return namedParameterJdbcTemplate.query(sql, parameterSource, ROW_MAPPER);
    }

    @Override
    public List<Product> getAllSortedByPrice(String name, BigDecimal min, BigDecimal max, boolean desc, int offset, int limit) {
        String sql = "SELECT * FROM products WHERE name LIKE :name " +
                (min == null ? "" : "AND price >= :min ") +
                (max == null ? "" : "AND price <= :max ") +
                "ORDER BY price " + (desc ? "DESC " : "ASC ") +
                "OFFSET :offset LIMIT :limit";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", StringUtils.isEmpty(name) ? "%" : "%" + name + "%" )
                .addValue("max", max)
                .addValue("min", min)
                .addValue("offset", offset)
                .addValue("limit", limit);

        return namedParameterJdbcTemplate.query(sql, parameterSource, ROW_MAPPER);
    }

    @Override
    public int getCount(String name, BigDecimal min, BigDecimal max) {
        String sql = "SELECT COUNT(*) FROM products WHERE name LIKE :name " +
                (min == null ? "" : "AND price >= :min ") +
                (max == null ? "" : "AND price <= :max ");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", StringUtils.isEmpty(name) ? "%" : "%" + name + "%" )
                .addValue("max", max)
                .addValue("min", min);

        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public Optional<Product> getById(int productId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> product = jdbcTemplate.query(sql, new Object[]{productId}, ROW_MAPPER);
        return Optional.ofNullable(DataAccessUtils.singleResult(product));
    }

    @Override
    public Product save(Product product) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("color", product.getColor().ordinal())
                .addValue("capacity", product.getCapacity())
                .addValue("display", product.getDisplay())
                .addValue("description", product.getDescription());

        if (product.getId() == null) {
            Number id = productInsert.executeAndReturnKey(parameterSource);
            product.setId(id.intValue());
        } else {
            String sql = "UPDATE products SET name=:name, price=:price, color=:color, capacity=:capacity, " +
                    "display=:display, description=:description WHERE id=:id";
            namedParameterJdbcTemplate.update(sql, parameterSource);
        }
        return product;
    }

    @Override
    public boolean delete(int productId) {
        return jdbcTemplate.update("DELETE FROM products WHERE id=?", productId) != 0;
    }

    List<Product> getByOrder(int orderId) {
        String sql = "SELECT * FROM products LEFT JOIN orders_products ON products.id = orders_products.product_id " +
                "WHERE orders_products.order_id = ?";
        return jdbcTemplate.query(sql, new Object[] {orderId},ROW_MAPPER);
    }
}
