package service;

import dao.ChatroomDao;
import dao.ChatroomDaoImpl;
import entity.Chatroom;
import entity.PageBean;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;
import java.util.Map;

public class ChatroomService {
    ChatroomDao dao;

    public ChatroomService() {
        dao = new ChatroomDaoImpl();
    }

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

    public List<Chatroom> listRooms() {
        ChatroomDao dao = new ChatroomDaoImpl();
        return dao.selectAll();
    }


    public int countRooms() {
        return dao.selectAll().size();
    }

    public PageBean findAllUserOfPage(int pageNum, int pageSize) {
        List<Chatroom> chatrooms = dao.selectAll();
        PageBean<Chatroom> pb = new PageBean<>(pageNum, pageSize, chatrooms);
        pb.setList(dao.selectAll(pb.getStartIndex(),pb.getPageSize()));
        return pb;
    }

    public void deleteRoom(int room_id) {
        dao.delete(room_id);
    }

    public void modRoom(int id, String name, String password,int admin_id) {
        Chatroom chatroom = new Chatroom();
        chatroom.setName(name);
        chatroom.setId(id);
        chatroom.setPassword(password);
        chatroom.setAdmin_id(admin_id);
        dao.update(chatroom);
    }


}
