<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view</title>
</head>
<body>
<script src='${pageContext.request.contextPath }/admin/JS/notice.js'></script>

<div class="viewList">
				<h1 class="viewTitle"></h1>
				<form>
				<input type="hidden" name="${id}" id="hidens" value="${id}">
				</form>
				<hr>	
				
				<div>	
				<span class="viewNo"></span>  <span class="text-left" id="viewDate"></span> 
				<span id="viewId" class="float-right ml-4 mr-2"></span> 
				</div>
				<hr>
				
				<div class="viewContent">				</div>
				<hr>
				
				<div class="text-right">
				<button type="button" class="btn btn-outline-success"onclick="location.href='${pageContext.request.contextPath}/admin/notice'"> 취소 </button>
				<button type="button" id="updatesBtn" class="d-none" > 수정 </button>
				<button type="button" id="deleteBtn" class="d-none" > 삭제 </button>  
				</div>
			</div> <!-- viewList -->
			
</body>
</html>