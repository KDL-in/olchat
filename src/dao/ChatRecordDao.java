package dao;

import entity.ChatRecord;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ChatRecordDao {
    boolean insert(ChatRecord cr);

    List<ChatRecord> getRecordsBetween(int room_id, Timestamp start, Timestamp end);

    List<ChatRecord> getRecordsBetween(int user_id, int friend_id, Timestamp timestamp, Timestamp timestamp1);

    List<ChatRecord> selectAll();
}
