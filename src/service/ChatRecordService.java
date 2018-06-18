package service;

import dao.ChatRecordDao;
import dao.ChatRecordDaoImpl;
import entity.ChatRecord;

import java.sql.Timestamp;
import java.util.List;

public class ChatRecordService {
    public List<ChatRecord> getRecordsBetween(String room_id, String start, String end) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        int rid=Integer.parseInt(room_id);
        long s = Long.parseLong(start), e = Long.parseLong(end);
        return dao.getRecordsBetween(rid,new Timestamp(s),new Timestamp(e));
    }

    public boolean insertMessage(ChatRecord cr) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        return dao.insert(cr);
    }
}
