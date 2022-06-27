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



<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />


<!--  지도 api  -->

<link href='${pageContext.request.contextPath }/admin/CSS/areaInfo.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/areaInfo.js'></script>


<body class="bg-light">

	<jsp:include page="${request.getRequestURI}/admin/nav/navs" flush="true" />
	
	
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-dark sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						
						<li class="nav-item "><a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/dashboard"> <span
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
						<li class="nav-item"><a class="nav-link bg-info text-white" href="${pageContext.request.contextPath}/admin/areaInfo"> <span
								data-feather="bar-chart-2"></span> <i class="fas fa-utensils"></i>&nbsp;&nbsp; 휴게소 정보
						</a></li>
					</ul>

				</div>
			</nav>
		</div><!-- sidebar 라인 -->


		
	
		
		
	    <main role="main" id="main_article" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 ">
		
	
	
	
	    <div id="list" >
	    	<div class="btn_team text-right mt-2 mb-2">
	    	<button type="button" id="btns" class="float-left" onclick="dataUpdate();"> 업데이트</button>
	    	<button type="button" id="btns" onclick="pageLoad(1);"> 휴게소</button>
			<button type="button" id="btns" onclick="updateG(1);"> 주유소 </button>
			<button type="button" id="btns" onclick="updateM(1);"> 음식 </button>
			</div>
			
				<form id="frmList" name="frmList">
					<table class="table text-center">
						<thead  class="bg-info text-white" >
							<tr id="heads">
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</form>
				
				<br>
				<div class="d-flex justify-content-center">
					<form name="sechForm" id="sechForm">
				<select id="sele_option" class="mdb-select mr-2">
						<option value="values_1" selected> 휴게소 이름  </option>
						<option value="values_2"> 주유소 이름</option>
						<option value="values_3"> 메뉴 이름 </option>
					</select>
					<input  type="text"  name="text_Info" class="input_seach">
					<button type="button"  class="btn_infos ml-2" onclick="info_Serch();"> 검색 </button>
				  </form>
				</div>
				
				
				<br>
				
				
				<div class="d-flex justify-content-center">
					<ul class="pagination " id="pagination">
					</ul>
				</div>
				
			</div> <!-- end list table -->
			
		
			
			
			<input type="hidden" id="parent">
			
			
		</main> <!-- article -->
	</div> <!-- end con -->




	
</body>
</html>