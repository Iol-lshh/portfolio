<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="main.java.com.view.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
	request.setCharacterEncoding("utf-8");
	int cnt = (Integer)request.getAttribute("loginOk");   
	MemberDTO[] arrs = (MemberDTO[])request.getAttribute("info_chk");
	String nick  ="";
	if(arrs!= null){
		 nick = arrs[0].getNickname();
		}
 %>  
 


    
  
<%if(cnt ==0 || arrs == null  ){ %>
	<script>
		alert("로그인 실패 정보가 일치하지 않습니다");
		history.back(); // 실패시 브라우저가 직전에 기억하는 이전 페이지로 이동함
	</script>
<%}else{%>
	<script>
		alert("로그인 성공  <%=nick%> 님 환영합니다.");		
		location.href = "<%=request.getContextPath() %>/mainpage/mainpage.do"
	</script>
	
	
	
	
		<!-- 세션 생성  -->
	<%
	String [] s_arr = {"writer","login","nickname","id","email","grade","rec_chk_write"};
	Object [] v_arr = {arrs[0].getMm_id(), 1, arrs[0].getNickname(), arrs[0].getId(), arrs[0].getEmail(), arrs[0].getGrade(), 0};
	
	for(int i=0; i<s_arr.length; i++){
		session.setAttribute(s_arr[i],v_arr[i]);
	}
	%>
	
	
<%}%>