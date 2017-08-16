package edu.bionic.service;

import edu.bionic.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getByProduct(int productId);
    void createNew(Comment comment);

}
