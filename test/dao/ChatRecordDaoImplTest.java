package dao;

import entity.ChatRecord;
import entity.Chatroom;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChatRecordDaoImplTest {

    @Test
    public void getRecentlyRecords() {
        ChatRecordDao dao = new ChatRecordDaoImpl();
        List<ChatRecord> list = dao.getRecentlyRecords("1");
        for (ChatRecord u :
                list) {
            System.out.println(u.toString());

        }
    }
}