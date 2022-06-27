<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  csrf token 읽기 -->
<meta name="csrf-token" content="${_csrf.token}">
<title>관리자페이지</title>
</head>




<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">




<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />


<!--  bootstrap -->
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<link href='${pageContext.request.contextPath }/admin/CSS/notice.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/notice.js'></script>




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
						<li class="nav-item "><a class="nav-link text-white " href="${pageContext.request.contextPath}/admin/postInfo"> <span
								data-feather="users"></span> <i class="fas fa-edit"></i>&nbsp;&nbsp;게시글 정보
						</a></li>
						<li class="nav-item"><a class="nav-link  bg-info text-white" href="${pageContext.request.contextPath}/admin/notice"> <span
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
	
	
	
		<!--  모달 -->
		<jsp:include page="${request.getRequestURI}/admin/notice/noticeModal" flush="true"/>
			
		 
			
	
	
	<!-- 게시글 리스트 부분 -->
		 <div id="list" >
			<div class="text-right mb-2">
					<button type="button" onclick="noticeModal();" class="mr-2 btn btn-outline-danger "> 공지 </button>
					<button type="button" onclick='writeNotice();' class="btn btn-outline-success">글작성</button>
			</div>
			
				<div class="clear"></div>
				<form id="frmList" name="frmList">
					<table class="table table-hover text-center">
						<thead class="thead-dark">
							<th>번호</th>
							<th colspan="3">제목</th>
							<th>작성일</th>
							<th>작성자</th>
						</thead>
						<tbody>

						</tbody>
					</table>
				</form> 
				<div class="d-flex justify-content-center">
					<ul class="pagination " id="pagination">
					</ul>
				</div>
				
				
				
				
				
			</div> <!-- end list table -->
			
				
			<div class="clear"></div>
			
			
			
			<!-- view -->
			<jsp:include page="${request.getRequestURI}/admin/notice/view" flush="true"/>
			
			<!-- 글작성-->
			<jsp:include page="${request.getRequestURI}/admin/notice/writer" flush="true" />

	 		<jsp:include page="${request.getRequestURI}/admin/notice/update" flush="true" />
			
			<a class="nav-link text-dark d-none"  href="#ex1" rel="modal:open"><i class="fas fa-bell"></i></a>
		
		</main> <!-- article -->
	</div> <!-- end con -->
</body>
</html>