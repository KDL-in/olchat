package dao;

import entity.Chatroom;
import entity.Friendship;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class FriendshipDapImpl implements FriendshipDao {

    @Override
    public Friendship select(int user1_id, int user2_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM Friendship Where user1_id = ? and user2_id = ?";
        Friendship friendship = null;
        try {
            friendship = queryRunner.query(sql, new BeanHandler<Friendship>(Friendship.class), user1_id,user2_id);
            return friendship;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(int user1_id, int user2_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO `friendship` (user1_id,user2_id)VALUES (?, ?)";
        try {
            queryRunner.update(sql, new Object[]{user1_id,user2_id});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findFriends(int user_id) {
        String sql = "select user.* from friendship,user\n" +
                "WHERE  (user1_id = user.id and user2_id=? ) or (user2_id = user.id and user1_id = ?);";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<User> users = null;
        try {
            users = queryRunner.query(sql, new BeanListHandler<>(User.class),user_id,user_id);
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Friendship> selectAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM Friendship";
        List<Friendship>friendships = null;
        try {
            friendships = queryRunner.query(sql, new BeanListHandler<>(Friendship.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public List<Friendship> selectAll(int startIndex, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM `friendship` LIMIT ?,?";
        List<Friendship> fss = null;
        try {
            fss = queryRunner.query(sql, new BeanListHandler<>(Friendship.class),startIndex,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fss;
    }

    @Override
    public void delete(int fs_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from friendship where id = ?";
        try {
            queryRunner.update(sql, fs_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
