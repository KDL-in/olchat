package dao;

import entity.Comment;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CommentDaoImplTest {

    @Test
    public void list() {
        List<Comment> list = new CommentDaoImpl().list(7);
        System.out.println(list);
    }
}