<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<meta name="csrf-token" content="${_csrf.token}">
<link href='${pageContext.request.contextPath }/resources/css/board/postboard.css' rel="stylesheet"/>
<script src="${pageContext.request.contextPath }/resources/js/board/postboard.js"></script>

<div id="postboard">
	<h4>게시글</h4>
    
    <div id="post_frmlist" name="frmlist">
        <table>
            <thead>
                <th class="ra_code undisplay">ra_code</th>
                <th class="post_id undisplay">post_id</th>
                <th class="post_title">제목</th>
                <th class="um_username">작성자</th>
                <th class="post_regdate">작성일</th>
                <th class="post_like_cnt">추천</th>
                <th class="post_reported">신고</th>
            </thead>
            <tbody>
            	<a class="post_element" onclick="postView('${post_id}')" href="javascript:void(0);">
	                <tr id="posthead_${post_id}">
	                    <td class="ra_code undisplay">${ra_code}</td>
	                    <td class="post_id undisplay">${post_id}</td>
	                    <td class="post_title">제목</td>
	                    <td class="um_username">작성자</td>
	                    <td class="post_regdate">작성일</td>
	                    <td class="post_like_cnt">좋아요 수</td>
	                    <td class="post_reported"><button class="reported_btn"><image src="${pageContext.request.contextPath }/resources/img/redalert.png"></button></td>
	                </tr>
                </a>
                <!--posthead 클릭시 js로 postbody를 집어넣어주기 내용 확장-->
                <!-- <tr class="postbody">
                    <div class="post_content">
                        ${post_content}
                    </div>
                    <button id="post_updatebtn" class="btn info">수정</button>
                    <button id="post_deletebtn" class="btn danger">삭제</button>
                </tr> -->
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
   
    <div class="center">
        <ul class="pagination center" id="post_pagination">
        </ul>
    </div>

	
	<!-- 글 작성 부분 -->
	<div class="d01">
        <div class="right">
            <button type="button" id="btn_open_post_new" class="btn success" onclick="open_post_new()">글작성</button>
        </div>
        <div id="postwrite_new" class="postbody undisplay">
			<form id="postwrite" name="post" method="post">
			    <div class="post_content">
			       	<input type="text" class="write_post_title" name="post_title"  maxlength="50" placeholder="제목을 입력해주세요"/>
			       	<textarea class="write_post_content" name="post_content" onkeyup="post_chkTextlimit(this,'500')" value="" placeholder="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다. 건전한 토론문화와 양질의 댓글 문화를 위해, 타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족, 종교 등을 비하하는 단어들은 표시가 제한됩니다."></textarea>
		            <span class="text-muted right">/500</span> 
		            <span id="post_textLength" class="text-muted right">0</span>
		            <div class="clear"></div>
			    </div>
			    <button id="post_deletebtn" class="btn danger right" onclick="post_new(); return false;">완료</button>
			    <button id="post_updatebtn" class="btn info right" onclick="post_cancle(); return false;">취소</button>
		    	<div class="clear"></div>
		    </form>
		</div> 
    </div>
    
			<input type="hidden" id="parent2">
			
    <div class="clear"></div>
</div>

