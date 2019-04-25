<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 2019-4-22
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("curUser");
    if (null == user) {
        out.println("<script language='javascript'>alert('您的账户已经过期，请重新登录!');window.location='login.jsp';</script>");
        return;
    }
    if (user.getType() != 2) {
        out.print("<script language='javascript'>alert('请使用管理员账号登陆');window.location='login.jsp';</script>");
        return;
    }
%>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/ser_index.css">
    <script src="assets/js/jquery-2.1.0.js"></script>
    <script src="assets/js/app.js"></script>
</head>
</head>

<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand"><img src="assets/i/logo.png"></div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">

            <li class="am-dropdown tognzhi" data-am-dropdown>
                <button class="am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o"
                        data-am-dropdown-toggle> 首页
                </button>
                <!--                <ul class="am-dropdown-content">


                                    <li class="am-dropdown-header">所有消息都在这里</li>


                                    <li><a href="#">未激活会员 <span class="am-badge am-badge-danger am-round">556</span></a></li>
                                    <li><a href="#">未激活代理 <span class="am-badge am-badge-danger am-round">69</span></a></a></li>
                                    <li><a href="#">未处理汇款</a></li>
                                    <li><a href="#">未发放提现</a></li>
                                    <li><a href="#">未发货订单</a></li>
                                    <li><a href="#">低库存产品</a></li>
                                    <li><a href="#">信息反馈</a></li>


                                </ul>-->
            </li>

            <li class="kuanjie">

                <a href="#">用户管理</a>
                <a href="#">聊天室管理</a>
                <a href="#">好友管理</a>
                <a href="#">聊天记录管理</a>
                <a href="#">个人动态管理</a>
                <a href="#">系统设置</a>
            </li>

            <li class="soso">

                <p>

                    <select data-am-selected="{btnWidth: 70, btnSize: 'sm', btnStyle: 'default'}">
                        <option value="b">全部</option>
                        <%--<option value="o">产品</option>--%>
                        <%--<option value="o">会员</option>--%>

                    </select>

                </p>

                <p class="ycfg"><input type="text" class="am-form-field am-input-sm" placeholder="圆角表单域"/></p>
                <p>
                    <button class="am-btn am-btn-xs am-btn-default am-xiao"><i class="am-icon-search"></i></button>
                </p>
            </li>


            <li class="am-hide-sm-only" style="float: right;"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">

    <div class="nav-navicon admin-main admin-sidebar">
        <div class="sideMenu am-icon-dashboard" style="color:#aeb2b7; margin: 10px 0 0 0;"> 欢迎系统管理员：Admin</div>
        <div class="sideMenu">
            <h3 class="am-icon-user"><em></em> <a href="#">用户管理</a></h3>
            <ul>
                <li><a href="">用户列表</a></li>
            </ul>
            <h3 class="am-icon-university"><em></em> <a href="#"> 聊天室管理</a></h3>
            <ul>
                <li>聊天室列表</li>
            </ul>
            <h3 class="am-icon-users"><em></em> <a href="#">好友管理</a></h3>
            <ul>
                <li>好友关系列表</li>
            </ul>
            <h3 class="am-icon-comments-o"><em></em> <a href="#">聊天记录管理</a></h3>
            <ul>
                <li>聊天记录</li>
            </ul>
            <h3 class="am-icon-gears"><em></em> <a href="#">个人动态管理</a></h3>
            <ul>
                <li>个人动态</li>
                <li>动态评论</li>
            </ul>
        </div>
        <!-- sideMenu End -->

        <script type="text/javascript">
            jQuery(".sideMenu").slide({
                titCell: "h3", //鼠标触发对象
                targetCell: "ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
                effect: "slideDown", //targetCell下拉效果
                delayTime: 300, //效果时间
                triggerTime: 150, //鼠标延迟触发时间（默认150）
                defaultPlay: true,//默认是否执行效果（默认true）
                returnDefault: true //鼠标从.sideMen移走后返回默认状态（默认false）
            });
        </script>


    </div>

    <div class=" admin-content">

        <div class="daohang">
            <ul>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs"> 首页</button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">帮助中心<a
                            href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">用户管理<a
                            href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">聊天室管理<a
                            href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>


            </ul>


        </div>


        <div class="admin">


            <div class="admin-index">
                <dl data-am-scrollspy="{animation: 'slide-right', delay: 100}">
                    <dt class="qs"><i class="am-icon-users"></i></dt>
                    <dd id="nOfUsers">${nOfUsers}</dd>
                    <dd class="f12">用户数量</dd>
                </dl>
                <dl data-am-scrollspy="{animation: 'slide-right', delay: 300}">
                    <dt class="cs"><i class="am-icon-area-chart"></i></dt>
                    <dd id="nOfImgs">${nOfImgs}</dd>
                    <dd class="f12">图片数量</dd>
                </dl>
                <dl data-am-scrollspy="{animation: 'slide-right', delay: 600}">
                    <dt class="hs"><i class="am-icon-comments-o"></i></dt>
                    <dd id="nOfRecords">${nOfRecords}</dd>
                    <dd class="f12">消息数量</dd>
                </dl>
                <dl data-am-scrollspy="{animation: 'slide-right', delay: 900}">
                    <dt class="ls"><i class="am-icon-university"></i></dt>
                    <dd id="nOfRooms">${nOfRooms}</dd>
                    <dd class="f12">聊天室数量</dd>
                </dl>
            </div>
            <div class="initDataDiv admin-biaoge">
                <div class="xinxitj">信息概况</div>
                <table class="am-table">
                    <thead>
                    <tr>
                        <th>团队统计</th>
                        <th>全部会员</th>
                        <th>全部未激活</th>
                        <th>今日新增</th>
                        <th>今日未激活</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>普卡</td>
                        <td>普卡</td>
                        <td><a href="#">4534</a></td>
                        <td>+20</td>
                        <td> 4534</td>
                    </tr>

                    </tbody>
                </table>
                <table class="am-table">
                    <thead>
                    <tr>
                        <th>团队统计</th>
                        <th>全部会员</th>
                        <th>全部未激活</th>
                        <th>今日新增</th>
                        <th>今日未激活</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>普卡</td>
                        <td>普卡</td>
                        <td>4534</td>
                        <td>+50</td>
                        <td> 4534</td>
                    </tr>

                    </tbody>
                </table>
                <table class="am-table">
                    <thead>
                    <tr>
                        <th>资金统计</th>
                        <th>账户总收入</th>
                        <th>账户总支出</th>
                        <th>账户余额</th>
                        <th>今日收入</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>普卡</td>
                        <td>普卡</td>
                        <td>4534</td>
                        <td>+20</td>
                        <td> 4534</td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div class="shuju">
                <div class="shujuone">
                    <dl>
                        <dt>你好！</dt>
                        <dt>管理员 【${curUser.user_name}】</dt>
                    </dl>
                    <ul>
                        <h2>欢迎使用</h2>
                        <li>OLchatRoom后台管理系统</li>
                    </ul>
                </div>
                <div class="shujutow">
                    <dl>
                        <dt>管理聊天室和成员？</dt>
                        <dt>管理聊天记录？</dt>
                        <dt>管理个人动态？</dt>
                    </dl>
                    <ul>
                        <h2>您想</h2>
                        <li>完成的操作是？</li>
                    </ul>
                </div>

                <script type="text/javascript">jQuery(".slideTxtBox").slide();</script>


            </div>

            <div class="foods">
                <ul>版权所有@2019</ul>
                <dl><a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a></dl>


            </div>


        </div>

    </div>


</div>


</body>
<script type="text/javascript" src="assets/js/server_index.js"></script>
</html>
