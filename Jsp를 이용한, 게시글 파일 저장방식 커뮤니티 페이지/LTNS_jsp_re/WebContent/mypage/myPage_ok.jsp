<%@page import="main.java.com.view.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int cnt = (Integer) request.getAttribute("rev_pw");
	String ctx = request.getContextPath();
	String rev_f = (String) session.getAttribute("nickname");
	MemberDTO[] arr = (MemberDTO[]) request.getAttribute("rev_nick");

	
	if(arr != null){
		session.removeAttribute("nickname");
		session.setAttribute("nickname", arr[0].getNickname());
	}
	
%>


<%if(cnt ==1){ %>
<script>
	alert("정보가 변경되었습니다.")
	location.href = "<%=ctx%>/mypage/mypage.do";
</script>
<%}else{%>
<script>
	alert("중복된 닉네임 입니다. 또는 정보가 올바르지 않습니다.")
	location.href = "<%=ctx%>/mypage/mypage.do";
</script>

<%}%>