<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTNS_jsp</title>
	<link rel="stylesheet" href="../lib/bootstrap-4.5.3-dist/css/bootstrap.min.css" />
	<script src="../lib/jquery.js"></script>
	<script src="../lib/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/5d1d5aa1e7.js" crossorigin="anonymous"></script>
	<script src="../fixedbtn/fixedbtn.js"></script>
	<link href="./CSS/mainpage.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/empath_board.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/nearest_board.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/viewcnt_board.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/card/card_post.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/card/sm_card_album.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/card/sm_card_list.css" rel="stylesheet" type="text/css">
	<link href="./CSS/board/card/sm_card_post.css" rel="stylesheet" type="text/css">
	<link href="../header/css/header.css" rel="stylesheet" type="text/css">
	<link href="../footer/css/footer.css" rel="stylesheet" type="text/css">
	<link href="../fixedbtn/fixedbtn.css" rel="stylesheet" type="text/css">
	
</head>
<body class="container">
	<jsp:include page="../header/component/header.jsp" />
	<br>
	<main class="my-2">
		<jsp:include page="./components/board/viewcnt_board.jsp"/>
		<jsp:include page="./components/board/empath_board.jsp"/>
		<hr>
		<br>
		<jsp:include page="./components/board/nearest_board.jsp"/>
	</main>
	<br>
<jsp:include page="../footer/component/footer.jsp" />
	<jsp:include page="../fixedbtn/fixedbtn.jsp"/>

</body>
</html>