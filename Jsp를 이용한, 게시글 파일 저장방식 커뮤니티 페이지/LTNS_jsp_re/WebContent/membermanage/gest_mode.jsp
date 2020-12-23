<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- css link  -->
<link rel="stylesheet" type="text/css" href="CSS/gest_mode.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

<!-- javascript link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="JS/gest_mode.js"></script>

<body>
<div id="img_div" class="main_article">
	<img id="img_id" class="main_imgs"  src="../images/nyancat.gif">
</div>

<div class="div_box">
   	<span class="gest_header">Gest Mode</span> 
   	<div class="gest_articles">
	<span> 댓글, 추천 버튼을 사용할 수 없습니다. </span> <br>
	</div>
	<div class="gest_articles">
	<span> 글을 읽는것만 가능합니다. </span> <br>
	</div>	
	<div class="gest_articles">
	<span> ( 조회수 반영 )</span> <br>
	</div>	
	
	
	
	<button onclick="location.href='gest.jsp'">입장하기</button>
	<button onclick="location.href='sign-up.jsp'">회원가입</button>
	<button onclick="location.href='loginmain.jsp'">돌아가기</button>

</div>



	
<div class="hidden">
<button id="hidden_btn">버튼</button>
</div>



</body>
</html>