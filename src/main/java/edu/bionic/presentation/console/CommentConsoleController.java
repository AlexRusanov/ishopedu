package edu.bionic.presentation.console;

import edu.bionic.ConsoleApplication;
import edu.bionic.domain.Comment;
import edu.bionic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class CommentConsoleController {

    private CommentService commentService;

    @Autowired
    public CommentConsoleController(CommentService commentService1) {
        this.commentService = commentService1;
    }

    public void printCommentForProduct(int productId) {
        List<Comment> comments = commentService.getByProduct(productId);

        comments.forEach(comment -> {
            System.out.println(String.format(
                    "%s. %s. Рейтинг - %d\n%s\n",
                    comment.getAuthor(),
                    comment.getDateTime().toString(),
                    comment.getRating(),
                    comment.getText()));
        });
        if (comments.size() == 0) System.out.println("Отзывов пока нет :(");
    }

    public void createNewComment(int productId) throws IOException {
        System.out.println("Введите имя:");
        String name = ConsoleApplication.consoleReader.readLine();
        System.out.println("Оцените продукт (0-5):");
        Integer rating = Integer.parseInt(ConsoleApplication.consoleReader.readLine());
        System.out.println("Оставьте расширенный отзыв:");
        String text = ConsoleApplication.consoleReader.readLine();

        // validation
        if (rating > 5) rating = 5;
        else if (rating < 0) rating = 0;

        Comment newComment = new Comment();
        newComment.setProductId(productId);
        newComment.setAuthor(name);
        newComment.setRating(rating);
        newComment.setText(text);

        commentService.createNew(newComment);
    }
}
