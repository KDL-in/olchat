package service;


import dao.UserDao;
import dao.UserDaoImpl;
import entity.PageBean;
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
        return new UserDaoImpl().selectAll();
    }

    public int countUsers() {
        List<User> users = dao.selectAll();
        return users.size();
    }
    //取得该页的用户并且返回pagebean供使用
    public PageBean<User> findAllUserOfPage(int pageNum, int pageSize) {
        List<User> users = dao.selectAll();
        PageBean<User> pb = new PageBean<User>(pageNum, pageSize, users);
        pb.setList(dao.selectAll(pb.getStartIndex(),pb.getPageSize()));
        return pb;
    }

    public void deleteUser(int user_id) {
        dao.delete(user_id);
    }

    public void modUser(int id, String user_name, String nickname, String img_url, int type) {
        User u = new User();
        u.setId(id);
        u.setUser_name(user_name);
        u.setNickname(nickname);
        u.setImg_url(img_url);
        u.setType(type);
        dao.update(u);
    }
}
