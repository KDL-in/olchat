package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipServiceTest {

    @Test
    public void getFriendshipGraph() {
        new FriendshipService().getFriendshipGraph();
    }
}