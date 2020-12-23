<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	int cnt = (Integer)request.getAttribute("delteOk");
%>
    
    
<% if(cnt == 0){ %>
	<script>
		alert("삭제 실패");
		history.back();
	</script>
<% } else { %>
	<script>
		alert("삭제 성공");
		location.href = "<%=request.getContextPath() %>/board/board_list.do"
	</script>
<% } %>