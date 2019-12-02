<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="/view/common/head.jsp" %>
<div>
    <%@ include file="/view/common/menu.jsp" %>
    <div class="container" style="border: 1px solid red;width: 88%;height: 85%;float: right;">
        <div class="container-fluid" style="width: 40%">
            <form action="/sys/user/update" method="get" id="form-add">
                <div class="form-group">
                    <label>部门</label>
                    <select id="dept" name="deptId" class="form-control">

                    </select>
                </div>
                <input type="text" name="id" value="${user.id}" style="display:none;">
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" class="form-control" id="account" name="account" value="${user.account}">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="text" class="form-control" id="password" name="password" value="${user.password}">
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <input type="text" class="form-control" id="age" name="age" value="${user.age}">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <input type="radio" id="male" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if>><label
                        for="male">男</label>
                    <input type="radio" id="female" name="sex" value="0"
                           <c:if test="${user.sex==0}">checked</c:if>><label
                        for="female">女</label>
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" id="email" name="email" value="${user.email}">
                </div>
                <div class="form-group">
                    <label>出生日期</label>
                    <input type="date" class="form-control" id="birthDate" name="birthDate" value="${user.birthDate}">
                </div>
                <!--居中-->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">修改</button>
                    <a href="/sys/user/list" class="btn btn-danger">返回</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    $(function () {
        $.ajax({
            url: "/sys/dept/list",
            data: "",
            type: "get",
            dataType: "json",
            success: (function (data) {
                var html = '<option value="-1">请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html = html + '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#dept").append(html);
            })
        });
    })
</script>
</html>
