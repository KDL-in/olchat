package dao;

import entity.ChatRecord;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ChatRecordDaoImpl implements ChatRecordDao {
    @Override
    public List<ChatRecord> getRecentlyRecords(String room_id) {
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
    }

    @Override
    public boolean insert(ChatRecord cr) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO `chat_record` (content,time,room_id,type,target_name,user_id,user)VALUES " +
                "(?, ?, ?,?, ?, ?, ?)";
        try{
            queryRunner.update(sql, new Object[]{cr.getContent(), cr.getTime(), cr.getRoom_id(), cr.getType(), cr.getTarget_name(), cr.getUser_id(), cr.getUser()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
