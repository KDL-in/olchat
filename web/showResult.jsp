<%@ page import="entity.Chatroom" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 2019-4-16
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<ul>
    <% int i = 0;%>
    <c:forEach var="room" items="${searchRooms}">
        <%
            String img_url = String.format("assets/images/chatroom_%d.png", i++ % 3 + 1);
//            System.out.println(img_url);
        %>
        <li class="room-li">
            <input class="room_id" type="hidden" value="${room.id}">
            <a href="#"><img class="am-radius" src="<%=img_url%>" alt=""/></a>
            <div class="content">
                <h3>${room.name}</h3>
                    <%--todo z最近聊天记录--%>
                <p>：[动画表情]</p>
            </div>
        </li>
    </c:forEach>
    <c:forEach var="friend" items="${searchUsers}">
        <li class="friend-li">
            <input class="friend_id" type="hidden" value="${friend.id}">
            <a href="#"><img  src="${friend.img_url}" alt=""/></a>
            <div class="content">
                <h3>${(friend.nickname=="")?friend.user_name:friend.nickname}</h3>
                <p>：[动画表情]</p>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>