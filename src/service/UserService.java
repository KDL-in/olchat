package service;


import dao.UserDao;
import dao.UserDaoImple;
import entity.User;

public class UserService {

	public User login(User user) {
		UserDao dao = new UserDaoImple();
		return dao.login(user);
	}
	
	
}
