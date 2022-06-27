<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="main.java.com.model.*" %>
<%@page import="main.java.com.model.mainpage.*" %>

<% 
	String str=request.getParameter("title");
	System.out.println("sm_card_post : "+str);
%>
<!--  -->
<c:set var="post_id" value='<%=request.getParameter("post_id") %>'/>
<c:set var="title" value='<%=request.getParameter("title") %>'/>
<c:set var="writer" value='<%=request.getParameter("writer") %>'/>
<c:set var="regdate" value='<%=request.getParameter("regdate") %>'/>
<c:set var="contents" value='<%=request.getParameter("contents") %>'/>
<%-- 
<c:set var="dtoArr" value="<%= %>"/>
--%>
<a class="sm_card_post list" href="../post/view.do?post_id=${post_id }">
	<p class="sm_card_post-title title">${param.title }</p>
	<div class="sm_card_list-metadata"><p class="sm_card_post-writer small">${param.writer }</p><time class="sm_card_post-regdate small">${param.regdate }</time></div>
	<p class="sm_card_post-contents small">${param.contents }</p>
</a>
<!--  -->

