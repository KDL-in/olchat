<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/16
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<ul>
    <c:forEach var="user" items="${members}">
        <c:choose>
            <c:when test="${userMap.containsKey(user)}">
                <li class="onlineMember">
            </c:when>
            <c:otherwise>
                <li class="offlineMember">
            </c:otherwise>
        </c:choose>
        <input name="cur_user_id" type="hidden" value="${user.id}">
        <a href="#">
            <img class="am-radius" src="${user.img_url}"/>
        </a>
        <div class="content">
            <h3>${user.user_name}</h3>
            <p>：[动画表情]</p>
        </div>
        </li>
    </c:forEach>

</ul>

</body>
</html>
