<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>일정 추가</title>

<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!--  date picker  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src='${pageContext.request.contextPath }/admin/JS/schedule.js'></script>

</head>
<style>
body{

font-family: 'Do Hyeon', sans-serif;
}
</style>

<body>

<div class="container mt-4" id="popupGroup">
	
	<div class="text-center">
		<h1> 일정 추가 </h1>
	</div> <!--  end head -->
	
	<div class ="text-center">
		<form id="scheduleData">
			 <div class="text-center">
				<input class="form-control" id="subject" type="text" name="subject" placeholder="제목 입력해주세요"  maxlength="6">
			 </div>
			 
			 
			 <div class="mt-2">
			 	<h3> 시작일 </h3>
			 	<input class="form-control" id="startDate" type="text" name ="startDate" id="startDate">
			 </div>
			 
			 
			 <div class="mt-2">
			 	<h3 > 종료일 </h3>
			 	<input class="form-control" id="endDate" type="text" name ="endDate" id="endDate">
			 </div>
			 <input type="hidden" name="csrfToken" value="${_csrf.token}" />
		</form>
		
		<button class="btn mt-2 btn-outline-success text-center" type="button" onclick="click_ok();"> 확인 </button>
		
	</div> <!--  end body  -->

</div> <!-- end group  -->


</body>
</html>