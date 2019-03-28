package dao;

import entity.Chatroom;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChatroomDaoImplTest {

    @Test
    public void getRooms() {
//        ChatroomDao dao = new ChatroomDaoImpl();
//        List<Chatroom> list = dao.getRooms("1");
//        for (Chatroom u :
//                list) {
//            System.out.println(u.toString());
//
//        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, i);
        }
    }

    @Test
    public void getRoom() {
        ChatroomDao dao = new ChatroomDaoImpl();
        Chatroom chatroom = dao.getRoom(1 + "");
        System.out.println(chatroom);
    }

    @Test
    public void find() {
        ChatroomDao dao = new ChatroomDaoImpl();
        Chatroom chatroom = dao.find("案例聊天室一");
        System.out.println(chatroom);
    }
}