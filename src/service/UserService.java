package service;


import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UserService {

    public User getUser(User user) {
        UserDao dao = new UserDaoImpl();
        return dao.login(user);
    }


    public boolean register(String user_name, String password) {
        User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);
        User r = getUser(user);
        if(r!=null) return false;
        UserDao dao = new UserDaoImpl();
        return dao.insert(user);
    }
}
