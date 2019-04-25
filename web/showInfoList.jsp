<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<%--聊天室--%>
<div class="xinxitj">信息概况</div>
<table class="am-table">
    <thead>
    <tr>
        <th>编号</th>
        <th>管理员</th>
        <th>聊天室名</th>
        <th>总人数</th>
        <th>密码</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${rooms}">
        <tr>
            <td>${r.id}</td>
            <td>${usersBuf[r.admin_id].user_name}</td>
            <%--<td>ss</td>--%>
            <td>${r.name}</td>
            <td>${roomsSize[r.id]}</td>
            <td>${r.password==""?'无':'有'}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table class="am-table">
    <thead>
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>头像</th>
        <th>昵称</th>
        <th>用户类型</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.user_name}</td>
                <%--<td>ss</td>--%>
            <td><img src="${u.img_url}"class = "am-circle"></td>
            <td>${u.nickname==""?"无":u.nickname}</td>
            <td>${u.type==1?"普通用户":"超级管理员"}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
