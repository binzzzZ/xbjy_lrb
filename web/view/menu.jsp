<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<script src="/static/js/jquery-3.4.1.js" type="text/javascript"></script>

<script>
    $(function () {
<<<<<<< HEAD
=======
        // $.ajax({
        //     url: "/sys/menu",
        //     type: "get",
        //     data: "",
        //     dataType: "json",
        //     success: function (data) {
        //         var html = '';
        //         //外层循环（取出1级菜单数据）
        //         for (var i = 0; i < data.length; i++) {
        //             //只选择1级菜单
        //             if (data[i].type == 1) {
        //                 html = html + data[i].name + "</br>";
        //                 html = html + '<ul>';
        //                 //内层循环（取出2级菜单数据）
        //                 for (var j = 0; j < data.length; j++) {
        //                     //2级菜单和1级菜单产生关联
        //                     if (data[j].pId == data[i].id) {
        //                         //只选择2级菜单
        //                         if (data[j].type == 2) {
        //                             html = html + '<li><a href="' + data[j].menuUrl + '">' + data[j].name + '</a></li>';
        //                         }
        //                     }
        //                 }
        //                 html = html + '</ul>';
        //             }
        //         }
        //         $("#div-menu").append(html);
        //     }
        // });
>>>>>>> github/master

        $.ajax({
            url: "/sys/menu",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var parent = data.parent;
                var son = data.son;
                var html = "";
                for (var i = 0; i < parent.length; i++) {
                    html = html + parent[i].name;
                    html = html + '<ul>';
                    for (var j = 0; j < son.length; j++) {
                        if (parent[i].id == son[j].pId) {
                            html = html + '<li><a href="' + son[j].menuUrl + '">' + son[j].name + '</a></li>';
                        }
                    }
                    html = html + '</ul>';
                }

                $("#div-menu").append(html);
            }
        });
    })
</script>
<body>
<div id="div-menu" style="border: 1px solid red;width: 10%;height: 85%;float: left;">

</div>
</body>
</html>
