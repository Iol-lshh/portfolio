<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/user/info.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/user/logout.css" />
<title>회원 정보 수정</title>
</head>
<body>


	<!-- dashboard nav -->
	<nav
		class="navbar navbar-dark  sticky-top bg-white flex-md-nowrap p-0 mt-0">
		<a class="navbar-brand bg-dark col-sm-3 col-md-2 mr-0 text-center" href="${pageContext.request.contextPath }${pageContext.request.contextPath }/front">LTNS HOME
			</a>
		<!--  barnd 설정  -->

		<ul class="navbar-nav  d-flex flex-row-reverse  ">

			<li class="nav-item  mr-5">
				<button class="btn btn-outline-primary" onclick="$('#logoutForm').submit();">로그아웃</button>
			</li>
			<li class="nav-item  mr-5"><span class="font-weight-bold mt-2"
				style="font-size: 14px;"> <c:set var="bool" value="false" />
					<c:forEach var="item" items="${auth}">
						<c:if test="${item eq 'ROLE_ADMIN' }">
							<c:set var="bool" value="true" />
						</c:if>
					</c:forEach> <c:choose>
						<c:when test="${bool eq 'true' }">
							<c:out value="Hello Master" />

						</c:when>
						<c:otherwise>
							<c:out value="Hello Member" />
						</c:otherwise>
					</c:choose>
			</span> <br /> <span class="welcomes" style="font-size: 12px;">${userObj.um_nickname}</span></li>
			<li class="nav-item mr-3">
				<div>
					<img src="${pageContext.request.contextPath }/resources/img/1.PNG">
				</div>
			</li>
			<li class="nav-item   mr-4 text-center"><a type="button"
				class="nav-link text-dark" href="#"><i class="fas fa-bell"></i></a>
			</li>

			<li class="nav-item  mr-4 text-center"><a
				class="nav-link text-dark" href="#ex1" rel="modal:open"><i
					class="fas fa-bell"></i></a></li>
		</ul>


	</nav>
	<!-- nav header 라인 -->

	<div class="box">
		<nav class="col-md-2 d-none d-md-block bg-dark sidebar">
			<div class="sidebar-sticky">
				<ul class="nav flex-column">

					<li class="nav-item "><a class="nav-link bg-info text-white"
						href="${pageContext.request.contextPath}/member/user/info"> <span
							data-feather="shopping-cart"></span> <i class="fas fa-chart-line"></i>&nbsp;&nbsp;
							나의 정보
					</a></li>

					<li class="nav-item "><a class="nav-link text-white "
						href="${pageContext.request.contextPath}/member/user/mypage">
							<span data-feather="shopping-cart"></span> <i
							class="far fa-calendar-check"></i>&nbsp;&nbsp; 내 페이지
					</a></li>

					<c:forEach var="item" items="${auth}">

						<c:if test="${item eq 'ROLE_ADMIN' }">
							<li class="nav-item"><a class="nav-link text-white"
								href="${pageContext.request.contextPath}/admin/dashboard"> <span
									data-feather="shopping-cart"></span> <i
									class="far fa-calendar-check"></i>&nbsp;&nbsp; 관리자 페이지
							</a></li>
						</c:if>

					</c:forEach>

				</ul>

			</div>
		</nav>
		<div class="contents mr-5 ml-5">
			<div class="column_box">
				<form id="modiyfyForm"
					action="${pageContext.request.contextPath }/member/user/modify">
					<table class="table table-hover table-bordered">
						<thead>
							<th colspan="3" class="text-center">나의 정보</th>
						</thead>
						<tbody>
							<tr>
								<th>아이디 (이메일)</th>
								<td colspan="2">${userObj.um_username }</td>
								<input type="hidden" id="um_username" name="um_username"
									value="${userObj.um_username }" />
							</tr>
							<tr>
								<th>비밀번호 변경</th>
								<td colspan="2"><input type="password" class="form-control"
									id="um_password" name="um_password" /></td>
							</tr>
							<tr>
								<th>비밀번호 확인</th>
								<td colspan="2"><input class="form-control" type="password"
									id="passwordCheck" name="passwordCheck" /></td>
							</tr>
							<tr>
								<td colspan="3" id="password" class="error text-center hide"></td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td><input class="form-control" id="um_nickname"
									name="um_nickname" value="${userObj.um_nickname }" disabled /></td>
								<td class="text-center"><button id="nickBtn" type="button"
										class="btn btn-primary">변경</button></td>
							</tr>
							<tr>
								<td colspan="3" id="nickname" class="error text-center hide"></td>
							</tr>
							<tr>
								<th>가입일</th>
								<td colspan="2">${userObj.um_regdate }</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" id="message" name="message" /> <input
						type="hidden" id="token" name="${_csrf.parameterName }"
						value="${_csrf.token }" />
				</form>
				<button id="changeBtn" class="btn btn-primary form-control">회원
					정보 변경</button>
				<button id="deleteBtn" class="btn btn-danger form-control mt-2">회원
					탈퇴</button>
			</div>
		</div>
	</div>
	<div id="logout"></div>

	<script type="module"
		src="${pageContext.request.contextPath }/resources/js/user/info.js"></script>
	<script type="module"
		src="${pageContext.request.contextPath }/resources/js/user/logout.js"></script>
</body>
</html>
