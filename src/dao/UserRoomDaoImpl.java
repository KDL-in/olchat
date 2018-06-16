package dao;

import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class UserRoomDaoImpl implements UserRoomDao {
    @Override
    public List<User> getMembers(String cid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT user.* FROM user,user_room WHERE user_room.id= ?";
        List<User>members;
        try {
            members = queryRunner.query(sql, new BeanListHandler<User>(User.class),1);
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
