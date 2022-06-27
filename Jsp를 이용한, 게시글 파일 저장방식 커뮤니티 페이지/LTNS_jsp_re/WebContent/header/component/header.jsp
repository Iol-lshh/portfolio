<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div id="login">
		<%
			String gest_s = (String) session.getAttribute("grade");
		%>
		<%if(!gest_s.equals("gest")){ %>
     	<a class="nav__login btn mr-2" href="<%=request.getContextPath() %>/mypage/mypage.do">${nickname }</a>
       	<%} %>
       	<a id ="btn_login" class="btn btn-secondary" href="<%=request.getContextPath() %>/membermanage/logoutOk.do">로그아웃</a>
	</div>
	<div>
		<a class="navbar-brand" href="../mainpage/mainpage.do">
	    	<img src="../images/logo.png" alt="logo" class="navbar__logo"/>
	    </a>
	</div>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
	     <button
	       class="navbar-toggler"
	       type="button"
	       data-toggle="collapse"
	       data-target="#navbarSupportedContent"
	       aria-controls="navbarSupportedContent"
	       aria-expanded="false"
	       aria-label="Toggle navigation"
	     >
	     	<span class="navbar-toggler-icon"></span>
	     </button>
	     <div class="collapse navbar-collapse" id="navbarSupportedContent">
	     	<ul class="navbar-nav mr-auto">
	        	<li class="nav-item">
	          		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=NOTICE">공지사항</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=MOVIE">영화게시판</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=BOOK">도서게시판</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=SPORTS">스포츠게시판</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=GAME">게임게시판</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=EMPATHIZE">공감게시판</a>
	         	</li>
	         	<li class="nav-item">
	           		<a class="nav-link" href="<%=request.getContextPath() %>/board/board_list.do?root=VIEWCNT">인기게시판</a>
	         	</li>
	       	</ul>
	    </div>
	</nav>
</header>
