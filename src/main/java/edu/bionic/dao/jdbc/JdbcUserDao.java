package edu.bionic.dao.jdbc;

import edu.bionic.dao.UserDao;
import edu.bionic.domain.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserDao implements UserDao {

    private RowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert productInsert;


    public JdbcUserDao(JdbcTemplate jdbcTemplate,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                       DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        productInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("name", user.getName())
                .addValue("role", user.getRole().ordinal());

        if (user.getId() == null) {
            Number id = productInsert.executeAndReturnKey(parameterSource);
            user.setId(id.intValue());
        } else {
            String sql = "UPDATE users SET email=:email, password=:password, name=:name, role=:role " +
                    "WHERE id=:id";
            namedParameterJdbcTemplate.update(sql, parameterSource);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    @Override
    public Optional<User> getById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> product = jdbcTemplate.query(sql, new Object[]{userId}, ROW_MAPPER);
        return Optional.ofNullable(DataAccessUtils.singleResult(product));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> product = jdbcTemplate.query(sql, new Object[]{email}, ROW_MAPPER);
        return Optional.ofNullable(DataAccessUtils.singleResult(product));
    }
}
