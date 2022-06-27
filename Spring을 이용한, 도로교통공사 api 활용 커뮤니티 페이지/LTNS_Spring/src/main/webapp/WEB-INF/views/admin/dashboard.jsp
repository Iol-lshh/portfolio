<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--  csrf token 읽기 -->
<meta charset="UTF-8" name="csrf-token" content="${_csrf.token}">

<title>관리자 페이지</title>
</head>


<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>




<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!--  chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href='${pageContext.request.contextPath }/admin/CSS/dashBoard.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/dashBoard.js'></script>

<body class="bg-light">

	<jsp:include page="${request.getRequestURI}/admin/nav/navs" flush="true" />
	
	
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-dark sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						
						<li class="nav-item "><a class="nav-link bg-info text-white" href="${pageContext.request.contextPath}/admin/dashboard"> <span
								data-feather="shopping-cart"></span> <i class="fas fa-chart-line"></i>&nbsp;&nbsp; 메인
						</a></li>
						
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/schedule"> <span
								data-feather="shopping-cart"></span>  <i class="far fa-calendar-check"></i>&nbsp;&nbsp; 일정
						</a></li>
						
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/memberInfo"> <span
								data-feather="shopping-cart"></span> <i class="far fa-user-circle"></i> &nbsp;&nbsp;회원정보
						</a></li>
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/postInfo"> <span
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

	
		
		
 <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 ">

	<!-- 일일 정산   -->
	<div id="rowsdata" class="row">
	
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-info">
	        <h3 class="text-info"> 오늘 가입한 유저 </h3>
	        <p id="numbers_today" class="card-title"> </p>
	        <span id="numbers_acount" class="card-body"></span>
	     	</div>
	    </div>
	  </div>
	  
	  
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-success">
	        <h3 class="text-success">오늘 작성된 게시글 </h3>
	        <p id="today_post" class="card-title"> </p>
	        <span id="total_post" class="card-body"></span>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-danger">
	        <h3 class="text-danger">오늘 신고된 게시글 </h3>
	          <p id="icons_report" class="card-title"></p>
	         <span id="today_reports_post" class="card-body"> </span>
	      </div>
	    </div>
	  </div>
	  
	    <div class="col-sm-3 ">
	    <div class="card text-center">
	      <div class="card-body border border-warning">
	        <h3 class="text-warning">전체 추천 개수</h3>
	        <p id="total_recomend" class="card-title"> </p>
	        <br>
	      </div>
	    </div>
	  </div>
	</div> <!-- end card row section 2 -->
	<div class="clear"></div>
	
	<hr>
	
	<!-- section2 -->
	<div id="rowsdata" class="row">
	  <div class="col-sm-3">
	    <div class="card text-center ">
	      <div class="card-body border border-info">
	        <h3 class="text-info">   BEST 휴게소 </h3>
	        <p id="Best1" class="card-title2"> </p>
	        <span id="Best2" class="card-body text-center"> </span> <br>
	     	<span id="Best3" class="card-body text-center">  </span> <br>
	        <span id="recomend_area" class="card-body text-center">  </span>
	     
	     	</div>
	    </div>
	  </div>
	  
	  
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-success">
	        <h3 class="text-success">  BEST 메뉴 </h3>
	     <p id="Food_name" class="card-title2"> </p>
	        <span id="Food_price" class="card-body text-center"> </span> <br>
	     	<span id="Food_raea" class="card-body text-center">  </span> <br>
	        <span id="recomend_food" class="card-body text-center">  </span>
	    
	      </div>
	    </div>
	  </div>
	  
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-danger">
	        <h3 class="text-danger">  BEST 주유소</h3>
	     	 <p id="gas_name" class="card-title2"> </p>
	        <span id="gas_price" class="card-body text-center"> </span> <br>
	        <span id="gas_price_lpg" class="card-body text-center"> </span> <br> 
	         <span id="recomend_gas" class="card-body text-center">  </span>
	     </div>
	    </div>
	  </div>
	 
	  <div class="col-sm-3">
	    <div class="card text-center">
	      <div class="card-body border border-warning">
	        <h3 class="text-warning" >가장 저렴한 주유소</h3>
	       <p id="chip_gas" class="card-title">  </p>
	        <span id="chip_gas_D" class="card-body "> </span> <br>
	        <span id="chip_gas_O" class="card-body "> </span> <br>
	        <span id="chip_gas_L" class="card-body "> </span>
	      </div>
	    </div>
	  </div>
	</div> <!-- end card row section 1 -->
	
	<hr>
		
		
		
		<div class= "d-inline-block justify-content-start ml-4" >
	
		<canvas id="myChart" style="position: relative; height:30vh; width:75vw" ></canvas>
		</div>
		
		
		<div class= "d-inline-block justify-content-start ml-4" >
		<canvas id="memberListAdd" style="height:30vh; width:75vw" ></canvas>
		</div>
		

	 
			
		
			
			
			
			
		</main> <!-- article -->
		
	</div> <!-- end con -->

	
</body>
</html>