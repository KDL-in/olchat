package service;

import dao.MomentDao;
import dao.MomentDaoImpl;
import entity.Moment;

import java.sql.Timestamp;
import java.util.List;

public class MomentService {
    MomentDao dao;

    public MomentService() {
        dao = new MomentDaoImpl();
    }

    public void addNewMoment(String content, int user_id, String img_url, Timestamp time) {
        Moment moment = new Moment();
        moment.setContent(content);
        moment.setImg_url("");
        moment.setLikes(0);
        moment.setTime(time);
        moment.setImg_url(img_url);
        moment.setUser_id(user_id);
        MomentDao dao = new MomentDaoImpl();
        dao.insert(moment);
    }


    public List<Moment> listMoments(int user_id) {
        MomentDao dao = new MomentDaoImpl();
        return dao.selectAllRelated(user_id);
    }

    public boolean deleteMoment(int id) {
        MomentDao dao = new MomentDaoImpl();
        return dao.delete(id);
    }

    public List<Moment> listMomentsOf(int cur_user_id) {
        return dao.selectAllBy(cur_user_id);
    }
}
