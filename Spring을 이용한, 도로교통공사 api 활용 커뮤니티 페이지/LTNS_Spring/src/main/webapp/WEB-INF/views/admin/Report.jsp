<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  csrf token 읽기 -->
<meta charset="UTF-8" name="csrf-token" content="${_csrf.token}">

<title>Insert title here</title>
</head>


<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">

<script>
$(document).ready(function(){
	
	var post_id = opener.$("#parent2").val();
	
	
	
	$("#post_No").text(post_id)
	
	
	$("#reportGo").click(function(){
		$.ajax({
			url : "reportManiger/"+ post_id,
			Type : "GET",
			headers: {
					'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		    },
			success : function(data,status){
				if(data.status=="OK"){
					alert("신고가 접수되었습니다.");
					self.close();
				}
			}
		})
	})
	
	
	
})



function close_btn(){
	
	
	self.close();
}


</script>



<body>
<div class="container  mt-4 border">
				<h1 class="text-center mt-4"> 게시글 신고하기 </h1>
				<hr>
			<form>
				  <div>
				  		<h6> Post_Number :<span id="post_No"> </span> </h6>
				  </div>

				  <hr>
				  <div class="form-group">
					    <label for="exampleFormControlSelect1">신고 종류</label>
					    <select class="form-control" name="title" id="exampleFormControlSelect1">
					      <option selected value="reportType_1"> 부적절한 컨탠츠 </option>
					      <option value="reportType_2"> 음란성 게시물 </option>
					      <option value="reportType_3"> 명예훼손 및 비방성 게시글 </option>
					    </select>
					    <small
							id="emailHelp" class="form-text text-muted">
							신고를 할때에는 신중하게 하는 것이 좋습니다.
						</small>
				  </div>
					<div class="text-center mb-2">
						<button type="button" class="btn btn-outline-danger" onclick="close_btn();"> Close </button>
						<button id="reportGo" type="button" class="btn btn-outline-success">Submit</button>
					</div>
				</form>
</div>
</body>
</html>