<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  csrf token 읽기 -->
<meta charset="UTF-8" name="csrf-token" content="${_csrf.token}">

<title>오늘 신고된 신고 목록</title>
</head>




<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!--  chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">

<script src='${pageContext.request.contextPath }/admin/JS/popup_report.js'></script>


<style>
body{

font-family: 'Do Hyeon', sans-serif;

}
</style>

<body>


	<div class="text-center mt-4"> <h1>금일 신고 목록중 신고 횟수가 10개 이상인 게시글 </h1></div>
	<br>
	<div id="list" class="text-center">
		<table class="table">
			<thead class="thead-dark">
				<th>POST_ID</th>
				<th>POST_TITLE</th>
				<th>RA_CODE</th>
				<th>UM_USERNAME</th>
				<th>POST_REPORTED</th>
			</thead>
			<tbody >
			
			</tbody>
		</table>
	</div>

</body>
</html>