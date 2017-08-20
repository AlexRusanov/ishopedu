package edu.bionic.dao.jdbc;

import edu.bionic.dao.CommentDao;
import edu.bionic.domain.Comment;
import edu.bionic.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
@Transactional
public class JdbcCommentDao implements CommentDao {

    private RowMapper<Comment> ROW_MAPPER;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert commentInsert;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        ROW_MAPPER = (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setProduct(new Product(rs.getInt("product_id")));
            comment.setAuthor(rs.getString("author"));
            comment.setDateTime(rs.getTimestamp("datetime").toLocalDateTime());
            comment.setText(rs.getString("text"));
            comment.setRating(rs.getInt("rating"));

            return comment;
        };
        commentInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("comments")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Comment> getByProduct(int productId) {
        String sql = "SELECT * FROM comments WHERE product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, ROW_MAPPER);
    }

    @Override
    public Comment save(Comment comment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", comment.getAuthor());
        parameterSource.addValue("product_id", comment.getProduct().getId());
        parameterSource.addValue("datetime", comment.getDateTime());
        parameterSource.addValue("rating", comment.getRating());
        parameterSource.addValue("text", comment.getText());
        Number newId = commentInsert.executeAndReturnKey(parameterSource);

        comment.setId(newId.intValue());
        return comment;
    }
}
