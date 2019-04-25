package action;

import entity.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import utils.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Random;


public class LoginServlet extends BaseServlet {

    //默认跳转方法，测试之用
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println("excute successed");
    }


    /**
     * 登录
     */
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        // 接收提交数据
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {

            // userService 对象是否存在
            BeanUtils.populate(user, map);
            UserService us = new UserService();
            User curUser = us.getUser(user);

            if (curUser == null) {// 失败
                req.setAttribute("msg", "用户名或密码错误!");
                return "/login.jsp";
            } else {//成功
                //重置session


                req.getSession().invalidate();
                //同一个用户重复登陆，应该清除之前的session，使得对方下线
                //-原理是在共享的 userMap中，用user可以得到相应的session引用
                //-所以只需要，将某user的session取出清空，就可以使得它不存在而下线
                //-不清空同账号的session的结果是，userMap中存放的是当前的user的session而，上一次的登陆的session没法操作
                Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext().getAttribute("userMap");
                if (userMap.containsKey(curUser)) {
                    HttpSession session = userMap.get(curUser);
                    session.invalidate();
                }
                req.getSession().setAttribute("curUser", curUser);
                if (curUser.getType() == 1)
                    resp.sendRedirect(req.getContextPath() + "/main.jsp");
                else if (curUser.getType() == 2) {
                    resp.sendRedirect(req.getContextPath() + "/server_index.jsp");
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退出聊天室
     */
    public String exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return null;
    }

    /**
     * 注册
     */
    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService service = new UserService();
        String img_url = String.format("assets/images/header/%d.png", new Random().nextInt(30) + 1);
        boolean suc = service.register(request.getParameter("user_name"), request.getParameter("password"), img_url);
        if (suc) {
            login(request, response);
        } else {
            request.setAttribute("msg", "注册失败");
            return "/register.jsp";
        }
        return null;
    }
}
