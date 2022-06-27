<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="main.java.com.model.*" %>
<%@page import="main.java.com.model.mainpage.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최신순게시판</title>
</head>
<body>

<!-- div 이외 부분은 실행시 주석화 할 것! -->

<div id="nearest_board">

	<c:forEach var="i" begin="0" end="4" step="1">
		<jsp:include page="./card/card_post.jsp" >
			<jsp:param name="i" value="${i }"/>
			<jsp:param name="post_id" value="${nearest_board[i].post_id }"/>
			<jsp:param name="title" value="${nearest_board[i].post_contents.title }"/>
			<jsp:param name="writer" value="${nearest_board[i].nickname }"/>
			<jsp:param name="regdate" value="${nearest_board[i].regdate }"/>
			<jsp:param name="contents" value="${nearest_board[i].post_contents.contentsText }"/>
			<jsp:param name="thumbnailPath" value="${nearest_board[i].post_contents.thumbnailPath }"/>
		</jsp:include>
		<hr>
	</c:forEach>
	<!-- 방안1.프론트 단에서 처리 :  response된 데이터를, ajax와 script를 이용해, 값을 카드에 넣어주기. json 객체 활용 (비추) -->
	<!-- 방안2.백 단에서 처리 :  ajax와 script를 이용해, 값을 카드에 넣어주기. json 객체 활용 (for문 사용해야하나?) 
	include 되야할 것에 변수를 어떻게 넘길까?-->
	<script src="JS/board/cardmaker_nearest_board.js"></script>
	
</div>
	<!-- 프론트 단에서 처리 : 버튼을 누르면 request를 보내고, response된 데이터를, ajax와 script를 이용해,
	카드를 3개 추가 생성, 값을 카드에 넣어주기. json 객체 활용 -->
<button id="nearest_board-add_post">더보기</button>
<script src="JS/board/nearest_board.js"></script>
<!-- div 이외 부분은 실행시 주석화 할 것! -->

