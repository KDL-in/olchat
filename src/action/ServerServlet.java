package action;

import com.google.gson.Gson;
import entity.Chatroom;
import entity.Friendship;
import entity.PageBean;
import entity.User;
import service.*;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerServlet extends BaseServlet {
    //首页
    //更新首页的一些数据
    public String getInfoData(HttpServletRequest req, HttpServletResponse response) throws IOException {
//        System.out.println("here");
        response.setContentType("text/html;charset=utf-8");
        ChatroomService roomService = new ChatroomService();
        UserRoomService urService = new UserRoomService();
        UserService uService = new UserService();
        ChatRecordService recordService = new ChatRecordService();
        //一些统计
        //-总用户数
        int nOfUsers = uService.countUsers();
        int nOfRecords = recordService.countRecords();
        int nOfRooms = roomService.countRooms();
        int nOfImg = recordService.countImg();
        Map<String, Integer> map = new HashMap<>();
        map.put("nOfUsers", nOfUsers);
        map.put("nOfRecords", nOfRecords);
        map.put("nOfRooms", nOfRooms);
        map.put("nOfImgs", nOfImg);
        Gson gson = new Gson();
        String str = gson.toJson(map);
//        System.out.println(str);
        response.getWriter().println(str);
        return null;
    }
    //更新首页列表
    public String getInfoList(HttpServletRequest req, HttpServletResponse response) {
        ChatroomService roomService = new ChatroomService();
        UserRoomService urService = new UserRoomService();
        List<Chatroom> rooms = roomService.listRooms();
        List<User> users = new UserService().listUsers();
        req.setAttribute("rooms", rooms);
        req.setAttribute("users", users);
        //一些统计
        //-聊天室人数
        Map<Integer, Integer> roomsSize = urService.countRooms();
        req.setAttribute("roomsSize", roomsSize);

        return "/showInfoList.jsp";
    }
    /**
     * 用户管理
     */
    //分页列表
    public String getUserListOfPage(HttpServletRequest req, HttpServletResponse response) {
        int pageNum = Integer.parseInt(req.getParameter("pageNum")), pageSize = 5;
        UserService  us=new UserService();
        PageBean pb = us.findAllUserOfPage(pageNum, pageSize);
        req.setAttribute("pageBean", pb);
        return "/jsp/showUserListOfPage.jsp";
    }
    //删除用户
    public String deleteUser(HttpServletRequest req, HttpServletResponse response) {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        UserService service = new UserService();
        service.deleteUser(user_id);
        return null;
    }
    //修改用户
    public String modUser(HttpServletRequest req, HttpServletResponse response) throws IOException {
        UserService us=new UserService();
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id")),
            type = Integer.parseInt(req.getParameter("type"));
        String user_name = req.getParameter("user_name"),
                nickname = req.getParameter("nickname"),
                img_url = req.getParameter("img_url");
        us.modUser(id,user_name,nickname,img_url,type);
//        response.sendRedirect(req.getContextPath()+"/server_um.jsp");
        return null;
    }
    /*
    * 聊天室管理
    * */
    //分页列表
    public String getRoomListOfPage(HttpServletRequest req, HttpServletResponse response) {
        int pageNum = Integer.parseInt(req.getParameter("pageNum")), pageSize = 5;
        ChatroomService  rs=new ChatroomService();
        PageBean pb = rs.findAllUserOfPage(pageNum, pageSize);
        req.setAttribute("pageBean", pb);
        return "/jsp/showRoomListOfPage.jsp";
    }
    //删除用户
    public String deleteRoom(HttpServletRequest req, HttpServletResponse response) {
        int room_id = Integer.parseInt(req.getParameter("room_id"));
        ChatroomService service = new ChatroomService();
        service.deleteRoom(room_id);
        return null;
    }
    //修改用户
    public String modRoom(HttpServletRequest req, HttpServletResponse response) throws IOException {
        ChatroomService s=new ChatroomService();
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id")),admin_id = Integer.parseInt(req.getParameter("admin_id"));
        String name = req.getParameter("name"),
                password = req.getParameter("password");
        s.modRoom(id,name,password,admin_id);
//        response.sendRedirect(req.getContextPath()+"/server_um.jsp");
        return null;
    }
    /*
    * 好友关系管理
    * */
    //分页列表
    public String getFriendshipListOfPage(HttpServletRequest req, HttpServletResponse response) {
        int pageNum = Integer.parseInt(req.getParameter("pageNum")), pageSize = 5;
        FriendshipService s = new FriendshipService();
        PageBean pb = s.findAllUserOfPage(pageNum, pageSize);
        req.setAttribute("pageBean", pb);
        return "/jsp/showFriendshipListOfPage.jsp";
    }
    //删除关系
    public String deleteFriendship(HttpServletRequest req, HttpServletResponse response) {
        int fs_id = Integer.parseInt(req.getParameter("fs_id"));
        FriendshipService service = new FriendshipService();
        service.deleteFriendship(fs_id);
        return null;
    }
}
