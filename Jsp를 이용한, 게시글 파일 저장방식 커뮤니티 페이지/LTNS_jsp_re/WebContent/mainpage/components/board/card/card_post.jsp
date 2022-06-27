<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- -->
<a id="whole_${param.i }" class="card_post article" href="../post/view.do?post_id=${param.post_id }">
	<div class="card_post-picture"><img src="${param.thumbnailPath }"/></div>
	<div class="card_post-main">
		<p class="post_id" style="display:none">${param.post_id }</p>
		<p class="card_post-title title">${param.title }</p>
		<div class="sm_card_list-metadata"><p class="card_post-writer small">${param.writer }</p><time class="card_post-regdate small">${param.regdate }</time></div>
		<p class="card_post-contents small">${param.contents }</p>
	</div>
</a>
<!--  -->

