<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Moment</title>
    <link rel="stylesheet" href="assets/css/amazeui.css"/>
    <link rel="stylesheet" href="assets/font/iconfont.css"/>
    <link rel="stylesheet" href="assets/css/custom.css">
    <link rel="stylesheet" href="assets/css/room_share.css"/>
    <link rel="stylesheet" href="assets/utils/webuploader/webuploader.css" type="text/css"/>
    <link rel="stylesheet" href="assets/css/moment-info.css">
    <link rel="stylesheet" href="assets/css/moment.css">
    <link rel="stylesheet" href="assets/utils/contextMenu/contextMenu.css"/>
    <link rel="stylesheet" href="assets/utils/webuploader/webuploader.css" type="text/css"/>
</head>

<body id="chat_bg">
<%--<input name="room_id" type="hidden" value="${curChatroom.id}">--%>
<input name="user_id" type="hidden" value="${curUser.id}">
<div class="wrapper">
    <div class="sidebar">
        <ul>
            <li><img class="am-circle" src="${curUser.img_url}"/></li>
            <li class="iconfont icon-liaotian1 lt"></li>
            <li class="iconfont icon-tongxunlu txl"></li>
            <li class="iconfont active icon-shoucang sc"></li>
            <li class="am-icon-power-off offline"></li>
        </ul>
    </div>
    <!-- 中间的tab栏-->
    <div class="tabs blog">
        <div class="blog-sidebar">
            <div id="signature" class="blog-sidebar-widget blog-bor">
                <h2 class="blog-text-center blog-title"><span>About ME</span></h2>
                <img src="${curUser.img_url}" alt="about me" class="blog-entry-img">
                <textarea id="intro-name">${curUser.nickname==""?curUser.user_name:curUser.nickname}</textarea>
                <textarea id="intro">${curUser.intro==null||curUser.intro==""?'编辑你的个性签名':curUser.intro}</textarea>

            </div>
            <div class="blog-sidebar-widget blog-bor">
                <h2 class="blog-text-center blog-title"><span>Contact ME</span></h2>
                <p id="contact">
                    <a href=""><span id="qq-span" class="am-icon-qq am-icon-fw am-primary blog-icon"></span></a>
                    <a href=""><span id="blog-span" class="am-icon-github am-icon-fw blog-icon"></span></a>
                    <a href=""><span id="weibo-span"class="am-icon-weibo am-icon-fw blog-icon"></span></a>
                    <a href=""><span id="weixin-span" class="am-icon-weixin am-icon-fw blog-icon"></span></a>
                </p>
            </div>


        </div>
    </div>
    <!-- 右侧的主要区域-->
    <div class="main">
        <div id="edit-area">
            <div class="message">
                <div class="menu">
                    <div class="doc iconfont icon-wenjian"></div>
                    <span id="img_url"></span>
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

                <div class="send"><span>发送(S)</span></div>
            </div>
        </div>
        <div id="moment-container">

            <section class="content">
                <img class="head_img" src="assets/images/header/10.png">

                <div class="contain">
                    <p class="owner">KDLin</p>
                    <p class="text">会当凌绝顶，一览众山小。</p>

                    <div class="images">
                        <img src="assets/images/header/3.png">
                    </div>

                    <div class="comment">
                        <div class="like">
                            <i class="unagree"></i>
                            <p class="">125</p>
                        </div>

                        <div class="discuss">
                            <i></i>
                            <p>评论</p>
                        </div>

                        <div class="dele">
                            <i></i>
                            <p>删除</p>
                        </div>
                    </div>

                    <p class="time">13小时前</p>

                    <ul class="dis_cont">
                        <li><a class="dis_name" href="javascript:void(0)">星星点灯</a>:风景很不错，什么时候回来！</li>
                        <li><a class="dis_name" href="javascript:void(0)">小红</a>:了解从未接触过的领域。</li>
                        <li><a class="dis_name" href="javascript:void(0)">小明</a>回复<a class="dis_name"
                                                                                     href="javascript:void(0)">小红</a>:有内涵~
                        </li>
                        <li><a class="dis_name" href="javascript:void(0)">KDLin</a>回复<a class="dis_name"
                                                                                        href="javascript:void(0)">星星点灯</a>:还好还好！
                        </li>
                        <li><a class="dis_name" href="javascript:void(0)">KDLin</a>回复<a class="dis_name"
                                                                                        href="javascript:void(0)">星星点灯</a>:还好还好！
                        </li>
                        <li><a class="dis_name" href="javascript:void(0)">KDLin</a>回复<a class="dis_name"
                                                                                        href="javascript:void(0)">星星点灯</a>:还好还好！
                        </li>
                        <li><a class="dis_name" href="javascript:void(0)">KDLin</a>回复<a class="dis_name"
                                                                                        href="javascript:void(0)">星星点灯</a>:还好还好！
                        </li>
                        <div class="more" style="display: block;">
                            <span>查看更多<p>1条</p>评论</span>
                            <i class="laydown"></i>
                        </div>
                    </ul>
                </div>

                <div class="assist"></div>
            </section>

            <section class="content">
                <img class="head_img" src="assets/images/header/10.png">

                <div class="contain">
                    <p class="owner">KDLin</p>
                    <p class="text">天气不好，心情一般。</p>

                    <div class="images">
                        <div class="img" style="height: 1.34rem;">
                            <img src="assets/images/header/5.png"
                                 style="width: 1.34rem; height: 1.34rem; margin-left: 0px;">
                            <img src="assets/images/header/5.png" style="width: 1.34rem; height: 1.34rem;">
                        </div>
                    </div>

                    <div class="comment">
                        <div class="like">
                            <i class="unagree"></i>
                            <p class="">125</p>
                        </div>

                        <div class="discuss">
                            <i></i>
                            <p>评论</p>
                        </div>

                        <div class="dele">
                            <i></i>
                            <p>删除</p>
                        </div>
                    </div>

                    <p class="time">13小时前</p>

                </div>

                <div class="assist"></div>
            </section>


            <footer>已显示全部内容</footer>

        </div>

    </div>
</div>
</body>
<script src="assets/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="assets/js/amazeui.js"></script>
<script type="text/javascript" src="assets/utils/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="assets/js/moment.js"></script>
<script type="text/javascript" src="assets/utils/contextMenu/contextMenu.js"></script>
</html>