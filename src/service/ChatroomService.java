package service;

import dao.ChatroomDaoImpl;
import entity.Chatroom;

import java.util.List;

public class ChatroomService {

    public List<Chatroom> getRooms(String user_id) {
        ChatroomDaoImpl dao = new ChatroomDaoImpl();
        return dao.getRooms(user_id);
    }

    public Chatroom getRoom(String cid) {
        ChatroomDaoImpl dao = new ChatroomDaoImpl();
        return dao.getRoom(cid);
    }
}
