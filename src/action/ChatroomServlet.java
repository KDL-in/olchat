package action;

import dao.UserDaoImpl;
import entity.ChatRecord;
import entity.Chatroom;
import entity.User;
import org.apache.commons.beanutils.BeanUtils;
import service.*;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


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
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        List<Chatroom> rooms = service.getRooms(req.getParameter("user_id"));
        req.getSession().setAttribute("roomList", rooms);
        FriendshipService friendshipService = new FriendshipService();
        List<User> friends = friendshipService.listFriends(user_id);
        req.getSession().setAttribute("friends", friends);
        req.getRequestDispatcher("/showRoomList.jsp").forward(req, res);
        return null;
    }

    //主页点击进入相应聊天室
    public String enterChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int type = Integer.parseInt(req.getParameter("type"));
        //点击聊天室
        if (type == 0) {
            ChatroomService service = new ChatroomService();
            Chatroom room = service.getRoom(req.getParameter("room_id"));
            req.getSession().setAttribute("curChatroom", room);
            req.getSession().setAttribute("curSession",room.getName());
            res.sendRedirect(req.getContextPath() + "/chatroom.jsp");
            return null;
        }
        //点击好友
        int id = Integer.parseInt(req.getParameter("user_id"));
        User u = new UserService().getUser(id);
        String curSession = (u.getNickname() == null || u.getNickname().equals("")) ? u.getUser_name() : u.getNickname();
        System.out.println(u);
        req.getSession().setAttribute("curSession",curSession );
        res.getWriter().println(curSession);
        return null;
    }

    //显示某聊天室聊天记录
    public String showChatRecords(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String start=req.getParameter("startDate");
        String end = req.getParameter("endDate");
        String room_id = req.getParameter("room_id");
        ChatRecordService service = new ChatRecordService();
        List<ChatRecord> records = service.getRecordsBetween(room_id,start,end);
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

    //创建群
    public String createChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //创建实体,创建群
        Chatroom chatroom = new Chatroom();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(chatroom,map);
            ChatroomService service = new ChatroomService();
            service.create(chatroom);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }
    //踢出用户
    public String deleteMember(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        UserRoomService service = new UserRoomService();
        service.removeUserFrom(req.getParameter("user_id"), req.getParameter("room_id"));

        return null;
    }
    //被t出群
    public String exitChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getSession().setAttribute("curChatroom", null);
        return null;
    }

    //添加新好友
    public String addNewFriend(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int user1_id = Integer.parseInt(req.getParameter("user1_id")), user2_id = Integer.parseInt(req.getParameter("user2_id"));
        FriendshipService friendshipService = new FriendshipService();
        res.getWriter().println(friendshipService.makeFriend(user1_id, user2_id)?"添加成功":"添加失败");
        return null;
    }

}
