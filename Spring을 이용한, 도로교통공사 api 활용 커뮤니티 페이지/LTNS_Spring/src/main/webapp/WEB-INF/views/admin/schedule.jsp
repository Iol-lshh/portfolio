<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
    
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ltns.rest_area.domain.admin.*" %>

<%
	List<ScheduleDTO> list = (ArrayList<ScheduleDTO>) request.getAttribute("showSchedule");

%>
    
<html>
<head>
<meta charset='utf-8' />
<title>일정</title>

<!--  fontawesome -->
<script src="https://kit.fontawesome.com/5ccafa9b7a.js" crossorigin="anonymous"></script> 

<!-- google font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>


<!--  date picker  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/album/">

<!-- 풀캘린더 패키지  -->
<link href='${pageContext.request.contextPath }/pakege/core/main.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath }/pakege/daygrid/main.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath }/pakege/timegrid/main.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath }/pakege/list/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/pakege/core/main.js'></script>
<script src='${pageContext.request.contextPath }/pakege/interaction/main.js'></script>
<script src='${pageContext.request.contextPath }/pakege/daygrid/main.js'></script>
<script src='${pageContext.request.contextPath }/pakege/timegrid/main.js'></script>
<script src='${pageContext.request.contextPath }/pakege/list/main.js'></script>

<!-- css & js -->
<link href='${pageContext.request.contextPath }/admin/CSS/schedule.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/admin/JS/schedule.js'></script>
   
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid',  'list' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: ''
      },
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      locale: "ko",
      events: [
    	  
<%
	  	for(int i =0; i< list.size(); i++ ){
    	  		ScheduleDTO dto = (ScheduleDTO)list.get(i);
    	  	
    	  
    	  
%>
    	      {
    		  title : '<%=dto.getSubject() %>',
        	  start :  '<%=dto.getStartDate() %>',
        	  end :   '<%=dto.getEndDate() %>'
    		  },
    	  
    	  
<%
    }
%>
    	  
      
      ]
    });

    calendar.render();
 });

  
  
  
  
  
  
</script>
<style>
.fc-center, table,
.fc-content{
	font-family: 'Do Hyeon', sans-serif;
}

</style>
</head>
<body class="bg-light"> 
	
    <!-- nav header 라인 -->
	<jsp:include page="${request.getRequestURI}/admin/nav/navs" flush="true" />
	
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-dark sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						
						<li class="nav-item "><a class="nav-link  text-white" href="${pageContext.request.contextPath}/admin/dashboard"> <span
								data-feather="shopping-cart"></span> <i class="fas fa-chart-line"></i>&nbsp;&nbsp; 메인
						</a></li>
						
						<li class="nav-item "><a class="nav-link bg-info text-white " href="${pageContext.request.contextPath}/admin/schedule"> <span
								data-feather="shopping-cart"></span>  <i class="far fa-calendar-check"></i>&nbsp;&nbsp; 일정
						</a></li>
						
						<li class="nav-item "><a class="nav-link   text-white " href="${pageContext.request.contextPath}/admin/memberInfo"> <span
								data-feather="shopping-cart"></span> <i class="far fa-user-circle"></i> &nbsp;&nbsp;회원정보
						</a></li>
						<li class="nav-item "><a class="nav-link  text-white " href="${pageContext.request.contextPath}/admin/postInfo"> <span
								data-feather="users"></span> <i class="fas fa-edit"></i>&nbsp;&nbsp;게시글 정보
						</a></li>
					
						<li class="nav-item"><a class="nav-link  text-white" href="${pageContext.request.contextPath}/admin/notice"> <span
								data-feather="bar-chart-2"></span> <i class="far fa-clipboard"></i>&nbsp;&nbsp; 공지사항
						</a></li>
						
						
						<li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/areaInfo"> <span
								data-feather="bar-chart-2"></span> <i class="fas fa-utensils"></i>&nbsp;&nbsp; 휴게소 정보
						</a></li>
					</ul>

				</div>
			</nav>
		</div><!-- sidebar 라인 -->






	<!-- 캘린더  -->
  <div class="mt-4" id='calendar' style="position : relative;"> 
	  <div>
	  	<button class="add-button" type="button" onclick="click_add();">  추가 </button>
	  	<button class="delete-button" type="button" onclick="click_delete();"> 삭제 </button>
	 	<button class="update-button" type="button" onclick="click_update();"> 수정</button>
	  </div>
  </div>
  
  
  
</div>
</body>
</html>
