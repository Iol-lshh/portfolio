<%@page import="main.java.com.view.MemberDAO"%>
<%@page import="main.java.com.view.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%

	String nickname = (String) session.getAttribute("nickname");
	String id = (String) session.getAttribute("id");
	String email= (String) session.getAttribute("email");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage_Info</title>
<style type="text/css">
	#myinfo{
		width:100%;
	}
</style>
</head>
<!-- css link  -->
 <link rel="stylesheet" type="text/css" href="CSS/myPage_Info.css">

<!-- bootstrep -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- javascript link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="JS/myPage.js"></script
>
<body class="container">

<div id="myinfo" class="card border mt-4">

	
	<table>
			<tbody class="table text-center border black">
			<tr>
			
			<th style="width:20%; height:100px" class="th_cell border ">
			<div class="text-center">
			<h6 class="h6_info ">아이디</h6>
			</div>
			</th>
			
			<td>
			<div class="td_cell text-left ml-4">
			<span class="span_id"><%=id %></span> 
			<h6 class="info_text"> LTNS의 ID 입니다. ID는 변경이 불가능 합니다.</h6>
			</div>
			</td>
			</tr>
			
			<tr>
			
			<th style="width:20%; height:100px" class="th_cell border ">
			<div class="text-center">
			<h6 class="h6_info ">비밀번호</h6>
			</div>
			</th>
			
			<td>
			<div class="td_cell text-left ml-4">
			<form id="myforms" name="forms"  method="POST" action="myPage_ok.do">
			</form>
			<h6 class="info_text">비밀번호는 주기적으로 변경하는 것을 권장합니다.</h6>
			<button id="rev_btn" type="button" class="rev_btn"> 변경 </button>
			
			</div>
			</td>
			</tr>
			
			<tr>
			
			<th style="width:20%; height:100px" class="th_cell border ">
			<div class="text-center">
			<h6 class="h6_info">닉네임</h6>
			</div>
			</th>
			<!--myPage_ok.do -->
			<td>
			<div id ="td_cells" class="td_cell text-left ml-4">
			<form id="myforms2" name="forms"  method="POST" action="myPage_ok.do">
			<span class="span_id" id="spans"><%=nickname %></span> 
			</form>
			<h6 class="info_text">닉네임은 변경이 가능합니다. 자신만의 특별한 닉네임으로 변경해보세요</h6>
			<button id="rev_btn2" type="button" class="rev_btn"> 수정 </button>
			
			</div>
			</td>
			</tr>
			
			<tr>
			<th style="width:20%; height:100px" class="th_cell border ">
			<div class="text-center">
			<h6 class="h6_info ">이메일</h6>
			</div>
			</th>
			
			<td>
			<div class="td_cell text-left ml-4">
			<span class="span_id"><%=email %></span> 
			<h6 class="info_text">가입된 이메일은 아이디 찾기 비밀번호 찾기 또는 다양한 이벤트 정보를 받을 수 있습니다.</h6>
			</div>
			</td>
			</tr>
			
			</tbody>
	</table>
	<button id="withdrawal">회원 탈퇴</button>
</div> <!-- end card  -->





</body>
</html>