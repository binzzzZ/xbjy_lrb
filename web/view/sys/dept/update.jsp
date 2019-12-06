<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
</head>
<body>
<%@ include file="/view/common/head.jsp" %>
<div>
    <%@ include file="/view/common/menu.jsp" %>
    <div class="container" style="border: 1px solid red;width: 88%;height: 85%;float: right;">
        <div class="container-fluid" style="width: 40%">
            <form action="/sys/dept/update" method="get" id="form-add">
                <input type="text" name="id" value="${dept.id}" style="display:none;">

                <div class="form-group">
                    <label>部门名称</label>
                    <input type="text" class="form-control" id="name" name="name" value="${dept.name}">
                </div>

                <!--居中-->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">修改</button>
                    <a href="/sys/dept/listAll" class="btn btn-danger">返回</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
