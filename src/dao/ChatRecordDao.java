package dao;

import entity.ChatRecord;

import java.sql.Timestamp;
import java.util.List;

public interface ChatRecordDao {
    boolean insert(ChatRecord cr);

    List<ChatRecord> getRecordsBetween(int room_id, Timestamp start, Timestamp end);
}
