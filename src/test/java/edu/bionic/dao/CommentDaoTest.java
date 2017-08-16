package edu.bionic.dao;

import edu.bionic.domain.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.bionic.testdata.CommentFactory.getCommentsForProduct1;
import static edu.bionic.testdata.CommentFactory.getNewCommentForProduct1;


public class CommentDaoTest extends BaseDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void getByProduct() throws Exception {
        List<Comment> expected = getCommentsForProduct1();
        List<Comment> actual = commentDao.getByProduct(1);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void save() throws Exception {
        Comment newComment = getNewCommentForProduct1();
        Comment savedComment = commentDao.save(newComment);
        newComment.setId(savedComment.getId());

        List<Comment> expected = getCommentsForProduct1();
        expected.add(newComment);

        List<Comment> actual = commentDao.getByProduct(1);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

}