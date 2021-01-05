<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  csrf token 읽기 -->
<meta name="csrf-token" content="${_csrf.token}">
<title> 회원 정보</title>
</head>



<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!--  chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">

<link href='${pageContext.request.contextPath }/admin/CSS/memberInfo.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/memberInfo.js'></script>

<body id="mybodys" class="bg-light" >

	<div class="loading mt-5">
			<img src="${pageContext.request.contextPath }/admin/img/loading.gif">
		</div>
	
	<div id="bodys">
	 
    <!-- nav -->
	<jsp:include page="${request.getRequestURI}/admin/nav/navs" flush="true" />
	 
	 

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-dark sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						
						<li class="nav-item "><a class="nav-link  text-white" href="${pageContext.request.contextPath}/admin/dashboard"> <span
								data-feather="shopping-cart"></span> <i class="fas fa-chart-line"></i>&nbsp;&nbsp; 메인
						</a></li>
						
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/schedule"> <span
								data-feather="shopping-cart"></span>  <i class="far fa-calendar-check"></i>&nbsp;&nbsp; 일정
						</a></li>
						
						<li class="nav-item "><a class="nav-link  bg-info text-white " href="${pageContext.request.contextPath}/admin/memberInfo"> <span
								data-feather="shopping-cart"></span> <i class="far fa-user-circle"></i> &nbsp;&nbsp;회원정보
						</a></li>
						<li class="nav-item "><a class="nav-link  text-white " href="${pageContext.request.contextPath}/admin/postInfo"> <span
								data-feather="users"></span> <i class="fas fa-edit"></i>&nbsp;&nbsp;게시글 정보
						</a></li>
					
						<li class="nav-item"><a class="nav-link  text-white" href="${pageContext.request.contextPath}/admin/notice"> <span
								data-feather="bar-chart-2"></span> <i class="far fa-clipboard"></i>&nbsp;&nbsp; 공지사항
						</a></li>
						
						
					<li class="nav-item"><a class="nav-link  text-white" href="${pageContext.request.contextPath}/admin/areaInfo"> <span
								data-feather="bar-chart-2"></span> <i class="fas fa-utensils"></i>&nbsp;&nbsp; 휴게소 정보
						</a></li>
						
					</ul>

				</div>
			</nav>
		</div><!-- sidebar 라인 -->

		<!-- section 1  -->
		<main role="main" id="main_article" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
		
		

	<div id="ex1" class="modal">
			<form id="mailForm">
					<div class="form-group mt-2">
						<label for="exampleInputEmail1">Email</label> <input
							type="text" class="form-control" name="username" id="userEmail"
							placeholder="Enter email"> <small
							id="emailHelp" class="form-text text-muted">
							메일을 보낼때에는 신중하게 보냅시다.</small>
					</div>
					
					<div class="form-group">
					    <label for="exampleFormControlSelect1">Title</label>
					    <select class="form-control" name="title" id="exampleFormControlSelect1">
					      <option selected value="intro"> 안녕하세요 LTNS 팀입니다. </option>
					      <option value="report"> 게시물의 신고가 많이 접수되어 있습니다.</option>
					    </select>
					    <small
							id="emailHelp" class="form-text text-muted">
							기본적인 메일을 보낼때는 인사말만 간단히 보내는게 좋아요
						</small>
				  </div>
					
					
					<div class="form-group">
						<label for="mailText">Text</label>
						<textarea class="form-control" id="emailText" name="textMail"
							rows="5"></textarea>
					</div>


					<div class="text-right">
						<button type="button" id="info_btn" class="btn btn-outline-primary " onclick="message_hello();">Hello info </button>
						<button type="button" id="info_btn" class="btn btn-outline-primary " onclick="message_report();">Report Info</button>
							
						
						<button onclick="mailSend();" type="button" class="btn btn-outline-success">Submit</button>
						 <a type="button" class="btn btn-outline-danger" href="#" rel="modal:close" id="close">Close</a>
					</div>
					
					 <input type="hidden" name="csrfToken" value="${_csrf.token}" />
	
				</form>



	</div> <!--  end modal -->





			<div id="list">
				<div class="d01 text-right font-lg">
					<div id="pageinfo"></div>
					<div id="pageRows"></div>
				</div>
				<div class="clear"></div>

				<form id="frmList" name="frmList">
					<table class="table">
						<thead class="thead-dark">
						<tr>
						
							<th>UID</th>
							<th>아이디</th>
							<th>닉네임</th>
							<th>가입일</th>
						</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</form> 
			</div> <!-- end list table -->
	<%--페이징 --%>
		<br>
		
				<div class="d-flex justify-content-center">
					<form name="sechForm" id="sechForm">
				<select id="sele_option" class="mdb-select">
						<option value="id" selected> 아이디 </option>
						<option value="nickName"> 닉네임 </option>
					</select>
					<input id="text_info"  type="text" name="text_Info" class="input_seach">
					<button type="button" class="btn_infos" onclick="addSerch();"> 검색 </button>
				 	
			
				  </form>
				</div>
		<br>
		
				<div class="d-flex justify-content-end">
					<ul class="pagination " id="pagination">
					</ul>
				</div>
				
		
			<div class="clear"></div>

	<hr>

		<div class= "d-inline-block justify-content-start  " >
		<canvas id="userCharts"style="height:300px; width:70vh" ></canvas>
		</div>
		
		<div class= "d-inline-block justify-content-start ml-4" >
		<canvas id="userCharts2" style="height:300px; width:70vh"></canvas>
		</div>

  	<hr>
	  	<div class="clear"></div>
	  
	  

		</main> <!-- article -->



	</div> <!-- end con -->
</div> <!--  hide body -->
</body>
</html>