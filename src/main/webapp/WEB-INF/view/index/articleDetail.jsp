<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:if test="${article.keywords!=null}">
	<meta name="${article.keywords }" content="${article.origin}">
</c:if>
<title>${article.title}</title>
<link rel="stylesheet" type="text/css" href="/resource/bootstrap.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap.js"></script>
</head>
<body>
	<div class="containner-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 30px">
				&nbsp;<font color="white" size="3px"
					style="margin-left: 10px; padding-top: 2px;">下载APP</font>&nbsp;&nbsp;&nbsp;&nbsp;
				<font color="white" size="3px"
					style="margin-left: 10px; padding-top: 2px;">注册头条号</font>
			</div>
		</div>

		<div class="row" style="padding-top: 10px;">
			<div class="col-md-1"></div>
			<div class="col-md-7">
				<h2>${article.title}</h2>
				<p>${article.user.username}&nbsp;&nbsp;<fmt:formatDate
						value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss" />
				<c:if test="${article.origin!=null}">
					&nbsp;文章来源:${article.origin}
				</c:if>
				</p>
				<c:if test="${collect!=null}">
				 <button type="button" onclick="deleteCollect()" class="btn btn-link">★&nbsp;  取消收藏</button>
				</c:if>
				<c:if test="${collect==null}">
				     <button type="button" onclick="collect()" class="btn btn-link">☆ &nbsp;  未收藏</button>
				</c:if>
				
				<hr>
				${article.content}

				<hr>
				<!-- 文章评论 -->
				<c:if test="${null!=sessionScope.user}">
				<div>
					<h5>文章评论:</h5>
					<textarea rows="8" cols="20" style="width: 753px" name="content"></textarea>
					<br>
					<button type="button" onclick="addComment()" class="btn btn-info">提交评论</button>
				</div>
				</c:if>
				<div>
					<!-- 显示评论内容 -->
					<c:forEach items="${info.list}" var="comment">
						<h5>${comment.user.username}
						<fmt:formatDate
						value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss" />
						</h5>
						${comment.content}
					</c:forEach>
				</div>
			</div>
			<div class="col-md-4">
			
				<div class="card" style="width: 18rem; margin-top: 6px">
					<div class="card-header">评论排行榜</div>
					<div class="card-body">
						<!-- 最新文章 --10篇 -->
						<c:forEach items="${info2.list}" var="article" varStatus="i">
							<p>${i.count}.${article.title}</p>
						</c:forEach>
					</div>
				</div>
				
			</div>
		</div>

	</div>
	<script type="text/javascript">
	//删除收藏
	function deleteCollect(){
		var id='${collect.id}';
		$.post("/deleteCollect",{id:id},function(flag){
			if(flag){
				alert("取消收藏成功");
				location.reload();
			}else{
				alert("取消收藏失败,需要登录后才能取消收藏");
			}
		},"json")
	}
	//增加收藏
	function collect(){
		//获取文章标题
		var title='${article.title}';
		//文章的url
		var url=location.href;
		$.post("/collect",{text:title,url:url},function(flag){
			if(flag){
				alert("收藏成功")
				location.reload();
			}else{
				alert("收藏失败,需要登录后才能登陆");
			}
		},"json")
	}
	//增加评论
		function addComment(){
			var articleId=${article.id};
			$.post("/addComment",{articleId:articleId,content:$("[name=content]").val()},function(flag){
				if(flag){
					alert("评论成功")
					location.reload();
				}else{
					alert("评论失败,需要登录后才能登陆")
				}
			},"json")
		}
	
		$(function(){
			var id=${article.id};
			$.post("/updateHits",{id:id},function(){
				
			},"json")
		})
	</script>
</body>
</html>