package service;

import dao.ChatRecordDao;
import dao.ChatRecordDaoImpl;
import entity.ChatRecord;

import java.util.List;

public class ChatRecordService {
    public List<ChatRecord> getRecentlyRecords(String room_id) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        return dao.getRecentlyRecords(room_id);
    }

    public boolean insertMessage(ChatRecord cr) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        return dao.insert(cr);
    }
}
