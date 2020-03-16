<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的收藏</title>
</head>
<body>
	<c:forEach items="${info.list}" var="collect">
		<a href="${collect.url}">
			<p>${collect.text}</p>
		<p>
			<fmt:formatDate value="${collect.created}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</p>
		</a>
			<button type="button" onclick="deleteCollect(${collect.id})" class="btn btn-warning">取消收藏</button>
		<hr>
	</c:forEach>
	<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
	<script type="text/javascript">
		function goPage(page) {
			$("#center").load("/my/collection?page=" + page)
		}
		
		//删除收藏
		function deleteCollect(id){
			$.post("/deleteCollect",{id:id},function(flag){
				if(flag){
					alert("取消收藏成功");
					location.reload();
				}else{
					alert("取消收藏失败,需要登录后才能取消收藏");
				}
			},"json")
		}
	</script>
</body>
</html>