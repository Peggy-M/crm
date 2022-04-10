<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {

			//这个的意思就是将登录页面作为最顶页
			if (window.top!=window){
				window.top.location=window.location;
			}

			//每一次加载刷新界面都会清除登录信息
			$("#loginAct").val("");

			//页面加载完毕以后用户用文本框自动获取焦点
			$("#loginAct").focus();

			//按下登录按钮实现登录
			$("#submitBtn").click(function () {
				login();
			})

			//按下回车键界面登录 keydown(参数) 键盘按下出发事件 event返回记录的键盘
			$(window).keydown(function (event) {
				//返回键盘按下的值
				var key=event.keyCode;
				if(13==key){
					login();
				}
			})
		})

		//登录方法 可以直接调用 一定写在fuction的外面
		function login(){
			//获取用户的登录密码与用户名
			//trim()方法去除 字符串的前后空格
			var loginAct = $.trim($("#loginAct").val());
			var loginPwd = $.trim($("#loginPwd").val());

			if(loginAct==""||loginPwd==""){
				$("#msg").text("用户名或密码为空");
				return false;
			}

			$.ajax({
				url:"settings/user/login.do",
				data:{
					"loginAct":loginAct,
					"loginPwd":loginPwd
				},
				//由于涉及到了密码 则这里的使用的就是post请求
				//git请求 会将密码携带在url的后面一起返回 这样是不安全的
				type:"post",
				dataType:"json",
				success:function (data){
					/*
					*  data:
					* 		这里返回的数据中有
					* 		1、账号密码验证成功与否 success ----> false/true
					* 		2、如果登录失败，返回登录失败的原因 msg ----->失败的原因
					* */
					//alert("success---->"+data.success);
					if (data.success)
					{
						//跳转到相应的响应主页
						window.location.href="workbench/index.jsp"
					}else{
						//返回具体的失败原因
					//	alert("msg--------->"+data.msg);
						$("#msg").html(data.msg);
					}
				}
			})

		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red"></span>
						
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;" id="submitBtn">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>