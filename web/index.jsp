<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="/sys/login/login">
    账号：<input type="text" name="account" id="account"><br><br>
    密码：<input type="text" name="password" id="password"><br><br>

    <img src="/sys/login/getPic" alt="无法加载" id="img" onclick="getPic()"><br><br>
    验证码：<input type="text" name="picCode" placeholder="验证码" id="picCode"><br><br>

    7天免登录：<input type="checkbox" name="remember" value="1"><br><br>
    <input type="submit" name="" value="登录">
</form>
<a href="/view/sys/login/forget.jsp">忘记密码</a>
</body>
<script>
    function getPic() {
        document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }
</script>
</html>
