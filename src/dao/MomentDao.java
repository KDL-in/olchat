package dao;

import entity.Moment;

import java.util.List;

public interface MomentDao {
    void insert(Moment moment);

    List<Moment> findMomentBy(int user_id);

    boolean delete(int id);
}
