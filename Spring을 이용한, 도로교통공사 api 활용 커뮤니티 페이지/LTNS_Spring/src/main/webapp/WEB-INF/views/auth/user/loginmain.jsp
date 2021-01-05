<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- css  -->
<link
	href="${pageContext.request.contextPath }/resources/css/user/loginmainstyle.css"
	rel="stylesheet" type="text/css">


<!-- boot -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<!-- js -->
<script
	src="${pageContext.request.contextPath }/resources/js/user/login.js"></script>


<title>로그인화면</title>
</head>

<body class="container ">

	<!-- 로고  -->
	<div class="text-center col-12 ">
		
			<a id="login_logo" href="${pageContext.request.contextPath }${pageContext.request.contextPath }/front"> <img alt="Login_logo" src="/rest_area/resources/img/logo.png"></a>
	
		<!-- container  -->

		<div>
			<form name="frm"
				action="${pageContext.request.contextPath }/loginProcess"
				method="POST">
				<div class="id_area ">
					<input class="col-12 col-sm-5" type="text" name="username"
						placeholder="아이디">
				</div>

				<div class="id_area ">
					<input class="col-12 col-sm-5" type="password" name="password"
						placeholder="비밀번호">
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="id_area ">
					<input id="submitbtn"
						class="col-12 col-sm-5 bg-success text-white btn-lg font-weight-bold "
						type="submit" value="로그인">
				</div>

			</form>

			<div class="id_area ">
				<input id="submitbtn"
					class="col-12 col-sm-5 bg-primary text-white btn-lg font-weight-bold "
					type="button" value="회원가입"
					onclick="location.href='${pageContext.request.contextPath}/auth/user/join'">
			</div>
		</div>
		<!-- id, pw , 로그인 btn  라인  -->

		<!-- 찾기 라인  -->
		<div class="id_area ">
			<span class="spans col-6 col-sm-2 "><a class="finds"
				href="<%=request.getContextPath()%>/auth/user/lookupid"> 아이디 찾기</a>
			</span> <span class="spans col-6 col-sm-2 "><a class="finds"
				href="<%=request.getContextPath()%>/auth/user/lookuppw"> 비밀번호 찾기
			</a> </span>

		</div>

	</div>
	<c:if test="${messageType != null &&  messageContent != null}">
		<script>
			alert("${messageContent}");
		</script>
	</c:if>

</body>
</html>