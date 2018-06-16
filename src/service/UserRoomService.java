package service;

import dao.UserRoomDao;
import dao.UserRoomDaoImpl;
import entity.User;

import java.util.List;

public class UserRoomService {
    public List<User> getMembers(String cid) {
        UserRoomDao dao = new UserRoomDaoImpl();
        return dao.getMembers(cid);
    }

}
