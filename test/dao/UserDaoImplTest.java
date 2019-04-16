package dao;

import entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void listUsers() {
        List<User> users = new UserDaoImpl().listUsers();
        System.out.println();
    }

    @Test
    public void select() {
        System.out.println(new UserDaoImpl().select(9));
    }
}