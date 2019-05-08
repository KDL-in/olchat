package action;

import com.sun.glass.ui.Size;
import dao.UserDaoImpl;
import entity.*;
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
        int sendTo = Integer.parseInt(req.getParameter("sendTo"));
        //点击聊天室
        if (sendTo == 0) {
            ChatroomService service = new ChatroomService();
            Chatroom room = service.getRoom(req.getParameter("room_id"));
            req.getSession().setAttribute("curChatroom", room);
            req.getSession().setAttribute("curSession",room.getName());
            res.sendRedirect(req.getContextPath() + "/chatroom.jsp");
            return null;
        }
        //点击好友
        int id = Integer.parseInt(req.getParameter("friend_id"));
        User friend = new UserService().getUser(id);
        String curSession = (friend.getNickname() == null || friend.getNickname().equals("")) ? friend.getUser_name() : friend.getNickname();
//        System.out.println(u);
        req.getSession().setAttribute("curSession",curSession );
        req.getSession().setAttribute("friend_id", friend.getId());
        req.getSession().setAttribute("user_id", req.getParameter("user_id"));
        res.getWriter().println(curSession);
        return null;
    }

    //显示某聊天室聊天记录
    public String showChatRecords(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String start=req.getParameter("startDate");
        String end = req.getParameter("endDate");
        int type = Integer.parseInt(req.getParameter("type"));
        List<ChatRecord> records;
        ChatRecordService service = new ChatRecordService();
        if (type < 5) {
            String room_id = req.getParameter("room_id");
            records = service.getRecordsBetween(room_id, start, end);
        } else {
            int user_id = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
            int friend_id = Integer.parseInt(req.getSession().getAttribute("friend_id").toString());
            records = service.getRecordsBetween(user_id, friend_id, start, end);
//            System.out.println("records:"+ records.size());
        }

        req.getSession().setAttribute("records", records);
        req.getRequestDispatcher("/showChatRecords.jsp").forward(req, res);
        return null;
    }

    //发送消息，插入数据库
    public String sendMessage(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int type = Integer.parseInt(req.getParameter("type"));//消息发送类型，0 1代表发给聊天室 5 6代表发给好友
        //构造实体
        User u= (User) req.getSession().getAttribute("curUser");
        ChatRecord cr = new ChatRecord();
        cr.setContent(req.getParameter("txt"));
        cr.setTime(new Timestamp(System.currentTimeMillis()));
        cr.setType(Integer.parseInt(req.getParameter("type")));
        cr.setUser_id(u.getId());
        cr.setUser(u.getUser_name());
        cr.setTarget_name(req.getParameter("target_name"));
        if (type < 5) {//发给聊天室
            Chatroom c = (Chatroom) req.getSession().getAttribute("curChatroom");
            cr.setRoom_id(c.getId());
        } else {//发给好友
            cr.setFriend_id(Integer.parseInt(req.getSession().getAttribute("friend_id").toString()));
        }
        //用service插入数据
        ChatRecordService service = new ChatRecordService();
        service.insertMessage(cr);
        return null;
    }

    //搜索群和成员cur
    public String searchChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        List<Chatroom> rooms = new ChatroomService().find(req.getParameter("keyWord"));
        List<User> users = new UserService().find(req.getParameter("keyWord"));
        req.getSession().setAttribute("searchRooms", rooms);
        req.getSession().setAttribute("searchUsers", users);
        req.getRequestDispatcher("showResult.jsp").forward(req, res);
        return null;
    }
    //尝试加入群
    public String addToChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String psw = req.getParameter("room_psw");
        Chatroom existRoom = new ChatroomService().getRoom(req.getParameter("room_id"));
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
        res.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();
        boolean flag = false;
        try {
            BeanUtils.populate(chatroom,map);
            ChatroomService service = new ChatroomService();
            flag = service.create(chatroom);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
//            System.out.println(1);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        res.getWriter().println(flag?"创建成功":"创建失败");
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

    //退出群
    public String exitFromChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int room_id = Integer.parseInt(req.getParameter("room_id"));
        UserRoomService service = new UserRoomService();
        service.delete(user_id, room_id);
        return null;
    }
    //删除群
    public String deleteChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int room_id = Integer.parseInt(req.getParameter("room_id"));
        ChatroomService service = new ChatroomService();
        service.deleteRoom(room_id);
        return null;
    }
    //删除关系
    public String deleteFriendship(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int user1_id = Integer.parseInt(req.getParameter("user1_id"));
        int user2_id = Integer.parseInt(req.getParameter("user2_id"));
        FriendshipService service = new FriendshipService();
        service.deleteFriendship(user1_id,user2_id);
        return null;
    }

    //修改聊天室名
    public String modRoomName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        int room_id = Integer.parseInt(req.getParameter("room_id"));
        ChatroomService service  = new ChatroomService();
        boolean flag = service.modRoom(room_id,name);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(flag?"修改成功":"修改失败");
        return null;
    }
    //添加一个朋友
    public String addFriendTo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id")),
                room_id = Integer.parseInt(req.getParameter("room_id"));
        UserRoomService service = new UserRoomService();
        service.addToChatroom(user_id, room_id);
        return null;
    }



}
