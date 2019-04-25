package service;


import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

import java.util.List;

public class UserService {
    private UserDao dao;
    public UserService() {
        dao = new UserDaoImpl();
    }

    public User getUser(User user) {
        UserDao dao = new UserDaoImpl();
        return dao.login(user);
    }


    public boolean register(String user_name, String password, String img_url) {
        User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);
        user.setImg_url(img_url);
        User r = getUser(user);
        if(r!=null) return false;
        UserDao dao = new UserDaoImpl();
        return dao.insert(user);
    }

    public User getUser(int id) {
        return new UserDaoImpl().select(id);
    }

    public List<User> find(String keyWord) {
        return new UserDaoImpl().find(keyWord);
    }

    public List<User> listUsers() {
        return new UserDaoImpl().listUsers();
    }

    public int countUsers() {
        List<User> users = dao.listUsers();
        return users.size();
    }
}
