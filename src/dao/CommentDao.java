package dao;

import entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> list(int id);

    void insert(Comment c);

    void delete(int comment_id);
}
