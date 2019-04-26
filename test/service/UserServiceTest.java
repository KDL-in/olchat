package service;

import entity.PageBean;
import entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void findAllUserOfPage() {
        UserService userService = new UserService();
        PageBean<User>pb=userService.findAllUserOfPage(2,4);
        System.out.println();
    }
}