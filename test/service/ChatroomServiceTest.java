package service;

import entity.Chatroom;
import entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChatroomServiceTest {

    @Test
    public void find() {
        List<Chatroom> rooms = new ChatroomService().find("聊天室");
        List<User> users = new UserService().find("小");
        System.out.println();
    }
}