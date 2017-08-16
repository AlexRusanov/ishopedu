package edu.bionic.dao;

import edu.bionic.domain.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getByProduct(int productId);
    Comment save(Comment comment);
}
