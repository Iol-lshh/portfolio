<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  csrf token 읽기 -->
<meta name="csrf-token" content="${_csrf.token}">
<title> 게시글 정보</title>
</head>

<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!--  chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">

<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<link href='${pageContext.request.contextPath }/admin/CSS/postInfo.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/postInfo.js'></script>

<body class="bg-light">

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
						
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/memberInfo"> <span
								data-feather="shopping-cart"></span> <i class="far fa-user-circle"></i> &nbsp;&nbsp;회원정보
						</a></li>
						<li class="nav-item "><a class="nav-link bg-info text-white " href="${pageContext.request.contextPath}/admin/postInfo"> <span
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

			   <div id="list" >
				<div class="d01 text-right font-lg">
					<div id="pageinfo"></div>
					<div id="pageRows"></div>
				</div>
				<div class="clear"></div>

				<form id="frmList" name="frmList">
					<table class="table">
						<thead class="thead-dark">
						<tr>
							<th>Check</th>
							<th>POST_ID</th>
							<th>POST_TITLE</th>
							<th>RA_CODE</th>
							<th>UM_USERNAME</th>
							<th>POST_REPORTED</th>
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
						<option value="title" selected> 타이틀 </option>
						<option value="nickName"> 닉네임 </option>
					</select>
					<input id="text_info"  class="input_seach" type="text" name="text_Info">
					<button type="button" class="btn_infos" onclick="addSerch();"> 검색 </button>
				 	<button type="button" class="btn_infos" onclick="click_delete();"> 삭제 </button>
				 
				  </form>
				</div>
		
				<div class="d-flex justify-content-end">
					<ul class="pagination " id="pagination">
					</ul>
				</div>
				
		
			<div class="clear"></div>
	<hr>
	
		<div class= "d-inline-block justify-content-start mr-4 ml-2" >
		<canvas id="myChart" style="height:300px; width:70vh" ></canvas>
		</div>
		
		<div class="d-inline-block justify-content-start mr-2 ml-2">
		<canvas id="postChart2" style="height:300px; width:70vh" ></canvas>
		</div>
		
			<div class="clear"></div>
	
	<hr>
		<div class="d-inline-block justify-content-center">
		<canvas id="linechart" style="height:30vh; width:75vw" ></canvas>
		</div>
	
	
		
		</main> <!-- article -->



	</div> <!-- end con -->
</body>
</html>