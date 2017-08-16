package edu.bionic.dao.jdbc;

import edu.bionic.dao.CommentDao;
import edu.bionic.domain.Comment;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@Transactional
public class JdbcCommentDao implements CommentDao {

    private RowMapper<Comment> ROW_MAPPER;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert commentInsert;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        ROW_MAPPER = BeanPropertyRowMapper.newInstance(Comment.class);
        commentInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("comments")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Comment> getByProduct(int productId) {
        String sql = "SELECT * FROM comments WHERE product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId},ROW_MAPPER);
    }

    @Override
    public Comment save(Comment comment) {
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(comment);
        Number newId = commentInsert.executeAndReturnKey(parameters);

        comment.setId(newId.intValue());
        return comment;
    }
}
