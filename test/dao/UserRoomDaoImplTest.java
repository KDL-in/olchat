package dao;

import entity.User;

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
}