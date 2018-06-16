package listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import entity.User;

/**
 * 本机ServletContext对象创建时，创建userMap，所有用户都是使用这个loginServlet 因此，userMap相当于共享的资源
 *
 */
public class MyServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		Map<User,HttpSession> userMap = new HashMap<User,HttpSession>();
		sce.getServletContext().setAttribute("userMap", userMap);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}



}
