<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- css  -->
<link href="CSS/loginmainstyle.css" rel="stylesheet" type="text/css">


<!-- boot -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<!-- js -->
<script src="JS/login.js"></script>

<!-- modal -->
<script src="../modal/js/modal.js"></script>

<title>로그인화면</title>
</head>

<body class="container ">

	<!-- 로고  -->
	<div class="text-center col-12 ">
			<a id="login_logo" href="loginmain.jsp"> <img alt="Login_logo" src="../images/favicon.ico"></a>
	<!-- container  -->
		
		<div>
			<form name="frm" action="loginOk.do" method="POST">
			<div class="id_area ">
			<input class="col-12 col-sm-5" type="text" name="id" placeholder="아이디">
			</div>
			
			<div class="id_area ">
			<input class="col-12 col-sm-5" type="password" name="password" placeholder="비밀번호">
			</div>
			
			<div class="id_area ">
			<input id="submitbtn" class="col-12 col-sm-5 bg-success text-white btn-lg font-weight-bold " type="submit" value="로그인">
			</div>
			</form>
		
		</div> <!-- id, pw , 로그인 btn  라인  -->

		<!-- 찾기 라인  -->
			<div class="id_area ">
			<span class="spans col-6 col-sm-2 "><a class="finds" href="find.jsp"> 아이디,비번 찾기</a> </span> 
			<span class="spans col-6 col-sm-2 "><a class="finds" href="sign-up.jsp"> 회원 가입 </a> </span> 
			<span class="spans col-6 col-sm-2 "><a class="finds" href="gest_mode.jsp"> 비밀 입장하기 </a> </span> 
			
			</div>
		
		</div>
			<jsp:include page="../modal/component/modal.jsp" />
	<c:if test="${messageType != null &&  messageContent != null}">
		<script>
			showModal("${messageType}", "${messageContent}");
		</script>
		<%
			session.removeAttribute("messageType");
			session.removeAttribute("messageContent");
		%>
	</c:if>
	</body>
</html>