<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="main.java.com.model.*" %>
<%@page import="main.java.com.model.mainpage.*" %>

<!--  -->
<% 
	String str=request.getParameter("comment_contents");
	System.out.println("댓글 : "+str);
%>

<a class="sm_card_list list" href="../post/view.do?post_id=${param.post_id }">
	<p class="sm_card_list-title title">${param.comment_contents }</p>
	<div class="sm_card_list-metadata"><time class="sm_card_list-regdate small">${param.comment_regdate }</time></div>
</a>
<!-- -->
