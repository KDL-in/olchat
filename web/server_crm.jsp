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
    <title>Chatroom Management</title>
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
    <style>
        div#my-popup {
            max-height: 580px;
        }
    </style>
</head>
</head>

<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand"><img src="assets/i/logo.png"></div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">

            <li class="am-dropdown tognzhi">
                <button class="indexButton am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o">
                    <a href="server_index.jsp"></a>首页
                </button>
            </li>

            <%--顶栏--%>
            <li class="kuanjie">

                <a href="server_um.jsp" >用户管理</a>
                <a href="server_crm.jsp" id="selectLi">聊天室管理</a>
                <a href="server_fm.jsp">好友管理</a>
                <a href="server_rm.jsp">聊天记录管理</a>
                <a href="server_sm.jsp">统计管理</a>

            </li>

       <%--     <li class="soso">

                <p>

                    <select data-am-selected="{btnWidth: 70, btnSize: 'sm', btnStyle: 'default'}">
                        <option value="b">全部</option>
                        &lt;%&ndash;<option value="o">产品</option>&ndash;%&gt;
                        &lt;%&ndash;<option value="o">会员</option>&ndash;%&gt;

                    </select>

                </p>

                <p class="ycfg"><input type="text" class="am-form-field am-input-sm" placeholder="搜索"/></p>
                <p>
                    <button class="am-btn am-btn-xs am-btn-default am-xiao"><i class="am-icon-search"></i></button>
                </p>
            </li>


            <li class="am-hide-sm-only" style="float: right;"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>--%>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">

    <div class="nav-navicon admin-main admin-sidebar">
        <div class="sideMenu am-icon-dashboard" style="color:#aeb2b7; margin: 10px 0 0 0;">
            欢迎系统管理员：${curUser.user_name}</div>
        <div class="sideMenu">
            <h3 class="am-icon-user"><em></em> <a href="#">用户管理</a></h3>
            <ul>
                <li><a href="server_um.jsp">用户列表</a></li>
            </ul>
            <h3 class="am-icon-university"><em></em> <a href="#"> 聊天室管理</a></h3>
            <ul>
                <li><a href="server_crm.jsp">聊天室列表</a></li>
            </ul>
            <h3 class="am-icon-users"><em></em> <a href="#">好友管理</a></h3>
            <ul>
                <li><a href="server_fm.jsp">好友关系列表</a></li>
            </ul>
            <h3 class="am-icon-comments-o"><em></em> <a href="#">聊天记录管理</a></h3>
            <ul>
                <li><a href="server_rm.jsp">聊天记录</a></li>
            </ul>
            <h3 class="am-icon-gears"><em></em> <a href="#">统计管理</a></h3>
            <ul>
                <li id="comeFrom"><a href="server_sm.jsp">分布统计</a></li>
                <li id="sexual"><a href="server_sm.jsp">性别</a></li>
            </ul>
        </div>
        <!-- sideMenu End -->

        <script type="text/javascript">
            jQuery(".sideMenu").slide({
                titCell: "h3", //鼠标触发对象
                defaultIndex:1,
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
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs"><a href="server_index.jsp"></a> 首页</button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">用户管理<a
                            href="server_um.jsp" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">聊天室管理<a
                            href="server_crm.jsp" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>
                <li>
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs">好友管理<a
                            href="server_fm.jsp" class="am-close am-close-spin" data-am-modal-close="">×</a>
                    </button>
                </li>


            </ul>


        </div>

        <div class="am-popup am-popup-inner" id="my-popup" style="max-height: 480px">
            <div class="am-popup-hd">
                <h4 class="am-popup-title">修改当前用户信息</h4>
                <span data-am-modal-close
                      class="am-close">&times;</span></div>
            <div class="am-popup-bd">
                <%--修改信息--%>
                <form class="modForm am-form">
                    <div class="am-form-group">
                        <span class="modDiv-label">ID</span>
                        <input type="text" name="id-input" id="id-input" placeholder="请输入标题" disabled>
                    </div>
                    <div class="am-form-group">
                        <span class="modDiv-label">聊天室名</span>
                        <input type="text" name="name-input" id="name-input" placeholder="请输入标题">
                    </div>
                    <div class="am-form-group">
                        <span class="modDiv-label">创建者</span>
                        <input type="text" name="admin-input" id="admin-input" placeholder="请输入昵称"disabled>

                    </div>
                    <div class="am-form-group">
                        <span class="modDiv-label">密码</span>
                        <input type="text" name="password-input" id="password-input" placeholder="请输入昵称">
                    </div>

                    <div class="am-form-group am-cf">
                        <div class="you">
                            <p>
                                <button type="submit" class="submitButton am-btn am-btn-success am-radius">提交</button>
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="admin-biaogelist">
            <div class="listbiaoti am-cf">
                <ul class="am-icon-user on">
                    聊天室列表
                </ul>
                <dl class="am-icon-home" style="float: right;">
                    当前位置： 首页 > <a href="#">聊天室列表</a>
                </dl>
                <!--data-am-modal="{target: '#my-popup'}" 弹出层 ID  弹出层 190行 开始  271行结束-->

            </div>

            <form class="am-form am-g" id="listContainer">
                <%--列表内容--%>
                <table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">

                    <thead>
                    <tr class="am-success">
                        <th class="table-check"><input type="checkbox"/></th>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>昵称</th>
                        <th>头像</th>
                        <th>用户类型</th>
                        <th width="163px" class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="recordTr">
                        <td><input type="checkbox"/></td>
                        <td>1</td>
                        <td class="user-name-td">kdlin</td>
                        <td class="nick-name-td">焜达</td>
                        <td><img src="assets/images/header/17.png"></td>
                        <td>超级管理员</td>


                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a class="modButton am-btn am-btn-default am-btn-xs am-text-success am-round am-icon-pencil-square-o"
                                       title="修改"></a>
                                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除">
                                        <span class="am-icon-trash-o"></span></button>
                                </div>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>

                <div class="am-btn-group am-btn-group-xs">
                    <button type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 新增</button>
                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除
                    </button>
                </div>
                <ul class="am-pagination am-fr">
                    <li class="am-disabled"><a href="#">«</a></li>
                    <li class="am-active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">»</a></li>
                </ul>
                <hr/>
                <p>
                    备注：操作图标含义
                    <a class="am-icon-pencil-square-o am-text-secondary" title="修改"> 修改栏目</a>
                    <a class="am-icon-copy am-text-warning" title="复制"> 复制栏目</a>
                    <a class="am-icon-trash-o am-text-danger" title="删除"> 删除栏目</a>


                </p>
            </form>

            <div class="foods">
                <ul>
                    版权所有@2015
                </ul>
                <dl>
                    <a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
                </dl>
            </div>
        </div>

    </div>


</div>


</body>
<script type="text/javascript" src="assets/js/server.js"></script>
<script type="text/javascript" src="assets/js/amazeui.js"></script>
<script type="text/javascript" src="assets/js/ser_crm.js"></script>
</html>
