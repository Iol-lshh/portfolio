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
	href="<%=request.getContextPath()%>/resources/css/user/lookupid.css" />
<title>아이디 찾기</title>
</head>
<body>
	<h1 class="mb-5">아이디 찾기</h1>
	<div class="container">
		<form id="lookupid">
			회원가입시 등록한 이메일
			<div class="input-group mb-5">
				<input class="form-control" type="email" id="um_username"
					name="um_username" />
				<button class="btn btn-primary ml-2" id="emainBtn" type="button">이메일로
					찾기</button>
			</div>
			닉네임
			<div class="input-group">
				<input class="form-control" type="text" id="um_nickname"
					name="um_nickname" />
				<button class="ml-2 btn btn-primary" id="nickBtn" type="button">닉네임으로
					찾기</button>
			</div>
			<input type="hidden" id="message" name="message" /> <input
				type="hidden" id="kind" name="kind" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<script
		src="<%=request.getContextPath()%>/resources/js/user/lookupid.js"></script>
</body>
</html>