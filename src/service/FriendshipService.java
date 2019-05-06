package service;

import dao.FriendshipDao;
import dao.FriendshipDapImpl;
import entity.FNode;
import entity.Friendship;
import entity.PageBean;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendshipService {
    FriendshipDao dao;

    public FriendshipService() {
        this.dao = new FriendshipDapImpl();
    }

    public boolean makeFriend(int user1_id, int user2_id) {
        if (user1_id > user2_id) {
            int temp = user1_id;
            user1_id = user2_id;
            user2_id = temp;
        }
        Friendship r = find(user1_id, user2_id);
        if(r!=null||user1_id==user2_id) return false;
        return new FriendshipDapImpl().insert(user1_id, user2_id);
    }

    private Friendship find(int user1_id, int user2_id) {
        if (user1_id > user2_id) {
            int temp = user1_id;
            user1_id = user2_id;
            user2_id = temp;
        }
        return new FriendshipDapImpl().select(user1_id,user2_id);
    }

    public List<User> listFriends(int user_id) {
        FriendshipDao friendshipDao = new FriendshipDapImpl();
        return friendshipDao.findFriends(user_id);
    }


    public List<Friendship> listFriendShips() {
        return new FriendshipDapImpl().selectAll();
    }

    public PageBean findAllUserOfPage(int pageNum, int pageSize) {
        List<Friendship> fss = dao.selectAll();
        PageBean<Friendship> pb = new PageBean<>(pageNum, pageSize, fss);
        pb.setList(dao.selectAll(pb.getStartIndex(), pb.getPageSize()));
        return pb;
    }

    public void deleteFriendship(int fs_id) {
        dao.delete(fs_id);
    }

    public void deleteFriendship(int user1_id, int user2_id) {
        Friendship friendship = find(user1_id, user2_id);
        dao.delete(friendship.getId());
    }

    public List<FNode> getFriendshipGraph() {
        List<Friendship> list = dao.selectAll();
        List<User> users = new UserService().listUsers();
        //user_id to user_name
        Map<Integer, String> userIdToName = new HashMap<>();
        for (User u :
                users) {
            userIdToName.put(u.getId(), u.getUser_name());
        }
        //建立邻接链表
        Map<String, Integer> nameToIdx = new HashMap<>();
        List<FNode> g = new ArrayList();
        for (Friendship f :
                list) {
            int id1 = f.getUser1_id(), id2 = f.getUser2_id();
            String name1 = userIdToName.get(id1), name2 = userIdToName.get(id2);
            if (!nameToIdx.containsKey(name1)) {
                addToMap(name1, g, nameToIdx);
            }
            if (!nameToIdx.containsKey(name2)) {
                addToMap(name2, g, nameToIdx);
            }
            addToGraph(nameToIdx.get(name1), name2,g);
            addToGraph(nameToIdx.get(name2), name1,g);
        }
        return g;
    }

    private void addToGraph(int idx, String name, List<FNode> g) {
        FNode fNode = new FNode(name);
        FNode head = g.get(idx);
        fNode.next = head.next;
        head.next = fNode;
    }

    private void addToMap(String name, List<FNode> g, Map<String, Integer> nameToIdx) {
        FNode head = new FNode(name);
        g.add(head);
        nameToIdx.put(name, g.size() - 1);
    }



}
