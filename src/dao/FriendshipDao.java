package dao;

import entity.Friendship;
import entity.User;

import java.util.List;

public interface FriendshipDao {
    Friendship select(int user1_id, int user2_id);

    boolean insert(int user1_id, int user2_id);

    List<User> findFriends(int user_id);

    List<Friendship> selectAll();

    List<Friendship> selectAll(int startIndex, int pageSize);

    void delete(int fs_id);
}
