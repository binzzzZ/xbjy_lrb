<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航栏</title>
</head>
<body>
<div style="border: 1px solid red;height:10%;">
    导航栏helloworld
    <div>
        <div><big>在线人数：${applicationScope.applicationLoginCount}</big></div>
        <div style="float: right"><a href="/sys/login/logout" class="btn btn-warning">Logout</a></div>
    </div>
</div>
</body>
</html>
