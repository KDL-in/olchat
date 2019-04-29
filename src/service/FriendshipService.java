package service;

import dao.FriendshipDao;
import dao.FriendshipDapImpl;
import dao.UserDao;
import entity.Friendship;
import entity.PageBean;
import entity.User;

import java.util.List;

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
}
