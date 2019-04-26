package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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

	@Override
	public List<User> selectAll() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user";
		List<User> users = null;
		try {
			users = queryRunner.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User select(int id) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where id = ?";
		try {
			User u = queryRunner.query(sql, new BeanHandler<>(User.class), id);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> find(String keyWord) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where user_name = ?";
		List<User> users = null;
		try {
			users = queryRunner.query(sql, new BeanListHandler<>(User.class), keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> selectAll(int startIndex, int pageSize) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM `user` LIMIT ?,?";
		List<User> users = null;
		try {
			users = queryRunner.query(sql, new BeanListHandler<>(User.class),startIndex,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void delete(int user_id) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from user where id = ?";
		try {
			queryRunner.update(sql, user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User u) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "UPDATE user\n" +
				"set  user_name = ? ,nickname=?,type = ?,img_url=?\n" +
				"where id = ?";
		try {
			queryRunner.update(sql, new Object[]{u.getUser_name(), u.getNickname(),u.getType(), u.getImg_url(), u.getId()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
