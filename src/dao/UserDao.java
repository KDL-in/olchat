package dao;

import entity.User;

import java.util.List;

public interface UserDao {

	public User login(User user);

    boolean insert(User user);

    List<User> listUsers();

    User select(int id);

    List<User> find(String keyWord);
}
