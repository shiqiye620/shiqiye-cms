<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条-管理员中心</title>
<link rel="stylesheet" type="text/css" href="/resource/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.js"></script>
</head>
<body>
	<div class="container-fluid">

		<!-- 头 -->
		<div class="row" style="background-color: gray; height: 55px;">
			<div class="col-md-12">
				<a href="/"><img alt="" src="/resource/images/ccc.jpg" style="height: 55px">
				<font color="white">今日头条-管理员中心</font></a>
				
				<div style="float: right;padding-top: 6px">
					<c:if test="${null!=sessionScope.admin}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<font color="white">登录信息</font>
							</button>
							<div class="dropdown-menu">
							<a class="dropdown-item" href="#">${sessionScope.admin.username }</a>
								<a class="dropdown-item" href="/passport/loginout">注销</a>
							</div>
						</div>
					</c:if>
				</div>

			</div>
		</div>

		<!-- 具体内容 -->
		<div class="row" style="padding-top: 5px">
			<!-- 左侧菜单 -->
			<div class="col-md-2 rounded"
				style="background-color: #9ff6ff; text-align: center; height: 500px;">
				<nav class="nav flex-column" style="padding-left: 12px;">
					<a class="list-group-item active" href="#" data="/admin/articles">文章审核</a>
					<a class="list-group-item" href="#" data="/admin/users">用户管理</a> <a
						class="list-group-item" href="#">栏目管理</a> <a
						class="list-group-item" href="#">分类管理</a> <a
						class="list-group-item " href="#">系统设置</a>
				</nav>
			</div>
			<!-- 右侧具体内容 -->
			<div class="col-md-10" id="center"
				style="background-color: pink; height: 500px;"></div>
		</div>

	</div>
	<script type="text/javascript">
		$(function() {
			//默认显示我的文章
			$("#center").load("/admin/articles");
			$("a").click(function() {
				var url = $(this).attr("data");
				//去除样式
				$("a").removeClass("active");
				//让当前点击的a添加选中样式
				$(this).addClass("list-group-item active")
				//在中间区域显示url的内容
				$("#center").load(url);
			})

		})
	</script>
</body>
</html>