package service;

import dao.CommentDao;
import dao.CommentDaoImpl;
import entity.Comment;

import java.sql.Timestamp;
import java.util.List;

public class CommentService {
    public List<Comment> listComments(int id) {
        CommentDao dao = new CommentDaoImpl();
        return dao.list(id);
    }

    public void addComment(int user_id, int reply_id, int moment_id, String content, Timestamp time) {
        Comment c  = new Comment();
        c.setContent(content);
        c.setMoment_id(moment_id);
        c.setReply_id(reply_id);
        c.setTime(time);
        c.setUser_id(user_id);
        CommentDao dao = new CommentDaoImpl();
        dao.insert(c);
    }

    public void deleteComment(int comment_id) {
        CommentDao dao = new CommentDaoImpl();
        dao.delete(comment_id);
    }
}
