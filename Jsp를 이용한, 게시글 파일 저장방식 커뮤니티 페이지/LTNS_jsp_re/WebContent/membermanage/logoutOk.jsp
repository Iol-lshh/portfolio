<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctx = request.getContextPath();
	System.out.println(ctx);
%>

<script>
	alert("세션삭제 완료 ");
	location.href = "<%=ctx%>/membermanage/loginmain.jsp";
</script>

