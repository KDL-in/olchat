package dao;

import entity.User;
import entity.UserRoom;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserRoomDaoImplTest {

    @org.junit.Test
    public void getMembers() {
        UserRoomDaoImpl dao = new UserRoomDaoImpl();
        List<User> list = dao.getMembers("1");
        for (User u :
                list) {
            System.out.println(u.toString());
        }
    }

    @Test
    public void insert() {
        UserRoomDaoImpl dao = new UserRoomDaoImpl();
        UserRoom ur=new UserRoom();
        ur.setUser_id(3);
        ur.setRoom_id(1);
        System.out.println(dao.insert(ur));
    }

    @Test
    public void select() {
        UserRoomDaoImpl dao = new UserRoomDaoImpl();
        UserRoom ur=new UserRoom();
        ur.setUser_id(2);
        ur.setRoom_id(3);
        System.out.println(dao.select(ur));
    }
}