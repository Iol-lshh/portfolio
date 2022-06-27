<%@page import="com.ltns.rest_area.domain.user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/user/list.css" />
<title>게시글 즐겨찾기</title>
</head>
<body>
<jsp:include page="../../header/header.jsp" />
<div class="container body">
	<div class="table">
		<form id="listForm">
			<input type="hidden" id="uid" name="um_uid"
				value="<%=((UserDTO) request.getSession().getAttribute("userObj")).getUm_uid()%>" />
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="text-center table__no">#</th>
						<th class="text-center table__no">번호</th>
						<th class="text-center table__title">제목</th>
						<th class="text-center table__user">글쓴이</th>
						<th class="text-center table__date">작성일</th>
					</tr>
				</thead>
				<tbody id="jsonList">
				</tbody>
			</table>
			<input type="hidden" id="table" name="table" value="post_like" /> <input
				type="hidden" id="column" name="column" value="post_id" /> <input
				type="hidden" id="token" name="${_csrf.parameterName }"
				value="${_csrf.token }" />
		</form>
		<button id="removeBtn" class="btn btn-success float-right">즐겨찾기
			해제</button>
	</div>
	<div class="center">
		<ul class="pagination" id="pagination">
		</ul>
	</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/resources/js/user/likePosts.js"></script>
</body>
</html>