<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="csrf-token" content="${_csrf.token}">
<title>Insert title here</title>
</head>
<script src='${pageContext.request.contextPath }/admin/JS/nav.js'></script>
<link href='${pageContext.request.contextPath }/admin/CSS/nav.css' rel='stylesheet' />
<body>
<!-- dashboard nav -->

   <nav class="navbar navbar-dark  sticky-top bg-white flex-md-nowrap p-0 mt-0">
      <a class="navbar-brand bg-dark col-sm-3 col-md-2 mr-0" href="${pageContext.request.contextPath}/admin/dashboard">LTNS DashBoard</a> <!--  barnd 설정  -->
      
      <ul class="navbar-nav  d-flex flex-row-reverse  ">
      
        <li class="nav-item  text-center mr-2">
			<button type="button" class="mt-1 btn" onclick="location.href='${pageContext.request.contextPath}/rest_area/front'">		
			<i class="fas fa-door-open"></i>
			</button>
		</li>
        
        
          <li class="nav-item  mr-5">
          		<span class="font-weight-bold mt-2"  style="font-size: 14px;"> Hello Master </span> <br>
         		<span class="welcomes" style="font-size: 12px;">${id}</span>
         		
          </li>
          <li class="nav-item mr-3">
	          <div class="under">
	          <img src="${pageContext.request.contextPath }/admin/img/1.PNG">
	          </div>
  	      </li>
  	      
			<li class="nav-item   mr-2 text-center">
			<button type="button" class="mt-1 btn" onclick="popover();" >		
			<i class="fas fa-bell"></i>	
			</button>
			</li>
			
		</ul>
     	
			<div class="popovers border border-dark text-center">
				<div class="popupTitle"> 알림 </div>
				<div class="popupBody">금일 공지사항은 없습니다.</div>
			</div>
	 
    </nav> <!-- nav header 라인 -->
</body>
</html>