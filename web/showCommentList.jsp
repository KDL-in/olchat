<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 2019-4-20
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<li class="commentSenderLi">
<a class="dis_name"  href="javascript:void(0)">我: </a><input type="text" placeholder="Add here!" class="commentSender" >
</li>
<%--<c:if test="${empty comments}">
    <li>
        <em style="font-size: 0.75em; color: gray">右键添加评论？</em>

    </li>
</c:if>--%>
<c:forEach items="${comments}" var="c">


    <li>
        <input type="hidden"name="comment_id" value="${c.id}">
        <a class="dis_name" href="javascript:void(0)">
            <input type="hidden"name="cmt_user_id" value="${c.user_id}">
            <input type="hidden"name="cmt_user_name" value="${usersBuf[c.user_id].user_name}">
            <span>${usersBuf[c.user_id].nickname!=""?usersBuf[c.user_id].nickname:usersBuf[c.user_id].user_name}:</span>
        </a>
        <c:if test="${c.user_id!=c.reply_id}">
            回复
            <a class="dis_name" href="javascript:void(0)">
                <input type="hidden"name="reply_id" value="${c.reply_id}">
                <span>${usersBuf[c.reply_id].nickname!=""?usersBuf[c.reply_id].nickname:usersBuf[c.reply_id].user_name}</span>
            </a>
        </c:if>
        <span>${c.content}</span>
    </li>
</c:forEach>
<div class="more" style="display: block;">
    <span>查看更多<p></p>评论</span>
    <i class="laydown"></i>
</div>
</body>
</html>
