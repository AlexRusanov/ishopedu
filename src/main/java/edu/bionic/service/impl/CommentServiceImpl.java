package edu.bionic.service.impl;

import edu.bionic.dao.CommentDao;
import edu.bionic.domain.Comment;
import edu.bionic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getByProduct(int productId) {
        return commentDao.getByProduct(productId);
    }

    @Override
    public void createNew(Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        commentDao.save(comment);
    }
}
