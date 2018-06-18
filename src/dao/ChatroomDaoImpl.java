package dao;

import action.ChatroomServlet;
import entity.Chatroom;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ChatroomDaoImpl implements ChatroomDao {
    @Override
    public List<Chatroom> getRooms(String user_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT chatroom.* FROM chatroom,user_room WHERE chatroom.id=room_id and user_id = ?";
        List<Chatroom> rooms;
        try {
            rooms = queryRunner.query(sql, new BeanListHandler<Chatroom>(Chatroom.class), user_id);
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chatroom getRoom(String cid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM chatroom WHERE id = ?";
        Chatroom room;
        try {
            room = queryRunner.query(sql, new BeanHandler<Chatroom>(Chatroom.class), cid);
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chatroom find(String keyWord) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM chatroom WHERE id = ? or name=?";
        Chatroom room;
        try {
            room = queryRunner.query(sql, new BeanHandler<Chatroom>(Chatroom.class), keyWord, keyWord);
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Chatroom chatroom) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO `chatroom`(admin_id,`name`,total_num,password) VALUES " +
                "(?, ?,?, ?)";
        try {
            queryRunner.update(sql, new Object[]{chatroom.getAdmin_id(),chatroom.getName(),chatroom.getTotal_num(),chatroom.getPassword()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
