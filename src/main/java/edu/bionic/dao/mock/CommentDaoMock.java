package edu.bionic.dao.mock;

import edu.bionic.dao.CommentDao;
import edu.bionic.domain.Comment;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDaoMock implements CommentDao {

    private List<Comment> commentStorage;

    @Override
    public List<Comment> getByProduct(int productId) {
        return commentStorage
                .stream()
                .filter(comment -> comment.getProductId() == productId)
                .collect(Collectors.toList());
    }

    @Override
    public Comment save(Comment comment) {
        commentStorage.add(comment);
        return comment;
    }

    public void initCommentStorage() {
        commentStorage = new ArrayList<>();

        commentStorage.add(new Comment(
                1,
                1,
                "Сергей",
                LocalDateTime.of(2016, Month.DECEMBER, 28, 13, 0),
                "Отличный девайс. Пользуюсь уже около года. Никаких замечаний",
                5
        ));
        commentStorage.add(new Comment(
                2,
                4,
                "Анна",
                LocalDateTime.of(2017, Month.MARCH, 12, 15, 0),
                "Возникли проблемы на второй месяц использования. Пропадает зук в динамиках",
                3
        ));
        commentStorage.add(new Comment(
                3,
                1,
                "Инна",
                LocalDateTime.of(2017, Month.APRIL, 5, 10, 30),
                "Хоший телефон. Единственный недостаток это цена :(",
                4
        ));
    }
}
