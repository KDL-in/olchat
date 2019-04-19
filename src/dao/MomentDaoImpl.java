package dao;

import entity.Moment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class MomentDaoImpl implements MomentDao {
    @Override
    public void insert(Moment m) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO moment(content,img_url,time,likes,user_id)"
                + "VALUES(?,?,?,?,?)";
        try {
            queryRunner.update(sql, new Object[]{m.getContent(), m.getImg_url(), m.getTime(), m.getLikes(), m.getUser_id()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Moment> findMomentBy(int user_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM moment\n" +
                "WHERE user_id in(\n" +
                "select user1_id FROM friendship WHERE user1_id = ? or user2_id = ?\n" +
                "UNION\n" +
                "SELECT user2_id FROM friendship WHERE user1_id = ? or user2_id = ?\n" +
                ")\n" +
                "ORDER BY time DESC;";
        List<Moment> moments = null;
        try {
            moments = queryRunner.query(sql, new BeanListHandler<>(Moment.class), user_id, user_id, user_id, user_id);
        } catch (SQLException e) {

        }
        return moments;
    }

    @Override
    public boolean delete(int id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from moment where id = ?";
        try {
            queryRunner.update(sql, id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
