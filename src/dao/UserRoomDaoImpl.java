package dao;

import entity.User;
import entity.UserRoom;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class UserRoomDaoImpl implements UserRoomDao {
    @Override
    public List<User> getMembers(String cid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT user.* FROM `user`,user_room WHERE `user`.id=user_id and room_id = ?";
        List<User> members;
        try {
            members = queryRunner.query(sql, new BeanListHandler<User>(User.class), cid);
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(UserRoom ur) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO `user_room` (user_id,room_id)VALUES (?, ?)";
        try {
            queryRunner.update(sql, new Object[]{ur.getUser_id(), ur.getRoom_id()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserRoom select(UserRoom userRoom) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM user_room WHERE user_id= ? and room_id = ?";
        UserRoom ur;
        try {
            ur = queryRunner.query(sql, new BeanHandler<UserRoom>(UserRoom.class), userRoom.getUser_id(), userRoom.getRoom_id());
            return ur;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(UserRoom userRoom) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "DELETE FROM user_room WHERE user_id= ? and room_id=?";
        try {
            queryRunner.update(sql, new Object[]{userRoom.getUser_id(),userRoom.getRoom_id()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserRoom> selectAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user_room";
        List<UserRoom> userRooms = null;
        try {
            userRooms = queryRunner.query(sql, new BeanListHandler<>(UserRoom.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRooms;

    }
}
