<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<!-- 일반 view -->
<tr class="postbody">
    <div class="post_content">
        ${post_content}
    </div>
    <button id="post_updatebtn" class="btn info">수정</button>
    <button id="post_deletebtn" class="btn danger">삭제</button>
</tr> 

<!-- 글쓴 회원, 관리자 view -->
<tr class="postbody">
    <div class="post_content">
        ${post_content}
    </div>
    <button id="post_updatebtn" class="btn info">수정</button>
    <button id="post_deletebtn" class="btn danger">삭제</button>
</tr> 


<!-- 글쓰기 확장 -->
<div id="postwrite_new" class="postbody">
	<form id="postwrite" name="post" method="post">
	    <div class="post_content">
	       	<input type="text" class="post_title" name="post_title"  maxlength="50" placeholder="제목을 입력해주세요"/>
	       	<textarea class="post_content" name="post_content" onkeyup="post_chkTextlimit(this,'500')" placeholder="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다. 건전한 토론문화와 양질의 댓글 문화를 위해, 타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족, 종교 등을 비하하는 단어들은 표시가 제한됩니다.">
	       	
            </textarea>
            <span id="post_textLength" class="text-muted right">0</span>
            <span class="text-muted right">/500</span> 
            <div class="clear"></div>
	    </div>
	    <button id="post_updatebtn" class="btn info" onclick="post_cancle(); return false;">취소</button>
	    <button id="post_deletebtn" class="btn danger" onclick="post_new(); return false;">완료</button>
    </form>
</div> 