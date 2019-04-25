package dao;

import entity.User;
import entity.UserRoom;

import java.util.List;

public interface UserRoomDao {
    List<User> getMembers(String cid);

    boolean insert(UserRoom dao);

    UserRoom select(UserRoom userRoom);

    boolean delete(UserRoom userRoom);

    List<UserRoom> selectAll();
}
