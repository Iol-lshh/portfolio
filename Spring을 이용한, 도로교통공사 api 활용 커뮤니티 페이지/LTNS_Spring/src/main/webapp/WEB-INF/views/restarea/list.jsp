<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<c:set var="ra" value="ra"/>
<c:set var="gs" value="gs"/>
<c:set var="fm" value="fm"/>
<c:set var="by_default" value="default"/>
<c:set var="by_like" value="BY_LIKE"/>
<c:set var="by_price" value="BY_PRICE"/>
<c:set var="by_diesel" value="BY_DIESEL"/>
<c:set var="by_gasoline" value="BY_GASOLINE"/>
<c:set var="by_lpg" value="BY_LPG"/>



<html>
<head>
<meta charset="UTF-8">
<title>LTNS_Spring</title>
</head>
<!-- jquery  -->
<!--  bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- css, js -->
<link href='${pageContext.request.contextPath }/resources/css/restarea/list.css' rel="stylesheet"/>
<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/restarea/list.js'></script>
<body class="container">
	<jsp:include page="../header/header.jsp" />
	<br>
	<nav id="search" class="navbar navbar-info">
		<div class="container-fluid">
	   		<div >	   		
	   			
	   			
			    <div class="navbar-form navbar-left " role="search">
			    	<div class="navnav1">
				    	<div class="form-group dropdown">
				    		<button class="btn btn-outline-info dropdown-toggle" type="button" id="listSortBtn" name="listSort" value="ra" data-toggle="dropdown">
				    			${listSortKorean }
				    			<span class="caret"></span>
				    		</button>
							<ul class="dropdown-menu" role="menu" aria-labelledby="listSortBtn">
							    <li><a tabindex="-1" role="button" onclick="changeListSortValue('휴게소')">휴게소</a></li>
							    <li><a tabindex="-1" role="button" onclick="changeListSortValue('주유소')">주유소</a></li>
							    <li><a tabindex="-1" role="button" onclick="changeListSortValue('음식메뉴')">음식메뉴</a></li>
							</ul>
						</div>
					</div>
				    
				    <div class="navnav2">
					    <div class="form-group input-group">
					      <input id="routeName" type="text" class="form-control" placeholder="노선 입력" name="routeName" value="${routeName }">
					      <div class="input-group-btn">
					        <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false" > <span class="caret"></span></button>
					        <ul id="routeNameList" class="dropdown-menu dropdown-menu-right" role="menu">
					          <li><a class="dropdownRouteNameList" tabindex="-1" role="button" onclick="changeRouteNameValue('')">이곳에 노선을 입력</a></li>
					        </ul>
					      </div><!-- /btn-group -->
					    </div><!-- /input-group -->
					</div>
					
					<div class="navnav2">				    
					    <div class="form-group input-group">
					      <input id="destination" type="text" class="form-control"  placeholder="방향 입력" name="destination" value="${destination }">
					      <div class="input-group-btn">
					        <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false" > <span class="caret"></span></button>
					        <ul id="destinationList" class="dropdown-menu dropdown-menu-right" role="menu">
					          <li><a class="dropdownDestinationList" tabindex="-1" role="button" onclick="changeDestinationValue('')">노선을 먼저 입력해주세요</a></li>
					        </ul>
					      </div><!-- /btn-group -->
					    </div><!-- /input-group -->
				    </div>
				    <br>
				    
				    <div class="navnav3">
			    		<div id="orderByRadio" class="form-group">
					    <c:choose>
					    	<c:when test="${ra eq listSort }">
					    		<c:choose>
					    			<c:when test="${orderBy eq by_default }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_like }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" checked="checked">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
					    			</c:when>
					    		</c:choose>	
					    	</c:when>
					    	<c:when test="${gs eq listSort }">
					    		<c:choose>
					    			<c:when test="${orderBy eq by_default }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL">
											<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE">
											<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG">
											<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_like }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" checked="checked">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL">
											<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE">
											<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG">
											<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_diesel }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" >
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL" checked="checked">
											<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE">
											<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG">
											<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_gasoline }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" >
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL">
											<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE" checked="checked">
											<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG">
											<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_lpg }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" >
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL">
											<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE">
											<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG" checked="checked">
											<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>
										</div>
					    			</c:when>
					    		</c:choose>	
					    	</c:when>
					    	<c:when test="${fm eq listSort }">
					    		<c:choose>
					    			<c:when test="${orderBy eq by_default }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_PRICE">
											<label class="custom-control-label" for="jb-radio-3">가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_like }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE" checked="checked">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_PRICE">
											<label class="custom-control-label" for="jb-radio-3">가격 순서</label>
										</div>
					    			</c:when>
					    			<c:when test="${orderBy eq by_price }">
								    	<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default">
											<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">
											<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
										</div>
										<div class="custom-control custom-radio">
											<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_PRICE" checked="checked">
											<label class="custom-control-label" for="jb-radio-3">가격 순서</label>
										</div>
					    			</c:when>
					    		</c:choose>	
					    	</c:when>
					    </c:choose>
					    </div>
					</div> <!-- end radio navnav -->    
				    
				    
				    <button id="list_searchBtn" class="btn btn-info right" onclick="searchBtnClick()">검색</button>
				    <div class="clear"></div>
			    </div><!-- form 역할 -->
		  
	   		</div>
		</div>
	</nav>
	<main id="searchResultList">
		<!-- 임시 -->
		<jsp:include page="./component/element.jsp"></jsp:include>
	</main>
	<br>
	<jsp:include page="../footer/footer.jsp" />
	<jsp:include page="../others/fixedbtn.jsp"/>
	<jsp:include page="../board/restareaview.jsp"/>
</body>
<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/restarea/list_ready.js'></script>


</html>