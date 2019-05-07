package action;

import entity.Comment;
import entity.Moment;
import entity.User;
import service.CommentService;
import service.MomentService;
import service.UserService;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MomentServlet extends BaseServlet {
    public String publicMoment(HttpServletRequest req, HttpServletResponse resp) {
        String txt = req.getParameter("txt");
        String img_url = req.getParameter("img_url");
        User user = (User) req.getSession().getAttribute("curUser");
        Moment moment = new Moment();
        MomentService service = new MomentService();
        service.addNewMoment(txt, user.getId(), img_url, new Timestamp(System.currentTimeMillis()));
        return null;
    }

    public String publicComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String content = req.getParameter("content");
        int user_id = Integer.parseInt(req.getParameter("user_id")),
                reply_id = Integer.parseInt(req.getParameter("reply_id")),
                moment_id = Integer.parseInt(req.getParameter("moment_id"));
        CommentService service = new CommentService();
        service.addComment(user_id, reply_id, moment_id, content, new Timestamp(System.currentTimeMillis()));
        resp.getWriter().println("finish!");
        return null;
    }

    public String getMomentList(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("curUser");
        List<Moment> moments = new MomentService().listMoments(user.getId());
        req.setAttribute("moments", moments);
        return "/showMomentList.jsp";
    }

    public String getMomentListOf(HttpServletRequest req, HttpServletResponse resp) {
        List<Moment> moments = new MomentService().listMomentsOf(Integer.parseInt(req.getParameter("cur_user_id")));
        req.setAttribute("moments", moments);
        return "/showMomentList.jsp";
    }

    public String getCommentList(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("moment_id"));
        List<Comment> comments = new CommentService().listComments(id);
        req.setAttribute("comments", comments);
        return "/showCommentList.jsp";
    }

    public String deleteMoment(HttpServletRequest req, HttpServletResponse resp) {
        int moment_id = Integer.parseInt(req.getParameter("moment_id"));
        MomentService service = new MomentService();
        service.deleteMoment(moment_id);
        return null;
    }

    public String deleteComment(HttpServletRequest req, HttpServletResponse resp) {
        int comment_id = Integer.parseInt(req.getParameter("comment_id"));
        CommentService service = new CommentService();
        service.deleteComment(comment_id);
        return null;
    }

    public String sendIntro(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String intro = req.getParameter("intro");
        int id = Integer.parseInt(req.getParameter("user_id"));
        UserService service = new UserService();
        service.modIntro(id, intro);
        User u = (User) req.getSession().getAttribute("curUser");
        u.setIntro(intro);
        req.getSession().setAttribute("curUser", u);
        return null;
    }

    public String modNickname(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String nickname = req.getParameter("nickname");
        int id = Integer.parseInt(req.getParameter("user_id"));
        UserService service = new UserService();
        service.modNickname(id, nickname);
        User u = (User) req.getSession().getAttribute("curUser");
        u.setNickname(nickname);
        req.getSession().setAttribute("curUser", u);
        return null;
    }
}
