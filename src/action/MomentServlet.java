package action;

import entity.Moment;
import entity.User;
import service.MomentService;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class MomentServlet extends BaseServlet {
    public String publicMoment(HttpServletRequest req, HttpServletResponse resp) {
        String txt = req.getParameter("txt");
        User user = (User) req.getSession().getAttribute("curUser");
        Moment moment = new Moment();
        MomentService service = new MomentService();
        service.addNewMoment(txt,user.getId(),new Timestamp(System.currentTimeMillis()));
        return null;
    }
}
