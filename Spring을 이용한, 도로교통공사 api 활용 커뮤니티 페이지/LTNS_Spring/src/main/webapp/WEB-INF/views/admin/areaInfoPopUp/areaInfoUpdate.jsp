<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="csrf-token" content="${_csrf.token}">
<title>지도 팝업</title>
</head>

<!-- jquery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cda329f12b24c4e5be5c45c10463b943"></script>
<script src='${pageContext.request.contextPath }/admin/JS/areaInfoPopup.js'></script>


<body>
	<input type="hidden" id="datas_name">
	<div id="map" style="width:780px; height:580px;"></div>

</body>
</html>