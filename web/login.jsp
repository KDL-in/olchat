<%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 18/6/15
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <!--<meta http-equiv="X-UA-Compatible" content="ie=edge">-->
    <title>登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css"/>
</head>
<body class="login-container">
<div class="warning-span">${ msg }</div>
<div class="login-box">
    <div class="logo-img">
        <img src="assets/images/logo2_03.png" alt=""/>
    </div>
    <form class="am-form" action="${pageContext.request.contextPath }/login" method="post" data-am-validator>
        <input type="hidden" name="method" value="login">
        <div class="am-form-group">
            <label for="doc-vld-name-2"><i class="am-icon-user"></i></label>
            <input type="text" id="doc-vld-name-2" name="user_name" minlength="3" placeholder="输入用户名（至少 3 个字符）" required/>
        </div>
        <div class="am-form-group">
            <label for="doc-vld-psw"><i class="am-icon-key"></i></label>
            <input type="password" id="doc-vld-psw" name="password" placeholder="输入密码" required/>
        </div>
        <div class="am-g">
            <div class="am-u-sm-6"><button class="am-btn am-btn-secondary" type="submit">登录</button></div>
            <div class="am-u-sm-6"><a class="am-btn am-btn-secondary" href="register.jsp">注册</a></div>

        </div>
    </form>
</div>
</body>
</html>
