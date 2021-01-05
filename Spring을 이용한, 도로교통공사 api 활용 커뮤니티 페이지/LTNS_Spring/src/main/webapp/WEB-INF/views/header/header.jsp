<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<!-- css -->
<link href='${pageContext.request.contextPath }/resources/css/header.css' rel="stylesheet"/>

<header>
	<nav id="search" class="navbar navbar-default">
		<div class="container-fluid">
			<!-- 내용 -->
	   		<div id="usernav" class="right">
			<c:choose>
				<c:when test="${userObj != null }">
				<p id="header_um_uid" class="undisplay">${userObj.um_uid }</p>
	   			<a id="header_um_username" class="undisplay" href="#">${userObj.um_username }</a>
	   			<a href="#" onclick="logout();">로그아웃</a>
	   				<c:forEach var="item" items="${auth}">
						<c:if test="${item eq 'ROLE_ADMIN' }">
							<a href="${pageContext.request.contextPath }/admin/dashboard">관리</a>
						</c:if>
					</c:forEach>
	   			<a class="" href="${pageContext.request.contextPath }/member/user/info">${userObj.um_nickname }</a>
				</c:when>
				<c:otherwise>
					<a class="" href="${pageContext.request.contextPath }/auth/user/login">로그인</a>
				</c:otherwise>
			</c:choose>
	   		</div>
			
			<!-- 로고 -->
			<div id="headerlogo" class="navbar-header">
		      <a class="navbar-brand" href="/rest_area/rest_area/front">
		        <img alt="Brand" src="${pageContext.request.contextPath }/resources/img/logo.png">
		      </a>
	   		</div>
	   		
		</div>
	</nav>
</header>
	<form action="<%=request.getContextPath()%>/logoutProcess"
		method="POST" id="logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function logout() {
			console.log($('#logout'));
			$('#logout').submit();
		}
	</script>
<!-- js -->
<script src='${pageContext.request.contextPath }/resources/js/header.js'></script>

