package action;

import com.google.gson.Gson;
import entity.Chatroom;
import entity.Friendship;
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
}
