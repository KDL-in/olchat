package listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import entity.User;

/**
 * 本机ServletContext对象创建时，创建userMap，所有用户都是使用这个loginServlet 因此，userMap相当于共享的资源
 *
 */
public class MyServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		Map<User,HttpSession> userMap = new HashMap<User,HttpSession>();
		sce.getServletContext().setAttribute("userMap", userMap);
		//维护用户列表
		List<User> users = new UserDaoImpl().listUsers();
		Map<Integer,User> usersBuf = new HashMap<>();
		for (User u :
				users) {
//			userBuf.put(u.getId(), u);
			usersBuf.put(u.getId(), u);
		}
		sce.getServletContext().setAttribute("usersBuf",usersBuf);
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}



}
