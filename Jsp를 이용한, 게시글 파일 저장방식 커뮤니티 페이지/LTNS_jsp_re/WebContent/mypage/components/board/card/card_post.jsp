<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- -->
<a id="whole_${param.post_id }" class="card_post article" href="../post/view.do?post_id=${param.post_id }">
	<div class="card_post-picture"><img src="${param.post_thumbnailPath }"/></div>
	<div class="card_post-main">
		<p class="post_id" style="display:none">${param.post_id }</p>
		<p class="card_post-title title">${param.post_title }</p>
		<div class="sm_card_list-metadata"><p class="card_post-writer small">${param.post_writer }</p><time class="card_post-regdate small">${param.post_regdate }</time></div>
		<p class="card_post-contents small">${param.post_contents }</p>
	</div>
</a>
<!--  -->

