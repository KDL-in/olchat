package service;

import dao.UserInfoDao;
import dao.UserInfoDaoImpl;
import entity.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoService {
    private UserInfoDao dao;

    public UserInfoService() {
        this.dao = new UserInfoDaoImpl();
    }


    public Map<String, Integer> countCities() {
        List<UserInfo> infos = listInfo();
        Map<String, Integer> cites = new HashMap<>();
        init(cites);
        for (UserInfo i :
                infos) {
            cites.put(i.getCity(), cites.get(i.getCity()) + 1);
        }
        return cites;
    }

    private void init(Map<String, Integer> cites) {
        cites.put("北京", 0);
        cites.put("天津", 0);
        cites.put("河北", 0);
        cites.put("山西", 0);
        cites.put("内蒙古", 0);
        cites.put("辽宁", 0);
        cites.put("吉林", 0);
        cites.put("黑龙江", 0);
        cites.put("上海", 0);
        cites.put("江苏", 0);
        cites.put("浙江", 0);
        cites.put("安徽", 0);
        cites.put("福建", 0);
        cites.put("江西", 0);
        cites.put("山东", 0);
        cites.put("河南", 0);
        cites.put("湖北", 0);
        cites.put("湖南", 0);
        cites.put("重庆", 0);
        cites.put("青海", 0);
        cites.put("四川", 0);
        cites.put("贵州", 0);
        cites.put("云南", 0);
        cites.put("西藏", 0);
        cites.put("陕西", 0);
        cites.put("甘肃", 0);
        cites.put("宁夏", 0);
        cites.put("新疆", 0);
        cites.put("广东", 0);
        cites.put("广西", 0);
        cites.put("海南", 0);
    }

    private List<UserInfo> listInfo() {
        return dao.selectAll();
    }

    public Map<String, Integer> countSex() {
        List<UserInfo> list = listInfo();
        Map<String, Integer> map = new HashMap<>();
        map.put("男", 0);
        map.put("女", 0);
        for (UserInfo f :
                list) {
            if (f.getSex() == 0) {
                map.put("男", map.get("男") + 1);
            } else {
                map.put("女", map.get("女") + 1);
            }
        }
        return map;
    }
}
