package action;

import entity.ChatRecord;
import entity.Chatroom;
import entity.User;
import entity.UserRoom;
import service.ChatRecordService;
import service.ChatroomService;
import service.UserRoomService;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


public class ChatroomServlet extends BaseServlet {
    public String updateData(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //使用service层访问获取某id聊天室所有成员
        UserRoomService service = new UserRoomService();
        String cid = req.getParameter("room_id");
        List<User> members = service.getMembers(cid);
        req.getSession().setAttribute("members", members);
        return null;
    }

    //显示已加入聊天室
    public String showRoomList(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //用service获得所有聊天室
        ChatroomService service = new ChatroomService();
        List<Chatroom> rooms = service.getRooms(req.getParameter("user_id"));
        req.getSession().setAttribute("roomList", rooms);
        req.getRequestDispatcher("/showRoomList.jsp").forward(req, res);
        return null;
    }

    //主页点击进入相应聊天室
    public String enterChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ChatroomService service = new ChatroomService();
        Chatroom room = service.getRoom(req.getParameter("room_id"));
        req.getSession().setAttribute("curChatroom", room);
        res.sendRedirect(req.getContextPath() + "/chatroom.jsp");
        return null;
    }

    //显示某聊天室聊天记录
    public String showChatRecords(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ChatRecordService service = new ChatRecordService();
        List<ChatRecord> records = service.getRecentlyRecords(req.getParameter("room_id"));
        req.getSession().setAttribute("records", records);
        req.getRequestDispatcher("/showChatRecords.jsp").forward(req, res);
        return null;
    }

    //发送消息，插入数据库
    public String sendMessage(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //构造实体
        Chatroom c= (Chatroom) req.getSession().getAttribute("curChatroom");
        User u= (User) req.getSession().getAttribute("curUser");
        ChatRecord cr = new ChatRecord();
        cr.setContent(req.getParameter("txt"));
        cr.setTime(new Timestamp(System.currentTimeMillis()));
        cr.setRoom_id(c.getId());
        cr.setType(Integer.parseInt(req.getParameter("type")));
        cr.setTarget_name(req.getParameter("target_name"));
        cr.setUser_id(u.getId());
        cr.setUser(u.getUser_name());
        //用service插入数据
        ChatRecordService service = new ChatRecordService();
        service.insertMessage(cr);
        return null;
    }

    //搜索群
    public String searchChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ChatroomService service = new ChatroomService();
        Chatroom chatroom =service.search(req.getParameter("keyWord"));
        if (chatroom != null) {
            req.getSession().setAttribute("existRoom", chatroom);
            res.getWriter().write("true");
        }
        res.getWriter().write("false");
        return null;
    }
    //尝试加入群
    public String addToChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Chatroom existRoom = (Chatroom) req.getSession().getAttribute("existRoom");
        String psw = req.getParameter("room_psw");
        req.getSession().setAttribute("existRoom", null);
        //校对密码
        if (psw.compareTo(existRoom.getPassword()) != 0) {
            res.getWriter().write("Wrong Password");
            return null;
        }
        //检测是否已在聊天室中
        UserRoomService service  = new UserRoomService();
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        if (service.isExisted(user_id, existRoom.getId())) {
            res.getWriter().write("Had been a member!");
            return null;
        }
        service.addToChatroom(user_id,existRoom.getId());
        res.getWriter().write("Wecome to join " + existRoom.getName());
        return null;
    }


}
