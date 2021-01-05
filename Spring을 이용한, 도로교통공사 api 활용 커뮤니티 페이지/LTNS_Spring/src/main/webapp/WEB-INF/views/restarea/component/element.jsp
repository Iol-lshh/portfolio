<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<c:set var="ra" value="ra"/>
<c:set var="gs" value="gs"/>
<c:set var="fm" value="fm"/>

<c:choose>
	<c:when test="${ra eq listSort }">
		<c:forEach var="dto" items="${dtos }">
			<a class="listelement" onclick="setPopup('${dto.ra_code}')" href="javascript:void(0);" >
				<p class="ra_code undisplay">${dto.ra_code }</p>
				<div class="card">
				  <img src="..." alt="" />
				  <div class="card-body">
				    <h5 class="card-title">${dto.ra_name }</h5>
				    <p class="card-text">위도 : ${dto.ra_xValue }, 경도 : ${dto.ra_yValue }</p>
				  </div>
				</div>
			</a>
		</c:forEach>	
	</c:when>
	<c:when test="${gs eq listSort }">
		<c:forEach var="dto" items="${dtos }">
			<a class="listelement" onclick="setPopup('${dto.ra_code}')" href="javascript:void(0);" >
				<p class="ra_code undisplay">${dto.ra_code }</p>
				<div class="card">
				  <img src="..." alt="" />
				  <div class="card-body">
				  	<p class="card-text">${dto.ra_name }</p>
				    <h5 class="card-title">${dto.gs_name }</h5>
				    <p class="card-text">경유 가격 : ${dto.gs_diesel }원,  휘발유 가격 : ${dto.gs_gasoline }원,  LPG 가격 : ${dto.gs_lpg }원</p>
				  </div>
				</div>
			</a>
		</c:forEach>
	</c:when>
	<c:when test="${fm eq listSort }">
		<c:forEach var="dto" items="${dtos }">
			<a class="listelement" onclick="setPopup('${dto.ra_code}')" href="javascript:void(0);" >
				<p class="ra_code undisplay">${dto.ra_code }</p>
				<div class="card">
				  <img src="..." alt="" />
				  <div class="card-body">
				  	<p class="card-text">${dto.ra_name }</p>
				    <h5 class="card-title">${dto.fm_name }</h5>
				    <p class="card-text">가격 : ${dto.fm_price }원</p>
			   	    <p class="card-text">재료 : ${dto.fm_material }</p>
			   	    <p class="card-text">${dto.fm_etc }</p>
				  </div>
				</div>
			</a>
		</c:forEach>
	</c:when>
</c:choose>



