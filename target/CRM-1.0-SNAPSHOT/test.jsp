<%--
  Created by IntelliJ IDEA.
  User: Peggy
  Date: 2022/4/9
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>

        $.ajax({
        url:"",
        data:{

        },
        type:"",
        dataType:"json",
        success:function (data){

        }
        })

        id
        owner
        name
        startDate
        endDate
        cost
        description
        createTime
        createBy
        editTime
        editBy

</body>
</html>
