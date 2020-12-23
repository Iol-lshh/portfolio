<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="main.java.com.model.*" %>
<%@page import="main.java.com.model.mainpage.*" %>

<!--  -->
<% 
	String str=request.getParameter("viewCnt");
	System.out.println("sm_card_list : "+str);
%>
<c:set var="post_id" value='<%=request.getParameter("post_id") %>'/>
<c:set var="title" value='<%=request.getParameter("title") %>'/>
<c:set var="regdate" value='<%=request.getParameter("regdate") %>'/>
<c:set var="viewCnt" value='<%=request.getParameter("viewCnt") %>'/>

<a class="sm_card_list list" href="../post/view.do?post_id=${post_id }">
	<p class="sm_card_list-title title">${param.title }</p>
	<div class="sm_card_list-metadata"><time class="sm_card_list-regdate small">${param.regdate }</time><p class="sm_card_list-writer small">조회수 : ${param.viewCnt }</p></div>
</a>
<!-- -->
