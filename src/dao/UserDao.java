package dao;

import entity.User;

import java.util.List;

public interface UserDao {

	public User login(User user);

    boolean insert(User user);

    List<User> selectAll();

    User select(int id);

    List<User> find(String keyWord);

    List<User> selectAll(int startIndex, int pageSize);

    void delete(int user_id);

    void update(User u);

    void updateIntro(User u);

    void updateNickname(int id, String nickname);
}
