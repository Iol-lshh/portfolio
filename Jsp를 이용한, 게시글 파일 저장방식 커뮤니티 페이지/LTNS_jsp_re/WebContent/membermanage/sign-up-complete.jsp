<%@page import="main.java.com.view.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		int ok = (Integer)request.getAttribute("joinOK");
		int ok2 = (Integer)request.getAttribute("insertOK");
		MemberDTO []  ok3 = (MemberDTO[])request.getAttribute("signOK");
	
	%>

    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../lib/bootstrap-4.5.3-dist/css/bootstrap.css">






<title>회원가입 완료</title>
</head>

<body style="height: 100vh; display: flex; flex-direction: column; justify-content: center; align-items: center;">





	<div class="wrap text-center" id="wrap">
		<div class="container text-center">
	
			
			<table class="table table-bordered table-hover" style="text-align: center" border="1px solid #dddddd">
				<thead>
					<tr >
						<td colspan="2"> 회원가입 완료 </td>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아아디</h5></td>
						<td> <%=ok3[0].getId() %> </td>
					</tr>
					
					<%-- <tr>
						<td style="width: 110px;">비밀번호</td>
						<td> <%=ok3[0].getPassword() %> </td>
					</tr> --%>
					
					<tr>
						<td style="width: 110px;">닉네임</td>
						<td> <%=ok3[0].getNickname() %> </td>
					</tr>
					
					<tr>
						<td style="width: 110px;">이메일</td>
						<td> <%=ok3[0].getEmail() %> </td>
					</tr>
				
				

				
				</tbody>
			
			</table>
			<div class="text-center">
					 <a class="btn btn-primary" href="loginOk.do"> 확인  </a>
					</div>
		
		</div>
		
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../lib/bootstrap-4.5.3-dist/js/bootstrap.js"></script>
</body>
</html>