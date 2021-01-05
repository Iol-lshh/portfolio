<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<link href='${pageContext.request.contextPath }/resources/css/board/restareaview.css' rel="stylesheet"/>
<script src="${pageContext.request.contextPath }/resources/js/board/restareaview.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aa50752794201809afbdc42f7f3046de"></script>

<div id="restareaview" class="modal">
    <div class="modal-content animate">
        <div class="container">

            <span class="close" title="Close Modal" onclick="closeModal()">&times;</span>

            <p id="ra_code" class="undisplay">ra_code</p>
            <div id="rabox">
                <p>휴게소 이름</p>
                <span><button class="like_btn"><image src="${pageContext.request.contextPath }/resources/img/like.png"></image></button>좋아요 수</span>
                <p>노선 이름</p><p>노선 방향</p><p>위치(경도,위도) : 경도, 위도</p>
            </div>
			<div id="kakao_map"></div>
          
            <div id="gsbox">
                <p>주유소 이름</p>
                <span><button class="like_btn"><image src="${pageContext.request.contextPath }/resources/img/like.png"></image></button>좋아요 수</span>
                <p>휘발유 가격 : 원</p>
                <p>경유 가격 : 원</p>
                <p>LPG 가격 : 원</p>
            </div>
            <div id="fmbox">
            	<div id="inner_fmbox" style="max-height:300px">
	                <h4>음식 메뉴</h4>
	                <ul>
	                    <li>
	                        <p>음식 이름</p> 
	                        <span><button class="like_btn"><image src="${pageContext.request.contextPath }/resources/img/like.png"></image></button>좋아요 수</span>
	                        <p>소비자가 : 원
	                        <br>재료 : 
	                        <br>음식 설명</p>
	                    </li>
	                </ul>
                </div>
                <button onclick="fmbox_toggle()">펼치기</button>
            </div>
            <jsp:include page="./postboard.jsp"/>
        </div><!--end div container-->
    </div>
</div>
