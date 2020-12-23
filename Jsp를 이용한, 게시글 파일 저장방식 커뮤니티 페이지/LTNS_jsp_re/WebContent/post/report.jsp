<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%

	String report_man = (String) session.getAttribute("nickname");
	int post_id = Integer.parseInt(request.getParameter("post_id"));

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report page</title>
</head>

<!-- css link  -->
 <link rel="stylesheet" type="text/css" href="CSS/report.css">
 <link rel="stylesheet" type="text/css" href="../footer/css/footer.css">
 <link rel="stylesheet" type="text/css" href="../header/css/header.css">
 <link rel="stylesheet" type="text/css" href="../loading/css/emailLoading.css">
 

<!-- javascript link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="JS/view.js"></script>

<!-- fontasome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!-- bootstrep -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!--  capcha -->
 <script src="https://www.google.com/recaptcha/api.js" async defer></script>


<script type="text/javascript">

function chk_Submit() {
	var frm = document.forms['report_form'];
	var subject =  frm['subjects'].value.trim();
	var artcle = frm['artcle'].value.trim();
	
	if(subject == ""){
		alert("제목 입력");
		frm["subjects"].focus();
		return false;
	}
	
	if(artcle == ""){
		alert("내용 입력")
		frm["artcle"].focus();
		return false;
	}
	
    var v = grecaptcha.getResponse();
    if(v.length == 0)
    {
    	alert("Captcha 를 체크해주세요");
        return false;
    }
    if(v.length != 0)
    {
    	
    	$("#cotents").hide();
   		$("#staticBackdrop").modal("show");
        return true;
        
       
    }
	
}


</script>
<body class="container">
<div>
<h6 class="mt-4"> 특정 페이지 신고하기</h6>
<hr>
</div>
<div id="cotents" class="border">
		<div class="jumbotron ">
			<h1>신고하기 </h1>	
			<span> LTNS(주) 서비스 이용약관에 기존하여  서비스 운영원칙에 따라 <br> 불량 이용자 처리 규정을 정하고 있습니다. <br> 허위 신고 또는 과한 신고시 제제 대상이 될 수 있습니다.</span> 
				
		</div> <!-- end jumbo -->
	
		<div class="col-12  text-secondary mt-2">
		<form name="report_form"  method="POST" action="report_ok.do?post_id=<%=post_id %>" onsubmit="return chk_Submit()" >
			
			<div class=" row mt-2 ">				
				<h4 class="col-sm-4 text-center mt-2"> 게시판 번호  </h4>
			 	<h4 class="col-sm-8 text-center mt-2"><%=post_id %> </h4>
			</div>
			
			<div class="row mt-2">
				 <h4 class ="col-sm-4 text-center mt-2"> 신고 사항 </h4>
				<div class ="col-sm-2">
				</div>
				 <select class="col-sm-4 selectpicker  d-flex justify-content-right" name="select" >
					<option selected="selected">부적절한 컨탠츠</option>
					<option>명예훼손</option>
					<option>욕설과 비방</option>
				
				</select>
			
			</div>
			
			<div class="row mt-2">
				 <h4 class="col-sm-4 text-center mt-2"> 신고자 ID </h4>
				<h4 class="col-sm-8 text-center mt-2"><%=report_man %></h4>
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
			<button class="btn-lg bg-success text-white  " type="button" onclick="history.back()">취소</button>
			<input class="btn-lg bg-success text-white " type="submit" value="신고">
			</div>
			
			
			
			
			</table>
			<input type="hidden" name="<%=post_id%>">
			<input type="hidden" name="<%=report_man%>">
			<input type="hidden" name="select">
	
			
			
			</form>
		</div> <!-- end report -->
	
</div>

<jsp:include page="../loading/component/emailLoading.jsp"/>

</body>
</html>