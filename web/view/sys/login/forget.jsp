<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
</head>
<body>
<div class="container">
    <div class="container-fluid" style="width: 40%;padding-top: 100px">
        <form action="/sys/user/forget">
            账号：<input type="text" name="account"><br><br>
            密码：<input type="text" name="password"><br><br>
            邮箱：<input type="text" name="email" id="email"><br><br>
            验证码：<input type="text" name="code">
            <input type="button" value="发送验证码" id="btn-send">
            <span id="time"></span><br><br>
            <input type="submit" value="修改">
        </form>
    </div>
</div>
</body>
<script>
    var time = 60;

    var timer;

    $(function () {
        $("#btn-send").click(function () {
            timer = window.setInterval(changeTime, 1000);
            $("#btn-send").attr("disabled", "disabled");

            var email = $("#email").val();
            $.ajax({
                url: "/sys/email/send",
                data: {email: email},
                type: "get",
                dataType: "text",
                success: function (data) {
                    alert(data);
                }
            });
        });
    });

    function changeTime() {
        --time;
        $("#time").text(time);
        if (time == 0) {
            $("#time").text("");
            time = 60;
            window.clearInterval(timer);
            $("#btn-send").attr("disabled", null);
        }
    }
</script>
</html>
