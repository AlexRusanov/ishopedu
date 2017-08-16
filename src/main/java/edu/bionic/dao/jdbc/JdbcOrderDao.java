package edu.bionic.dao.jdbc;

import edu.bionic.dao.OrderDao;
import edu.bionic.domain.Order;
import org.springframework.context.annotation.Primary;
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

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
@Transactional
public class JdbcOrderDao implements OrderDao {

    private RowMapper<Order> ROW_MAPPER;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert orderInsert;

    private JdbcProductDao jdbcProductDao;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate,
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        DataSource dataSource,
                        JdbcProductDao jdbcOrderDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcProductDao = jdbcOrderDao;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        ROW_MAPPER = BeanPropertyRowMapper.newInstance(Order.class);
        orderInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = jdbcTemplate.query(sql, ROW_MAPPER);
        orders.forEach(order -> order.setProducts(jdbcProductDao.getByOrder(order.getId())));
        return orders;
    }

    @Override
    public Order save(Order order) {
        SqlParameterSource orderParameterSource = new BeanPropertySqlParameterSource(order);
        Number newId = orderInsert.executeAndReturnKey(orderParameterSource);
        order.setId(newId.intValue());

        String sql = "INSERT INTO orders_products (order_id, product_id) VALUES (:order_id, :product_id)";

        order.getProducts().forEach(product -> {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("order_id", order.getId());
            parameterSource.addValue("product_id", product.getId());
            this.namedParameterJdbcTemplate.update(sql, parameterSource);
        });

        return order;
    }
}
