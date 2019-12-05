<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%@ include file="/view/common/head.jsp" %>
<div>
    <%@ include file="/view/common/menu.jsp" %>
    <div class="container" style="border: 1px solid red;width: 88%;height: 85%;float: right;">
        <div class="container-fluid" style="width: 40%">
            <form action="/sys/dept/add" method="get" id="form-add">
                <div class="form-group">
                    <label>部门名称</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入部门名称">
                </div>

                <!--居中-->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="reset" class="btn btn-default">重置</button>
                    <a href="/sys/dept/listAll" class="btn btn-danger">返回</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
</script>
</html>
