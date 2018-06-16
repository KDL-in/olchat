package action;

import entity.Chatroom;
import entity.User;
import service.ChatroomService;
import service.UserRoomService;
import utils.BaseServlet;

import javax.servlet.ServletException;
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

    //显示已加入聊天室
    public String showRoomList(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //用service获得所有聊天室
        ChatroomService service = new ChatroomService();
        List<Chatroom>rooms = service.getRooms(req.getParameter("user_id"));
        req.getSession().setAttribute("roomList",rooms);
        req.getRequestDispatcher("/showRoomList.jsp").forward(req, res);
        return null;
    }
}
