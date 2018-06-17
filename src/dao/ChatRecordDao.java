package dao;

import entity.ChatRecord;

import java.util.List;

public interface ChatRecordDao {
    List<ChatRecord> getRecentlyRecords(String room_id);

    boolean insert(ChatRecord cr);
}
