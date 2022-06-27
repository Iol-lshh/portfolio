<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title><%=URLDecoder.decode(request.getParameter("title"), "UTF-8") %></title>