package dao;

import entity.Chatroom;

import java.util.List;

public interface ChatroomDao {
    List<Chatroom> getRooms(String user_id);

    Chatroom getRoom(String cid);

    Chatroom find(String keyWord);

    boolean insert(Chatroom chatroom);
}
