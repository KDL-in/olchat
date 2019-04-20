package dao;

import entity.Comment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public List<Comment> list(int moment_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from comment where moment_id = ? order by time desc";
        List<Comment> comments = new ArrayList<>();
        try {
            comments = queryRunner.query(sql, new BeanListHandler<>(Comment.class), moment_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public void insert(Comment c) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO comment(content,user_id,reply_id,moment_id,time)"
                + "VALUES(?,?,?,?,?)";
        try {
            queryRunner.update(sql, new Object[]{c.getContent(), c.getUser_id(), c.getReply_id(), c.getMoment_id(), c.getTime()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int comment_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from comment where id = ?";
        try {
            queryRunner.update(sql, comment_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
