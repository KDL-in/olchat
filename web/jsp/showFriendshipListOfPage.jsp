<%@ page import="entity.Friendship" %>
<%@ page import="entity.PageBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
    <thead>
    <tr class="am-success">
        <th class="table-check"><input type="checkbox" id="selectAll"/></th>
        <th>ID</th>
        <th>用户一</th>
        <th>好友关系</th>
        <th>用户二</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageBean.list}" var="fs">
        <tr class="recordTr">
            <td><input type="checkbox"/></td>
            <td class="id-td">${fs.id}</td>
            <td class="user1-name-td">${usersBuf[fs.user1_id].user_name}</td>
            <td><--------></td>
            <td class="user2-name-td">${usersBuf[fs.user2_id].user_name}</td>
            <td>
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button class="deleButton am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除">
                            <input type="hidden" value="${fs.id}">
                            <span class="am-icon-trash-o"></span>
                        </button>
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
        PageBean<Friendship> pb = (PageBean<Friendship>) request.getAttribute("pageBean");
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
