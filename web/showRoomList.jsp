<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/16
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<ul>
    <% int i = 0;%>
    <c:forEach var="room" items="${roomList}">
        <%
            String img_url = String.format("assets/images/chatroom_%d.png", i++ % 3 + 1);
//            System.out.println(img_url);
        %>
        <li class="room-li">
            <input name="cid" type="hidden" value="${room.id}">
            <input name="admin_id" type="hidden"value="${room.admin_id}">
            <a href="#"><img class="am-radius" src="<%=img_url%>" alt=""/></a>
            <div class="content">
                <h3>${room.name}</h3>
                <%--todo z最近聊天记录--%>
                <p>：[详细]${room.id}</p>
            </div>
        </li>
    </c:forEach>
    <c:forEach var="friend" items="${friends}">
        <li class="friend-li">
            <input name="friend_id"class="friend_id" type="hidden" value="${friend.id}">
            <a href="#"><img  src="${friend.img_url}" alt=""/></a>
            <div class="content">
                <h3>${(friend.nickname==""||friend.nickname==null)?friend.user_name:friend.nickname}</h3>
                <p>：[详细]</p>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>
