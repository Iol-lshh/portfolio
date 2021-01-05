<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<link href='${pageContext.request.contextPath }/resources/css/front.css' rel="stylesheet"/>
<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/front.js'></script>


<body class="container">
	<jsp:include page="../header/header.jsp" />
	<div id="frontpage" class="gridcontainer">
		<div id="backgroundPane">
		</div>
		
		<div class="gridNav">
	 		<div class="backgroundNav"></div>
		 	<nav id="search" class="navbar navbar-info">
				<div class="container-fluid">
			   		<div >	   		
			   			
			   			
					    <div class="navbar-form navbar-left " role="search">
					    	<div id="search-top-margin"/><br></div>
					    	<div class="form-group dropdown">
					    		<button class="btn btn-outline-info dropdown-toggle" type="button" id="listSortBtn" name="listSort" value="ra" data-toggle="dropdown">
					    			휴게소
					    			<span class="caret"></span>
					    		</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="listSortBtn">
								    <li><a tabindex="-1" role="button" onclick="changeListSortValue('휴게소')">휴게소</a></li>
								    <li><a tabindex="-1" role="button" onclick="changeListSortValue('주유소')">주유소</a></li>
								    <li><a tabindex="-1" role="button" onclick="changeListSortValue('음식메뉴')">음식메뉴</a></li>
								</ul>
							</div>
		
						    
						    <div class="form-group input-group">
						      <input id="routeName" type="text" class="form-control" placeholder="노선 입력" name="routeName" value="">
						      <div class="input-group-btn">
						        <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false" > <span class="caret"></span></button>
						        <ul id="routeNameList" class="dropdown-menu dropdown-menu-right" role="menu">
						          <li><a class="dropdownRouteNameList" tabindex="-1" role="button" onclick="changeRouteNameValue('')">이곳에 노선을 입력</a></li>
						        </ul>
						      </div><!-- /btn-group -->
						    </div><!-- /input-group -->
						    
						    <div class="form-group input-group">
						      <input id="destination" type="text" class="form-control"  placeholder="방향 입력" name="destination" value="">
						      <div class="input-group-btn">
						        <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false" > <span class="caret"></span></button>
						        <ul id="destinationList" class="dropdown-menu dropdown-menu-right" role="menu">
						          <li><a class="dropdownDestinationList" tabindex="-1" role="button" onclick="changeDestinationValue('')">노선을 먼저 입력해주세요</a></li>
						        </ul>
						      </div><!-- /btn-group -->
						    </div><!-- /input-group -->
						    
						    <div id="orderByRadio" class="form-group">
						    	<div class="custom-control custom-radio">
									<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">
									<label class="custom-control-label" for="jb-radio-1">경로 순서</label>
								</div>
								<div class="custom-control custom-radio">
									<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">
									<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>
								</div>
						    </div>
						    
						    <button id="searchBtn" class="btn btn btn-info" onclick="searchBtnClickLogic()">검색</button>
						    <br class="clear-br">
						    <div id="search-bottom-margin"/><br></div>
					    </div><!-- form 역할 -->
				  
			   		</div>
				</div>
			</nav>
		</div><!-- end gridNav -->
	</div><!-- end gridcontainer -->
</body>
</html>