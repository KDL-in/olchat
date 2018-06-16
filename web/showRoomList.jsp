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
    <c:forEach var="room" items="${roomList}">
        <li>
            <input name="cid" type="hidden" value="${room.id}">
            <a href="#"><img src="${pageContext.request.contextPath}/assets/images/1.png" alt=""/></a>
            <div class="content">
                <h3>${room.name}</h3>
                <p>：[动画表情]</p>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>
