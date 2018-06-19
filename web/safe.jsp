<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(null == session.getAttribute("curUser")){
    out.println("<script language='javascript'>alert('您的账户已经过期，请重新登录!');window.location='login.jsp';</script>");
    return;
}%>
