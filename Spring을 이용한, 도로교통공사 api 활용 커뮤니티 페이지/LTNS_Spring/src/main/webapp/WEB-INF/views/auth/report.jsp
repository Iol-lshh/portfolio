<%@page import="com.ltns.rest_area.domain.user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자에게 문의하기</title>
</head>

<!-- css link  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/user/report.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/user/emailLoading.css">
<!-- javascript link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- fontasome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!-- bootstrep -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!--  capcha -->
 <script src="https://www.google.com/recaptcha/api.js" async defer></script>


<body class="container">
<div>
<h6 class="mt-4"> 관리자에게 문의하기</h6>
<hr>
</div>
<div id="cotents" class="border">
		<div class="jumbotron ">
			<h1>문의하기 </h1>	
			<span> LTNS(주) 서비스 이용약관에 기존하여  서비스 운영원칙에 따라 <br> 이용자 등급에 대한 접근권한 규정을 정하고 있습니다. <br> 허위 문의 또는 과한 문의시 제제 대상이 될 수 있습니다.</span> 
				
		</div> <!-- end jumbo -->
	
		<div class="col-12  text-secondary mt-2">
		<form id="reportForm" >
			
	
			
			<div class="row mt-2">
				 <h4 class ="col-sm-4 text-center mt-2"> 문의 사항 </h4>
				<div class ="col-sm-2">
				</div>
				 <select class="col-sm-4 selectpicker  d-flex justify-content-right" name="select" >
					<option selected="selected">접근 권한</option>

				
				</select>
			
			</div>
			
			<div class="row mt-2">
				 <h4 class="col-sm-4 text-center mt-2"> 문의자 Email </h4>
			
				<h4 class="col-sm-8 text-center mt-2"><input type="email" name="email"></h4>
			
			</div>
			
				<div class="row mt-2">
				 <h4 class="col-sm-4 text-center mt-2"> 문의자 아이디 </h4>
			
				<h4 class="col-sm-8 text-center mt-2"><input type="email" name="um_username"></h4>
			
			</div>
			
			<div class=" row mt-2">
				 <h4 class="col-sm-4 text-center mt-2"> 제목 </h4>
			
				<h4 class="col-sm-8 text-center mt-2"> <input type="text" name="subjects"></h4>
			</div>
			
			
			<div class=" row mt-2">
				 <h4 class="col-sm-4 text-center mt-2"> 내용 </h4>
				<h4 class="col-sm-8 text-center mt-2"> <textarea class="text_aer" rows="3" cols="20" name="artcle"></textarea> </h4>
			</div>
		<div class="row mt-2 d-flex justify-content-center">
			<div class="g-recaptcha " data-sitekey="6LffD-IZAAAAANuqCjSeHuEyjZ9AXUQn9jFkn5NZ"></div>
		</div>
			
			
			<div class=" row mt-2 d-flex justify-content-center">
			<button class="btn-lg bg-success text-white  " type="button" onclick="location.href='<%=request.getContextPath()%><%=request.getContextPath()%>/front'">취소</button>
			<input class="btn-lg bg-success text-white " id="emailSend" type="button" value="문의">
			</div>
			
			
			
			
			<%--
			</table>
			<input type="hidden" name="<%=post_id%>">
			<input type="hidden" name="<%=report_man%>">
			<input type="hidden" name="select">
			 --%>
	
			<input type="hidden" id="message" name="message" /> <input
				type="hidden" id="kind" name="kind" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			</form>
		</div> <!-- end report -->
	
</div>
<jsp:include page="/WEB-INF/views/auth/emailLoading.jsp" />

<script src="<%=request.getContextPath() %>/resources/js/user/report.js"></script>
</body>
</html>