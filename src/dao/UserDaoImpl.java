package dao;

import java.sql.SQLException;

import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public User login(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where user_name = ? and password = ?";
		User existUser;
		try {
			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUser_name(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("用户登录失败!");
		}
		return existUser;
	}

	@Override
	public boolean insert(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "INSERT INTO `user` (user_name,password,img_url)VALUES (?, ?, ?)";
		try {
			queryRunner.update(sql, new Object[]{user.getUser_name(), user.getPassword(),user.getImg_url()});
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
