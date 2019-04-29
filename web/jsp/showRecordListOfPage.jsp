<%@ page import="entity.PageBean" %>
<%@ page import="entity.User" %>
<%@ page import="entity.ChatRecord" %><%--
  Created by IntelliJ IDEA.
  User: KundaLin
  Date: 2019-4-25
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
    <thead>
    <tr class="am-success">
        <th class="table-check"><input type="checkbox" id="selectAll"/></th>
        <th>ID</th>
        <th>发言人</th>
        <th>时间</th>
        <th>内容</th>
        <th>所属聊天室</th>
        <th>接收好友</th>
        <th width="163px" class="table-set">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageBean.list}" var="r">
        <tr class="recordTr">
            <td><input type="checkbox"/></td>
            <td>${r.id}</td>
            <td class="user-td">${r.user}</td>
            <td class="time-td">${r.time}</td>
            <td class="content-td">${r.content}</td>
            <td class="room-td">${r.room_id}</td>
            <td class="friend-td">${usersBuf[r.friend_id].user_name}</td>
            <td>
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <a class="modButton am-btn am-btn-default am-btn-xs am-text-success am-round am-icon-pencil-square-o"
                           title="修改"></a>
                        <button class="deleButton am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除">
                            <input type="hidden" class="id-input" value="${r.id}">
                            <span class="am-icon-trash-o"></span></button>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>
<%--列表内容--%>
<div class="am-btn-group am-btn-group-xs">
    <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
    <button type="button" class="mulDeleButton am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除
    </button>
</div>


<ul class="am-pagination am-fr">
    <input type="hidden" id="pageNumInp" value="${pageBean.pageNum}"/>
    <input type="hidden" id="pageNumInp" value="${pageBean.start}"/>
    <input type="hidden" id="pageNumInp" value=""/>
    <%--分页导航--%>
    <li id="lastPage"><a href="#">«</a></li>
    <%
        PageBean<ChatRecord> pb = (PageBean<ChatRecord>) request.getAttribute("pageBean");
        int start, end, pageNum;
        start = pb.getStart();
        end = pb.getEnd();
        pageNum = pb.getPageNum();
//        System.out.println("pageNum: "+ pageNum);
        for (int i = start; i <= end; ++i) {
//            System.out.println(i+" "+pb.getPageNum());
            request.setAttribute("i", i);
    %>
    <c:if test="${i==pageBean.pageNum}">
        <li class="am-active"><a href="#">${i}</a></li>
    </c:if>
    <c:if test="${i!=pageBean.pageNum}">
        <li><a href="#">${i}</a></li>
    </c:if>
    <%
        }
    %>
    <li id="nextPage"><a href="#">»</a></li>
</ul>
<hr/>
<p>
    备注：操作图标含义
    <a class="am-icon-pencil-square-o am-text-secondary" title="修改"> 修改栏目</a>
    <a class=" am-icon-trash-o am-text-danger" title="删除"> 删除栏目</a>


</p>

</html>
