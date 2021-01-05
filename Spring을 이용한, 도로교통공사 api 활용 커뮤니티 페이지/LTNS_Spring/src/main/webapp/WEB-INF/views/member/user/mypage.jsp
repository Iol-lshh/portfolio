<%@page import="com.ltns.rest_area.domain.user.UserDTO"%>
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
	href="${pageContext.request.contextPath }/resources/css/user/mypage.css" />
<title>MyPage</title>
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
				<button class="btn btn-outline-primary" onclick="logout();">로그아웃</button>
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

					<li class="nav-item "><a class="nav-link text-white"
						href="${pageContext.request.contextPath}/member/user/info"> <span
							data-feather="shopping-cart"></span> <i class="fas fa-chart-line"></i>&nbsp;&nbsp;
							나의 정보
					</a></li>

					<li class="nav-item "><a
						class="nav-link  bg-info  text-white "
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
		<div class="contents">
			<div class="column_box">
				<div class="contents_box">
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							★ 휴게소</li>
						<div id="likeRest"></div>
					</ul>
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							★ 음식</li>
						<div id="likeFood"></div>
					</ul>
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							★ 주유소</li>
						<div id="likeGas"></div>
					</ul>
				</div>
				<div class="contents_box mt-5">
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							★ 게시글</li>
						<div id="likePost"></div>
					</ul>
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							내가 쓴 글</li>
						<div id="posts"></div>
					</ul>
					<ul class="list-group">
						<li class="list-group-item active text-center" aria-current="true">
							내가 쓴 덧글</li>
						<div id="comments"></div>
					</ul>
				</div>
			</div>
		</div>
		<form class="hidden hide" id="mypageForm">
			<input type="hidden" id="um_uid" name="um_uid"
				value='<%=((UserDTO) request.getSession().getAttribute("userObj")).getUm_uid()%>' />
			<input type="hidden" id="message" name="message" value="findByUid" />
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

	<form action="<%=request.getContextPath()%>/logoutProcess"
		method="POST" id="logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script src="<%=request.getContextPath()%>/resources/js/user/mypage.js"></script>
	<script>
		function logout() {
			console.log($('#logout'));
			$('#logout').submit();
		}
	</script>
</body>
</html>
