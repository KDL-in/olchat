package dao;

import entity.Chatroom;

import java.util.List;

public interface ChatroomDao {
    List<Chatroom> getRooms(String user_id);

    Chatroom getRoom(String cid);

    Chatroom find(String keyWord);

    boolean insert(Chatroom chatroom);

    List<Chatroom> search(String keyWord);

    List<Chatroom> selectAll();

    List<Chatroom> selectAll(int startIndex, int pageSize);

    void delete(int room_id);

    void update(Chatroom c);
}
