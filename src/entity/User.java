package entity;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;

public class User implements HttpSessionBindingListener {
    private int id;
    private String user_name;
    private String password;
    private String img_url;
    private int type;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("id: %d,name: %s,password: %s,type: %d,img: %s", id, user_name, password, type, img_url);
    }
    /**
     * HttpSessionBindingListener
     * 当本对象进入session时被调用，这里用于维护共享列表
     */

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        Map<User, HttpSession> userMap = (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
        userMap.put(this, session);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        Map<User, HttpSession> userMap = (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
        userMap.remove(this);
    }

    /**
     * 由于使用了user本身作为key，因此要重写hash和equals方法，防止后期出错
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;//id为特征
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
