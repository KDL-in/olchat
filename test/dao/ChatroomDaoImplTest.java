package dao;

import entity.Chatroom;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChatroomDaoImplTest {

    @Test
    public void getRooms() {
        ChatroomDao dao = new ChatroomDaoImpl();
        List<Chatroom> list = dao.getRooms("1");
        for (Chatroom u :
                list) {
            System.out.println(u.toString());

        }
    }
}