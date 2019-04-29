package service;

import dao.ChatRecordDao;
import dao.ChatRecordDaoImpl;
import dao.ChatroomDao;
import dao.ChatroomDaoImpl;
import entity.ChatRecord;
import entity.PageBean;

import java.sql.Timestamp;
import java.util.List;

public class ChatRecordService {
    private ChatRecordDao dao;

    public List<ChatRecord> getRecordsBetween(String room_id, String start, String end) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        int rid = Integer.parseInt(room_id);
        long s = Long.parseLong(start), e = Long.parseLong(end);
        return dao.getRecordsBetween(rid, new Timestamp(s), new Timestamp(e));
    }

    public boolean insertMessage(ChatRecord cr) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        return dao.insert(cr);
    }

    public List<ChatRecord> getRecordsBetween(int user_id, int friend_id, String start, String end) {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        long s = Long.parseLong(start), e = Long.parseLong(end);
        return dao.getRecordsBetween(user_id, friend_id, new Timestamp(s), new Timestamp(e));
    }

    public ChatRecordService() {
        dao = new ChatRecordDaoImpl();
    }

    public int countRecords() {
        List<ChatRecord> records = dao.selectAll();
        return records.size();
    }

    public int countImg() {
        List<ChatRecord> records = dao.selectAll();
        int count =0 ;
        for (ChatRecord record
                : records
        ) {
            if (record.getType() == 1 || record.getType() == 5) {
                ++count;
            }
        }
        return count;
    }

    public PageBean findAllUserOfPage(int pageNum, int pageSize) {
        List<ChatRecord> records = dao.selectAll();
        PageBean<ChatRecord> pb = new PageBean<>(pageNum, pageSize, records);
        pb.setList(dao.selectAll(pb.getStartIndex(), pb.getPageSize()));
        return pb;
    }

    public void deleteRecord(int id) {
        dao.delete(id);
    }

    public void modContent(int id, String content) {
        ChatRecord record = new ChatRecord();
        record.setId(id);
        record.setContent(content);
        dao.updateContent(record);
    }
}
