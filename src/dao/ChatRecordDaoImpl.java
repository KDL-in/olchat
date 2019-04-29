package dao;

import entity.ChatRecord;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ChatRecordDaoImpl implements ChatRecordDao {
/*    @Override
    public List<ChatRecord> getRecordsBetween(String room_id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM  chat_record  where date_sub(curdate(), INTERVAL 7 DAY) <= date (`time`) and room_id=?";
        List<ChatRecord> records;
        try {
            records = queryRunner.query(sql, new BeanListHandler<ChatRecord>(ChatRecord.class), room_id);
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public boolean insert(ChatRecord cr) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO `chat_record` (content,time,room_id,type,target_name,user_id,user,friend_id)VALUES " +
                "(?, ?, ?,?, ?, ?, ?,?)";
        try {
            queryRunner.update(sql, new Object[]{cr.getContent(), cr.getTime(), cr.getRoom_id(), cr.getType(), cr.getTarget_name(), cr.getUser_id(), cr.getUser(),cr.getFriend_id()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ChatRecord> getRecordsBetween(int room_id, Timestamp start, Timestamp end) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM  chat_record  where `time`>= ? and `time`<= ? and room_id=?";
        List<ChatRecord> records;
        try {
            records = queryRunner.query(sql, new BeanListHandler<ChatRecord>(ChatRecord.class), start,end, room_id);
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChatRecord> getRecordsBetween(int user_id, int friend_id, Timestamp start, Timestamp end) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM  chat_record  where `time`>= ? and `time`<= ? and ((user_id=? and friend_id=?) or (friend_id=? and user_id = ? ))";
        List<ChatRecord> records;
        try {
            records = queryRunner.query(sql, new BeanListHandler<ChatRecord>(ChatRecord.class), start,end, user_id,friend_id,user_id,friend_id);
//            System.out.println(user_id+" "+friend_id+"size: "+records.size());
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChatRecord> selectAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * from chat_record";
        List<ChatRecord> records;
        try {
            records = queryRunner.query(sql, new BeanListHandler<ChatRecord>(ChatRecord.class));
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChatRecord> selectAll(int startIndex, int pageSize) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM `chat_record` LIMIT ?,?";
        List<ChatRecord> records = null;
        try {
            records = queryRunner.query(sql, new BeanListHandler<>(ChatRecord.class),startIndex,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public void delete(int id) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from chat_record where id = ?";
        try {
            queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent(ChatRecord record) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "UPDATE chat_record\n" +
                "set  content = ?\n" +
                "where id = ?";
        try {
            queryRunner.update(sql, new Object[]{record.getContent(),record.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
