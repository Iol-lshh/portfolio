<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../lib/bootstrap-4.5.3-dist/css/bootstrap.css">

<title>아이디찾기</title>
</head>
<body style="height: 100vh; display: flex; flex-direction: column; justify-content: center; align-items: center;">
	<div id="wrap">
		<div id="header" class="text-center">
		<h1>
			<a id="login_logo" href="loginmain.jsp">
				<img alt="Login_logo" src="../images/favicon.ico">
			</a>
		</h1>			
		</div>
		
		<div class="container">
			<form action="findOK.do" method="post">
				<table class="table table-bordered table-hover" style="text-align: center" border="1px solid #dddddd">
					<thead>
							<tr>
							<th colspan="3"><h4>아이디 비번  찾기</h4> </th>
							</tr>
					</thead>
					
					<tbody>
						<tr>
							<td style="width: 110px;"><h5>이메일</h5></td>
							<td><input class="form-control" type="email" id="email" name="email" maxlength="20"> </td>
							<td style="width: 110px;"><button class="btn btn-primary" id="emailCK" onclick="emailSend();" type="button">요청</button> </td>
						</tr>
						
						<tr>
							<td style="width: 110px;"><h5>이메일 인증</h5></td>
							<td><input class="form-control" type="text" id="emailAC" name="emailAC" maxlength="20"> </td>
							<td style="width: 110px;"><button class="btn btn-primary" disabled="disabled" id="emailACbtn" onclick="emailChk();" type="button">확인</button> </td>
						</tr>
					</tbody>
				</table>
					<div class="text-center">
					<button type="submit"  id="submit" disabled="disabled" class="btn btn-primary text-center" >확인</button>
					</div>
			</form>
		
		</div>
		
	</div>
	
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../lib/bootstrap-4.5.3-dist/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="JS/findJS.js"></script>

</body>
</html>