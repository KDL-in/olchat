package dao;

import entity.User;

import java.util.List;

public interface UserRoomDao {
    List<User> getMembers(String cid);
}
