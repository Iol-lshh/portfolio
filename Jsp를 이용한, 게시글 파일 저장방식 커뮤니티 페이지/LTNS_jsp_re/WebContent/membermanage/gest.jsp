<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	session.setAttribute("writer", 9999999);
	session.setAttribute("login",0);
	session.setAttribute("grade", "gest");	
	session.setAttribute("nickname", "gest");	
%>


<script>
	alert("게스트 모드로 입장하셨습니다.");
	location.href = "<%=request.getContextPath() %>/mainpage/mainpage.do"
</script>