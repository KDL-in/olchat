package service;

import dao.ChatroomDao;
import dao.ChatroomDaoImpl;
import entity.Chatroom;

import java.util.List;
import java.util.Map;

public class ChatroomService {

    public List<Chatroom> getRooms(String user_id) {
        ChatroomDaoImpl dao = new ChatroomDaoImpl();
        return dao.getRooms(user_id);
    }

    public Chatroom getRoom(String cid) {
        ChatroomDaoImpl dao = new ChatroomDaoImpl();
        return dao.getRoom(cid);
    }

    public Chatroom search(String keyWord) {
        ChatroomDao dao = new ChatroomDaoImpl();
        return dao.find(keyWord);
    }


    public boolean create(Chatroom chatroom) {
        boolean r = new ChatroomDaoImpl().insert(chatroom);
        //管理员加入群
        ChatroomService cs = new ChatroomService();
        Chatroom fc = cs.search(chatroom.getName());
        UserRoomService urs = new UserRoomService();
        urs.addToChatroom(fc.getAdmin_id(), fc.getId());
        return r;
    }

    public List<Chatroom> find(String keyWord) {
        ChatroomDao dao = new ChatroomDaoImpl();
        return dao.search(keyWord);
    }
}
