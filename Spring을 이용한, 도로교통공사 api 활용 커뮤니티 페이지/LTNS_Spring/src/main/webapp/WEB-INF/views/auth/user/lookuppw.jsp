<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/css/user/lookuppw.css"/>
    <title>비밀번호 찾기</title>
</head>
<body>
<h1 class="mb-5">비밀번호 찾기</h1>
<div class="container">
    <form id="lookuppw">
        회원가입시 등록한 이메일
        <div class="input-group mb-5">
            <input class="form-control" type="email" id="um_username"
                   name="um_username"/>
            <button class="btn btn-primary ml-2" id="pwBtn" type="button">비밀번호
                찾기
            </button>
        </div>
        <div id="changePassword" class="form-group mt-3 hide">
            비밀번호 <input class="form-control mb-2" type="password"
                        id="um_password" name="um_password"/> 비밀번호 확인 <input
                class="form-control" type="password" id="passwordCheck"
                name="passwordCheck"/>
            <div id="password" class="error"></div>
            <button class="btn btn-primary mt-2 form-control" id="pwChangeBtn"
                    type="button">비밀번호 변경
            </button>
        </div>
        <input type="hidden" id="message" name="message"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<script
        src="<%=request.getContextPath()%>/resources/js/user/lookuppw.js"></script>
</body>
</html>