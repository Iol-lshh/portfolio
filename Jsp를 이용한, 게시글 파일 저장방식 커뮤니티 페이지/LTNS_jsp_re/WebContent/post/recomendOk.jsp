<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
	int cnt = (Integer)request.getAttribute("rec");
	int likes = (Integer)request.getAttribute("like");
	int post_id = Integer.parseInt(request.getParameter("post_id"));
	
%>


<% if(cnt == 1){

	if(likes==0){%>
	<script>
	alert("좋아요")
	location.href = "view.do?post_id=<%=post_id%>"
	</script>
	
<%}else{%>
	<script>
	alert("좋아하지 않아요")
	location.href = "view.do?post_id=<%=post_id%>"
	</script>
<% } // end els ;	
} // end if %>

