<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/16
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>高仿微信</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font/iconfont.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/room_share.css"/>
</head>
<body id="chat_bg">
<input class="data" name="room_id" type="hidden" value="${curChatroom.id}">
<div class="wrapper">
    <div class="sidebar">
        <ul>
            <li><img src="${pageContext.request.contextPath}/assets/images/1.jpg" alt=""/></li>
            <li class="iconfont active icon-liaotian1 lt"></li>
            <li class="iconfont icon-tongxunlu txl"></li>
            <li class="iconfont icon-shoucang sc"></li>
            <li class="iconfont icon-101 more"></li>
        </ul>
    </div>
    <!-- 中间的tab栏-->
    <div class="tabs">
        <div class="search">
            <input type="text" placeholder="搜索"/>
            <span class="iconfont icon-search"></span>
            <span class="plus">+</span>
        </div>
        <div class="talk">
    <%--        <ul>
                <li>
                    <input name="id" type="hidden" value="0">
                    <a href="#"><img src="${pageContext.request.contextPath}/assets/images/1.png" alt=""/></a>
                    <div class="content">
                        <h3>前端大神群</h3>
                        <p>：[动画表情]</p>
                    </div>
                </li>


            </ul>--%>
        </div>
    </div>
    <!-- 右侧的主要区域-->
    <div class="main">
        <div class="top">
            <div class="mini">-</div>
            <div class="max">□</div>
            <div class="close">x</div>

        </div>
        <div class="name">
            <div class="user">${curChatroom.name}</div>
            <div class="more">···</div>
        </div>
        <div class="dialouges">
            <div class="container">

            </div>
            <!--<div class="person">-->
            <!--<img src="images/1.jpg" alt=""/>-->
            <!--<span>好的</span>-->
            <!--</div>-->
            <!--<div class="self">-->
            <!--<span>好的</span>-->
            <!--<img src="images/1.jpg" alt=""/>-->
            <!--</div>-->
        </div>
        <div class="message">
            <div class="menu">
                <div class="smile iconfont icon-biaoqing"></div>
                <div class="doc iconfont icon-wenjian"></div>
                <div class="snap iconfont icon-jieping  "></div>
                <div class="video iconfont icon-shipin"></div>
                <div class="speak iconfont icon-dianhua"></div>
            </div>
            <textarea name="" id="txt" cols="30" rows="10"></textarea>
            <input name="type" type="hidden" value="0">
            <input name="target_name" type="hidden" value="">
            <div class="send"><span>发送(S)</span></div>

        </div>

    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/room_share.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/chatroom.js"></script>