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
	<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/user/autherror.css"/>
<title>Auth Error</title>
</head>
<body>
<div class="container">
<h1  class="text-center text-warning"><i class="fas fa-user-lock"></i></h1>
<h3>
	해당페이지에 접근 권한이 없습니다.
</h3>
<h3>
	문제가 지속될 경우 관리자에게 문의해주시기 바랍니다.
</h3>
<div class="clearfix">
	<button class="btn btn-danger float-right ml-2" onclick="location.href='<%=request.getContextPath()%>/auth/report'">문의하기</button>
</div>
</div>
</body>
</html>