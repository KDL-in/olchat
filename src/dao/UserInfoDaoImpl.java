package dao;

import entity.User;
import entity.UserInfo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {

    @Override
    public List<UserInfo> selectAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM `user_info`";
        List<UserInfo> infos = null;
        try {
            infos = queryRunner.query(sql, new BeanListHandler<>(UserInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infos;
    }
}
