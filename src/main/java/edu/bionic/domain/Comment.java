package edu.bionic.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private Integer productId;

    @NotBlank(message = "Имя не должно быть пустым")
    private String author;
    private LocalDateTime dateTime;

    @NotBlank(message = "Комментарий не должен быть пустым")
    private String text;

    @Range(min = 1, max = 5)
    private Integer rating;

    public Comment(Integer id, Integer productId, String author, LocalDateTime dateTime, String text, Integer rating) {
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
        this.rating = rating;
    }

    public Comment() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (productId != null ? !productId.equals(comment.productId) : comment.productId != null) return false;
        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        return dateTime != null ? dateTime.equals(comment.dateTime) : comment.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", productId=" + productId +
                ", author='" + author + '\'' +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
