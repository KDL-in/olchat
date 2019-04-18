package dao;

import entity.Moment;
import org.apache.commons.dbutils.QueryRunner;
import utils.JDBCUtils;

import java.sql.SQLException;

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
}
