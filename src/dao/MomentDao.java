package dao;

import entity.Moment;

import java.util.List;

public interface MomentDao {
    void insert(Moment moment);

    List<Moment> selectAllRelated(int user_id);

    boolean delete(int id);

    List<Moment> selectAllBy(int cur_user_id);
}
