<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询部门</title>
</head>
<body>
<%@ include file="/view/common/head.jsp" %>
<div>
    <%@ include file="/view/common/menu.jsp" %>
    <div class="container" style="border: 1px solid red;width: 88%;height: 85%;float: right;">
        <form action="/sys/dept/listAll" method="get">
            部门名称：<input type="text" name="name" value="${name}">
            开始时间：<input type="date" value="" name="">
            结束时间：<input type="date" value="" name="">
            <input type="submit" value="查询" class="btn btn-info">
        </form>

        <a href="/view/sys/dept/add.jsp" class="btn btn-success">添加</a>

        <table class="table table-bordered text-center">
            <tr>
                <td>序号</td>
                <td>部门名称</td>
                <td>创建时间</td>
                <td>创建人</td>
                <%--<td>部门人数</td>--%>
                <td>操作</td>
            </tr>

            <c:forEach var="dept" items="${list}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${dept.name}</td>
                    <td>
                        <fmt:parseDate var="createTime" value="${dept.createTime}"
                                       pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                        <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td>${dept.createName}</td>
                        <%--<td></td>--%>
                    <td>
                        <a href="/sys/dept/toUpdate?id=${dept.id}">修改</a>
                        <a href="/sys/dept/delete?id=${dept.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <a href="/sys/dept/listAll?name=${name}&page=1" class="btn btn-primary">首页</a>
        <a href="/sys/dept/listAll?name=${name}&page=${page.pageCurrent<=1 ? 1 : (page.pageCurrent-1)}"
           class="btn btn-info">上一页</a>
        <a href="/sys/dept/listAll?name=${name}&page=${page.pageCurrent>=page.pageCount ? page.pageCount : (page.pageCurrent+1)}"
           class="btn btn-info">下一页</a>
        <a href="/sys/dept/listAll?name=${name}&page=${page.pageCount}" class="btn btn-primary">尾页</a>
        <br>
        当前页：${page.pageCurrent}，总页数：${page.pageCount},总记录数：${page.count},每页显示记录数：${page.pageSize}
    </div>
</div>
</body>
</html>
