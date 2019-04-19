package dao;

import entity.Moment;
import org.junit.Test;
import service.MomentService;

import java.util.List;

import static org.junit.Assert.*;

public class MomentDaoImplTest {

    @Test
    public void findMomentBy() {
        List<Moment> list = new MomentService().listMoments(9);
        System.out.println();
    }
}