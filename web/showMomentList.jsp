<%@ page import="entity.Moment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 2019-4-19
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<%
    System.out.println("here");
%>
<c:forEach var="m" items="${moments}">
    <section class="content">
        <input type="hidden" name="moment_id" value="${m.id}">
        <input type="hidden" name="moment_user_id" value="${m.user_id}">
        <img class="head_img" src="${usersBuf[m.user_id].img_url}">
        <div class="contain">
                <%--todo--%>
            <p class="owner">${usersBuf[m.user_id].nickname!=""?usersBuf[m.user_id].nickname:usersBuf[m.user_id].user_name}</p>
            <p class="text">${m.content}</p>
            <c:if test="${m.img_url!=''}">
                <div class="images">
                    <img src="${m.img_url}">
                </div>
            </c:if>
            <div class="comment">
                <div class="like">
                    <i class="unagree"></i>
                    <p class="">${m.likes}</p>
                </div>

                <div class="discuss">
                    <i></i>
                    <p>评论</p>
                </div>
                <c:if test="${curUser.id==m.user_id}">
                    <div class="dele">
                        <i></i>
                        <p>删除</p>
                    </div>
                </c:if>
            </div>
            <p class="time">${m.time}</p>
            <ul class="dis_cont">
                <li>
                    <a class="dis_name" href="javascript:void(0)">我: </a><input type="text" placeholder="Add your comment here!"class="commentSender" >
                </li>
                <li>
                    <a class="dis_name" href="javascript:void(0)">
                        <span>星星点灯</span>
                    </a>
                    <span>:风景很不错，什么时候回来！</span>
                </li>
                    <%--<li><a class="dis_name" href="javascript:void(0)">小红</a>:了解从未接触过的领域。</li>--%>
                    <%--<li><a class="dis_name" href="javascript:void(0)">小明</a>回复<a class="dis_name" href="javascript:void(0)">小红</a>:有内涵~</li>--%>
                    <%--<li><a class="dis_name" href="javascript:void(0)">KDLin</a>回复<a class="dis_name" href="javascript:void(0)">星星点灯</a>:还好还好！</li>--%>
                <div class="more" style="display: block;">
                    <span>查看更多<p>1条</p>评论</span>
                    <i class="laydown"></i>
                </div>
            </ul>
        </div>
    </section>
</c:forEach>
</body>
</html>
