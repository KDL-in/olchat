package action;

import entity.User;
import service.UserRoomService;
import utils.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ChatroomServlet extends BaseServlet {
    public String updateData(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //使用service层访问获取某id聊天室所有成员
        UserRoomService service = new UserRoomService();
        String cid = req.getParameter("cid");
        List<User> members= service.getMembers(cid);
        req.getSession().setAttribute("members", members);
        return null;
    }
}
