package service;

import dao.UserRoomDao;
import dao.UserRoomDaoImpl;
import entity.User;
import entity.UserRoom;

import java.util.List;

public class UserRoomService {
    public List<User> getMembers(String cid) {
        UserRoomDao dao = new UserRoomDaoImpl();
        return dao.getMembers(cid);
    }

    public boolean addToChatroom(int user_id, int room_id) {
        //构造实体
        UserRoom userRoom = new UserRoom();
        userRoom.setUser_id(user_id);
        userRoom.setRoom_id(room_id);
        UserRoomDao dao = new UserRoomDaoImpl();
        return dao.insert(userRoom);
    }

    public boolean isExisted(int user_id, int room_id) {
        UserRoom userRoom = new UserRoom();
        userRoom.setUser_id(user_id);
        userRoom.setRoom_id(room_id);
        UserRoomDao dao = new UserRoomDaoImpl();
        return dao.select(userRoom)!=null;
    }

}
