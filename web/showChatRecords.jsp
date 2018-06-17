<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/17
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<div class="self">
    <div>
        <span></span>
    </div>
    <img src="assets/images/1.jpg" alt="">

</div>
<c:forEach var="record" items="${records}">
    <c:choose>
        <c:when test="${record.user_id==curUser.id}"><%--我的消息--%>
            <c:choose>
                <c:when test="${record.type==1}"><%--内容为图片--%>
                    <div class="self">
                        <div>
                            <span><img class="chatImg" src="${record.content}"></span>
                        </div>
                        <img src="assets/images/1.jpg" alt="">
                    </div>
                </c:when>
                <c:otherwise><%--内容为文字--%>
                    <div class="self">
                        <div>
                            <span>${record.content}</span>
                        </div>
                        <img src="assets/images/1.jpg" alt="">
                    </div>
                </c:otherwise>
            </c:choose>
            <%--<div class="self"><span></span><img src="assets/images/1.jpg" alt=""></div>--%>
        </c:when>
        <c:otherwise><%--对方的消息--%>
            <c:choose>
                <c:when test="${record.type==1}"><%--内容为图片--%>
                    <div class="person">
                        <img src="assets/images/1.jpg" alt="">
                        <div>
                            <p>${record.user}</p>
                            <span><img class="chatImg" src="${record.content}"></span>
                        </div>

                    </div>
                </c:when>
                <c:otherwise><%--内容为文字--%>
                    <div class="person">
                        <img src="assets/images/1.jpg" alt="">
                        <div>
                            <p>${record.user}</p>
                            <span>${record.content}</span>
                        </div>

                    </div>
                </c:otherwise>
            </c:choose>
            <%--<div class="person"><img src="assets/images/1.jpg" alt=""><span>${record.content}</span></div>--%>
        </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>
