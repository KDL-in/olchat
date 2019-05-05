package service;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserInfoServiceTest {

    @Test
    public void countCities() {
        Map<String, Integer> cities = new UserInfoService().countCities();
        System.out.println();
    }
}