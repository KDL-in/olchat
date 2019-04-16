<%@ page import="java.sql.Timestamp" %><%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/16
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="safe.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>在线聊天室</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font/iconfont.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/room_share.css"/>
    <link rel="stylesheet" href="assets/utils/webuploader/webuploader.css" type="text/css"/>
</head>
<div id="search_bar" class="am-offcanvas">
    <div class="am-offcanvas-bar">
        <div class="am-offcanvas-content search-result-container">

        </div>
    </div>
</div>
<body id="chat_bg">
<input name="room_id" type="hidden" value="${curChatroom.id}">
<input name="user_id" type="hidden" value="${curUser.id}">
<div class="wrapper">
    <div class="sidebar">
        <ul>
            <li><img class="am-circle" src="${curUser.img_url}"/></li>
            <li class="iconfont icon-liaotian1 lt"></li>
            <li class="iconfont active icon-tongxunlu txl"></li>
            <li class="iconfont icon-shoucang sc"></li>
            <li class="am-icon-power-off offline"></li>
        </ul>
    </div>
    <!-- 中间的tab栏-->
    <div class="tabs">
        <div class="search">
            <input type="text" class="searchRoom" placeholder="搜索"/>
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
            <div class="user">${curSession==null||curSession==""?"欢迎登陆":curSession}</div>
            <%--todo显示最近聊天内容--%>
            <div class="more">···</div>
        </div>
        <div class="dialouges">
            <div class="dateseletor">
                <div class="am-alert am-alert-danger" id="my-alert" style="display: none">
                    <p>开始日期应小于结束日期！</p>
                </div>
                <div class="am-g">
                    <div class="am-u-sm-6">
                        <button type="button" class="am-btn am-btn-xs am-btn-primary am-margin-right" id="my-start">开始日期</button><span id="my-startDate">2014-12-20</span>
                    </div>
                    <div class="am-u-sm-6">
                        <button type="button" class="am-btn am-btn-xs am-btn-primary am-margin-right" id="my-end">结束日期</button><span id="my-endDate">2014-12-25</span>
                    </div>
                </div>
            </div>
            <div class="container">
                <%--             <div class="person">
                                 <img src="assets/images/1.jpg" alt="">
                                 <div>
                                     <p>测试</p>
                                     <span>sdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaafsdalksdjlasdjklfjaklsdjflajldfjlajfaaaaaaaaaaaaaaaljkjaldjflkajldjlajlfjaljlfajllajlfjaldjlfajlajldjflajlfjasaaf</span>
                                 </div>

                             </div>--%>
                <%--        <div class="self">
                            <div>
                                <span><img class ="chatImg"src="uploadDir/img/P60223-171600.jpg"></span>
                            </div>
                            <img src="assets/images/1.jpg" alt="">

                        </div>--%>


            </div>
        </div>
<%--        <div class="dialouges historyDiv">
            <div class="container">

            </div>

        </div>--%>
        <div class="message">
            <div class="menu">
                <div class="smile iconfont icon-biaoqing"></div>
                <div class="doc iconfont icon-wenjian"></div>
                <div class="snap iconfont icon-jieping  "></div>
                <div class="video iconfont icon-shipin"></div>
                <div class="speak iconfont icon-dianhua"></div>
            </div>
            <div id="uploadPanel" class="am-panel am-panel-success">
                <div class="am-panel-hd">
                    选择图片或文件
                </div>
                <div class="am-panel-bd">
                    <div id="thelist" class="uploader-list"></div>
                    <div class="btns">
                        <div id="picker">选择文件</div>
                        <%--<button id="ctlBtn" class="btn btn-default">开始上传</button>--%>
                    </div>
                </div>
            </div>


            <textarea name="" id="txt" cols="30" rows="10"></textarea>
            <input name="type" type="hidden" value="0">
            <input name="target_name" type="hidden" value="">
            <div class="send"><span>发送(S)</span></div>

        </div>

    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.0.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/room_share.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/userRoom.js"></script>
<script type="text/javascript" src="assets/utils/webuploader/webuploader.js"></script>
<script type="text/javascript" src="assets/utils/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="assets/js/upload.js"></script>
<script type="text/javascript" src="assets/js/amazeui.js"></script>
</html>
