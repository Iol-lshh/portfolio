<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String url = "";
	String root = "NOTICE";
	String type = "list";
	if(request.getParameter("root") != null && !request.getParameter("root").equals("")) {
		root = request.getParameter("root");		
		url += "&root=" + request.getParameter("root");
	}
	if(request.getParameter("searchType") != null 
			&& !request.getParameter("searchType").equals("")
			&& request.getParameter("search") != null 
			&& !request.getParameter("search").equals("")) {
		url += "&searchType=" + request.getParameter("searchType");
		url += "&search=" + request.getParameter("search");
	}
	if(request.getParameter("type") != null && !request.getParameter("type").equals(""))
		type = request.getParameter("type");
	
	String initialURL = "?root=" + root + "&type=" + type;
	
%> 
<div class="actionBar py-3">
  <div class="actionBtns">
    <a class="actionBtn btn" href="board_list.do?type=list<%=url%>">
      <i class="fas fa-list">list</i>
    </a>
    <a class="actionBtn btn" href="board_list.do?type=album<%=url%>">
      <i class="far fa-images">album</i>
    </a>
    <a class="actionBtn btn" href="board_list.do?type=post<%=url%>">
      <i class="far fa-address-card">post</i>
    </a>
  </div>
  <div class="actionbar__wrapper">
    <a class="btn btn-outline-warning" href="board_list.do<%=initialURL%>">
            검색초기화
    </a>
    <button class="btn btn-outline-primary" id="searchBtn">
      <i class="fas fa-search"></i>
            검색
    </button>
    <c:choose>
    	<c:when test="${param.root eq 'NOTICE' }">
    		<c:if test="${grade eq 'admin'}">
			    <a
			      id="writeBtn"
			      class="btn btn-outline-primary"
			      ><i class="fas fa-pencil-alt"></i>글쓰기</a
			    >    	    	
    		</c:if>
    	</c:when>
    	<c:otherwise>
		    <c:if test="${login ne '0'}">
		      <c:choose>
		        <c:when test="${param.root eq 'EMPATHIZE' }">
		        </c:when>
		        <c:when test="${param.root eq 'VIEWCNT' }">
		        </c:when>
		        <c:otherwise>
			      <a
			        id="writeBtn"
			        class="btn btn-outline-primary"
			        ><i class="fas fa-pencil-alt"></i>글쓰기</a
			      >    	
		        </c:otherwise>
		      </c:choose>
		    </c:if>    		
    	</c:otherwise>
    </c:choose>
  </div>
</div>
<div class="modal" id="modalForm" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"><i class="fas fa-search"></i> 검색</h5>
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
        >
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="searchForm" name="searchForm" method="get">
          <select class="form-control" name="searchType" id="searchType">
            <c:if test="${param.root ne 'MYPAGE' }">        
              <option value="writer">닉네임(작성자)</option>
            </c:if>
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="titleAndContent" selected>제목 + 내용</option>
          </select>
          <input
            class="form-control mt-3"
            type="search"
            id="search"
            name="search"
          />
          <div class="input-group mt-3">
			<input class="form-control" type="date" id="startDate" name="startDate" />
			  <div class="input-group-prepend">
			    <span class="input-group-text">-</span>
			  </div>
			<input class="form-control" type="date" id="endDate" name="endDate" />
		  </div>
        </form>
      </div>
      <div class="modal-footer">
        <button
          type="button"
          class="btn btn-secondary"
          data-dismiss="modal"
        >
          취소
        </button>
        <button class="btn btn-primary" id="searchSubmitBtn">검색</button>
      </div>
    </div>
  </div>
</div>