package dao;

import entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FriendshipDapImplTest {

    @Test
    public void findFriends() {
        List<User> friends = new FriendshipDapImpl().findFriends(9);
        System.out.println();
    }
}