package dao;

import entity.Chatroom;
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
            queryRunner.update(sql, new Object[]{chatroom.getAdmin_id(), chatroom.getName(), chatroom.getTotal_num(), chatroom.getPassword()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Chatroom> search(String word) {
        String keyWord = "%" + word + "%";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from chatroom where name like ?";
        List<Chatroom> rooms;
        try {
            rooms = queryRunner.query(sql, new BeanListHandler<Chatroom>(Chatroom.class),keyWord);
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chatroom> selectAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from chatroom";
        List<Chatroom> rooms = null;
        try {
            rooms = queryRunner.query(sql, new BeanListHandler<Chatroom>(Chatroom.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Chatroom> selectAll(int startIndex, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM `Chatroom` LIMIT ?,?";
        List<Chatroom> rooms = null;
        try {
            rooms = queryRunner.query(sql, new BeanListHandler<>(Chatroom.class),startIndex,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void delete(int room_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from chatroom where id = ?";
        try {
            queryRunner.update(sql, room_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Chatroom c) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "UPDATE chatroom\n" +
                "set  name = ? ,password=?,admin_id = ?\n" +
                "where id = ?";
        try {
            queryRunner.update(sql, new Object[]{c.getName(),c.getPassword(),c.getAdmin_id(),c.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(int room_id, String name) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "UPDATE chatroom\n" +
                "set  name = ?" +
                "where id = ?";
        try {
            int id = queryRunner.update(sql, new Object[]{name,room_id});
            return id == 0 ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
