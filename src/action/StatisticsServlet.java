package action;

import com.google.gson.Gson;
import entity.FNode;
import entity.UserInfo;
import service.FriendshipService;
import service.UserInfoService;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StatisticsServlet extends BaseServlet {
    public String placeStatistic(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        rep.setCharacterEncoding("utf-8");
        UserInfoService service = new UserInfoService();
        Map<String, Integer> cityToNum = service.countCities();
        Gson gson = new Gson();
        rep.getWriter().println(gson.toJson(cityToNum));

        return null;
    }

    public String sexualStatistic(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        rep.setCharacterEncoding("utf-8");
        UserInfoService service = new UserInfoService();
        Map<String, Integer> sexToNum = service.countSex();
        Gson gson = new Gson();
        rep.getWriter().println(gson.toJson(sexToNum));

        return null;
    }

    public String friendshipStatistic(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        System.out.println("here");
        rep.setCharacterEncoding("utf-8");
        FriendshipService service = new FriendshipService();
        List<FNode> g = service.getFriendshipGraph();
        Gson gson = new Gson();
        rep.getWriter().println(gson.toJson(g));
        System.out.println("here2");
        return null;
    }
}
