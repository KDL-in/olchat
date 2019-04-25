package service;

import dao.UserRoomDao;
import dao.UserRoomDaoImpl;
import entity.User;
import entity.UserRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean removeUserFrom(String user_id, String room_id) {
        UserRoom userRoom = new UserRoom();
        userRoom.setUser_id(Integer.parseInt(user_id));
        userRoom.setRoom_id(Integer.parseInt(room_id));
        UserRoomDao dao = new UserRoomDaoImpl();
        return dao.delete(userRoom);
    }

    public Map<Integer, Integer> countRooms() {
        List<UserRoom> userRooms = listUserRooms();
        Map<Integer, Integer> roomsSize = new HashMap<>();
        for (UserRoom ur :
                userRooms) {
            if (roomsSize.containsKey(ur.getRoom_id())) {
                int t = roomsSize.get(ur.getRoom_id());
                roomsSize.put(ur.getRoom_id(), t + 1);
            } else {
                roomsSize.put(ur.getRoom_id(), 1);
            }
        }
        return roomsSize;
    }

    private List<UserRoom> listUserRooms() {
        return new UserRoomDaoImpl().selectAll();
    }
}
