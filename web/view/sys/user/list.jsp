<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户</title>
</head>
<body>
<%@ include file="/view/common/head.jsp" %>
<div>
    <%@ include file="/view/common/menu.jsp" %>
    <div class="container" style="border: 1px solid red;width: 88%;height: 85%;float: right;">
        <form action="/sys/user/list" method="get">
            账号：<input type="text" name="account" value="${account}">
            开始时间：<input type="date" value="" name="">
            结束时间：<input type="date" value="" name="">
            <input type="submit" value="查询" class="btn btn-info">
        </form>

        <a href="/view/sys/user/add.jsp" class="btn btn-success">添加</a>

        <table class="table table-bordered text-center">
            <tr>
                <td>序号</td>
                <td>部门名称</td>
                <td>账号</td>
                <td>真实姓名</td>
                <td>年龄</td>
                <td>性别</td>
                <td>出生日期</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>

            <c:forEach var="user" items="${list}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${user.deptName}</td>
                    <td>${user.account}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:if test="${user.sex==1}">男</c:if>
                        <c:if test="${user.sex==0}">女</c:if>
                    </td>
                    <td>
                        <fmt:parseDate var="birthDate" value="${user.birthDate}"
                                       pattern="yyyy-MM-dd"></fmt:parseDate>
                        <fmt:formatDate value="${birthDate}" pattern="yyyy年MM月dd日"></fmt:formatDate>
                    </td>
                    <td>
                        <fmt:parseDate var="createTime" value="${user.createTime}"
                                       pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                        <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td>
                        <a href="/sys/user/toUpdate?id=${user.id}">修改</a>
                        <a href="/sys/user/delete?id=${user.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <a href="/sys/user/list?account=${account}&page=1" class="btn btn-primary">首页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCurrent<=1 ? 1 : (page.pageCurrent-1)}"
           class="btn btn-info">上一页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCurrent>=page.pageCount ? page.pageCount : (page.pageCurrent+1)}"
           class="btn btn-info">下一页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCount}" class="btn btn-primary">尾页</a>
        <br>
        当前页：${page.pageCurrent}，总页数：${page.pageCount},总记录数：${page.count},每页显示记录数：${page.pageSize}
    </div>
</div>
</body>
</html>
