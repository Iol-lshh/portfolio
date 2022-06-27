<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>LTNS_jsp</title>
</head>
<body>

<!-- div 이외 부분은 실행시 주석화 할 것! -->
<div id="mypost_board" class="sm_board card">
	<div class="board">
		<h3><a href="../board/board_list.do?root=MYPAGE">내가 쓴 글</a></h3>
			<div class="board-contents">
				<c:set var="_count" value="${mypost_board_cnt }"/>
				<fmt:parseNumber var= "count" integerOnly= "true" value= "${_count/10 }" />
				<c:forEach var="dto" items="${mypost_board }">
					<hr>
					<jsp:include page="./card/sm_card_list.jsp" >
						<jsp:param name="post_id" value="${dto.post_id }"/>
						<jsp:param name="post_title" value="${dto.title }"/>
						<jsp:param name="post_regdate" value="${dto.regdate }"/>
						<jsp:param name="post_viewCnt" value="${dto.viewCnt }"/>
					</jsp:include>
				</c:forEach>
				<!-- 방안1.프론트 단에서 처리 :  response된 데이터를, ajax와 script를 이용해, 값을 카드에 넣어주기. json 객체 활용 (비추) -->
				<!-- 방안2.백 단에서 처리 :  ajax와 script를 이용해, 값을 카드에 넣어주기. json 객체 활용 (for문 사용해야하나?) 
				include 되야할 것에 변수를 어떻게 넘길까?-->
			</div>
<!--  페이지네이션의 잔재
		<nav class="mt-5" aria-label="pagination">
		  <ul class="pagination" id="pagination">
		  	<li id="page_first" class="page_btn">&lt&lt</li>
		  	<li id="page_before" class="page_btn">&lt</li>
		  	
		  	<c:forEach var="i" begin="1" end="${count }" step="1">
		  		<li id="page_${i }" class="page_btn page_num_btn">${i }</li>
		  	</c:forEach>
		  	<li id="page_after" class="page_btn">&gt</li>
			<li id="page_last" class="page_btn">&gt&gt</li>
		  </ul>
		</nav>
-->
	</div>
</div>
<!-- div 이외 부분은 실행시 주석화 할 것! -->

</body>
</html>