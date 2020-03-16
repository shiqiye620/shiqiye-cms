<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条-个人中心</title>
<link rel="stylesheet" type="text/css" href="/resource/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row">
			<div class="col-md-12" style="background-color: purple; height: 60px">
				<a href="/"><img alt=""
					src="/resource/images/AB36C424BD5B00DF27D3BAD0D9F917D2.jpg"
					style="height: 60px;"> <span style="color: white">今日头条-个人中心</span></a>

					<div style="float: right;padding-top: 7px">
						<c:if test="${null!=sessionScope.user}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"><font color="white">登录信息</font></button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username}</a> <a
									class="dropdown-item" href="/my">个人中心</a> <a
									class="dropdown-item" href="/passport/loginout">注销</a>
							</div>
						</div>
					</c:if>
					</div>
				
			</div>
		</div>
		<!-- body -->
		<div class="row" style="padding-top: 5px">
			<div class="col-md-2">
				<!-- 左侧菜单 -->
				<ul class="list-group">
					<li class="list-group-item active" style="background-color:"><a
						href="#" data="/my/articles" style="color: white">我的文章</a></li>
					<li class="list-group-item" style="background-color: gray"><a
						href="#" data="/my/publish" style="color: white">发布文章</a></li>
					<li class="list-group-item" style="background-color: gray"><a
						href="#" data="/my/collection" style="color: white">我的收藏</a></li>
					<li class="list-group-item" style="background-color: gray"><a
						href="#" style="color: white">我的评论</a></li>
					<li class="list-group-item" style="background-color: gray"><a
						href="#" style="color: white">个人信息</a></li>
				</ul>


			</div>
			<!-- 内容显示区域 -->
			<div class="col-md-10" id="center">
				<!-- 先加载kindeditor的样式 -->
				<div style="display: none">
					<jsp:include page="/WEB-INF/view/my/publish.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//默认显示我的文章
			$("#center").load("/my/articles");
			$("li").click(function() {
				var url = $(this).children().attr("data");
				//去除样式
				$("li").removeClass("active");
				//让当前点击的li添加选中样式
				$(this).addClass("list-group-item active")
				//追加背景
				$("li").css("background-color", "gray")
				//删除选中的背景
				$(this).css("background-color", "")

				//在中间区域显示url的内容
				$("#center").load(url);
			})
		})
	</script>
</body>
</html>