package edu.bionic.testdata;

import com.google.common.collect.Lists;
import edu.bionic.domain.Comment;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class CommentFactory {

    public static Comment getComment1() {
        return new Comment(
                1,
                1,
                "Сергей",
                LocalDateTime.of(2016, Month.DECEMBER, 28, 13, 0),
                "Отличный девайс. Пользуюсь уже около года. Никаких замечаний",
                5
        );
    }

    public static Comment getComment2() {
        return new Comment(
                2,
                1,
                "Анна",
                LocalDateTime.of(2017, Month.MARCH, 12, 15, 0),
                "Возникли проблемы на второй месяц использования. Пропадает зук в динамиках",
                3
        );
    }

    public static Comment getComment3() {
        return new Comment(
                3,
                1,
                "Инна",
                LocalDateTime.of(2017, Month.APRIL, 5, 10, 30),
                "Хоший телефон. Единственный недостаток это цена :(",
                4
        );
    }

    public static List<Comment> getCommentsForProduct1() {
        return Lists.newArrayList(getComment1(), getComment3());
    }

    public static Comment getNewCommentForProduct1() {
        return new Comment(
                null,
                1,
                "Валера",
                LocalDateTime.of(2017, Month.AUGUST, 11, 11, 11),
                "Классный аппарат!!",
                5
        );
    }
}
