package dao;

import entity.User;
import org.junit.Test;

import java.util.List;

public class UserDaoImplTest {

    @Test
    public void listUsers() {
        List<User> users = new UserDaoImpl().selectAll();
        System.out.println();
    }

    @Test
    public void select() {
        System.out.println(new UserDaoImpl().select(9));
    }

    @Test
    public void find() {
        List<User> list = new UserDaoImpl().find("xiaohong");
        System.out.println();
    }
}