<%@ page import="java.sql.Timestamp" %><%--
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
    <title>在线聊天室</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font/iconfont.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/room_share.css"/>
</head>
<body id="chat_bg">
<input name="room_id" type="hidden" value="${curChatroom.id}">
<input name="user_id" type="hidden" value="${curUser.id}">
<div class="wrapper">
    <div class="sidebar">
        <ul>
            <li><img src="${pageContext.request.contextPath}/assets/images/1.jpg" alt=""/></li>
            <li class="iconfont icon-liaotian1 lt"></li>
            <li class="iconfont active icon-tongxunlu txl"></li>
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
            <div class="user">${curChatroom==null?"欢迎登陆":curChatroom.name}</div>
            <div class="more">···</div>
        </div>
        <div class="dialouges">
            <div class="container">
                <%--            <div class="person">
                                <img src="assets/images/1.jpg" alt="">
                                <div>
                                    <p>测试</p>
                                    <span>sdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaaf</span>
                                </div>

                            </div>
                            <div class="self">
                                <div>
                                    <span>sdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaaf</span>
                                </div>
                                <img src="assets/images/1.jpg" alt="">

                            </div>--%>


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
        <%--        <div class="message">
                    <div class="menu">
                        <div class="smile iconfont icon-biaoqing"></div>
                        <div class="doc iconfont icon-wenjian"></div>
                        <div class="snap iconfont icon-jieping  "></div>
                        <div class="video iconfont icon-shipin"></div>
                        <div class="speak iconfont icon-dianhua"></div>
                    </div>
                    <textarea name="" id="txt" cols="30" rows="10"></textarea>
                    <form action="visit" method="post">&lt;%&ndash;隐藏字段，用于提交消息到数据库&ndash;%&gt;
                        <input name="content" type="hidden" value="">
                        <input name="time" type="hidden" value="<%new Timestamp(System.currentTimeMillis());%>">
                        <input name="room_id" type="hidden" value="${curChatroom.id}">
                        <input name="type"type="hidden"value=""
                        <input name="user_id" type="hidden" value="${curUser.id}">

                    </form>
                    <div class="send"><span>发送(S)</span></div>
                </div>--%>

    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/room_share.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/userRoom.js"></script>