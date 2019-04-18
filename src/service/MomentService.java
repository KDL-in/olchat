package service;

import dao.MomentDao;
import dao.MomentDaoImpl;
import entity.Moment;

import java.sql.Timestamp;

public class MomentService {
    public void addNewMoment(String content, int user_id, Timestamp time) {
        Moment moment = new Moment();
        moment.setContent(content);
        moment.setImg_url("");
        moment.setLikes(0);
        moment.setTime(time);
        moment.setUser_id(user_id);
        MomentDao dao = new MomentDaoImpl();
        dao.insert(moment);
    }
}
