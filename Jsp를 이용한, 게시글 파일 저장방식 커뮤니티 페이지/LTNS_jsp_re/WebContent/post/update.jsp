<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="main.java.com.model.post.WriteDTO"%>
<%@page import="main.java.com.model.post.FileWriteDTO"%>
<%@page import="main.java.com.model.DTO"%>    
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<%
	
	request.setCharacterEncoding("utf-8");
	WriteDTO[] arr = (WriteDTO[])request.getAttribute("update");
%>

<%
	if( arr == null || arr.length ==0  ){
%>
	<script>
		alert("해당정보가 삭제되거나 없습니다");
		history.back();
	</script>
<%
	return;
	}

%>

<% 
	FileWriteDTO[] file_info = (FileWriteDTO[])request.getAttribute("file_view");

	
	String ctx = request.getContextPath();
	int writer =  (int) session.getAttribute("writer");
	String title = arr[0].getTitle();
	String grade =  (String)session.getAttribute("grade");
	int  post_id = Integer.parseInt(request.getParameter("post_id"));
	
	
%>









<%
	Date today = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("[yyyy-mm-dd]");

%>    

<%
	//파일읽어오기  
	String titles ="";	
	String contents ="";
	String filename = file_info[0].getFilename();
	String saveDirectory = getServletContext().getRealPath("/") + "data" + File.separator + filename;
	String urls = getServletContext().getRealPath("/") + "data" + File.separator;
	
	
	BufferedReader br = null;
	try{
	br = new BufferedReader(new FileReader(saveDirectory));
	String line = null;


	titles = br.readLine();
	
	while((line = br.readLine())!=null){
		if(!line.equals(titles)){
			contents += line;
		}
	}//end while
	
	
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> update Page </title>
</head>
<!-- css link  -->
 <link rel="stylesheet" type="text/css" href="CSS/Write.css">
 <link rel="stylesheet" type="text/css" href="../footer/css/footer.css">
 <link rel="stylesheet" type="text/css" href="../header/css/header.css">


<!-- bootstrep -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<!-- smartedit -->
<script type="text/javascript" src="<%=ctx %>/post/S2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<%=ctx%>/post/S2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : false,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec("PASTE_HTML", [" "]);
          },
          fCreator: "createSEditor2"
      });
    
  
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        $("#frm").submit();
    }); 
	return true;
  
});
 
 
function chkSubmit(){
	frm = document.forms['frm'];
	
	var title = frm["title"].value.trim();
	
	if( title == "" ){
		alert("제목이 비었습니다 제목은 필수사항 입니다.");		
		frm["title"].focus();
		return false;
	}

}

var delay = 300;
var timer = null;
var cnt = 0;

$(window).on('resize', function(){
	clearTimeout(timer);
	timer = setTimeout(function(){
		 var width_size = window.outerWidth;
		 var td = document.getElementById("tds");
		 if (width_size <= 780) {
			alert("네이버 스마트 에디터는 width:800(이상) 사용이 가능합니다.")
		}else if(width_size >=800){
			 location.reload();
		}
	}, delay);
});


</script>

<body class="container">

	<!--  헤더  -->
	<jsp:include page="../header/component/header.jsp" />

	<div class="col-12">
	   <div>
		<form name="frm" action="updateOk.do?post_id=<%=post_id %>" method="post" onsubmit="return chkSubmit()">
			<table class="table">
		        <tr >
		       		<td class="text-center" style="width:5%;">
		       		<select name="category" class='selector'>
		       		
		       		<%if(grade.equals("admin")){ %>
		       		<option value="NOTICE" > 공지사항 </option>
					<%} %>
		       		<option value="MOVIE" selected="selected" > 영화 게시판 </option>
		       		<option value="GAME"> 게임 게시판 </option>
		       		<option value="BOOK">도서 게시판  </option>
		       		<option value="SPORTS"> 스포츠 게시판 </option>	
		       		</select>
		       		</td>     
		            <td>
		            <input type="text" id="title" name="title" style="width:100%" maxlength="30" value="<%=title %>"/>
		            </td>
		        </tr>
		          <tr class="mt-2 text-right">
		            <td colspan="2" >
		                <input class="fun-btn btn-sm font-weight-bold" type="button" value="취소" onclick="history.back()"/>
		                <input class="fun-btn btn-sm font-weight-bold" type="submit" id="save" value="수정"/>
		            </td>
		        </tr>
		        <tr class="justify-content-center">
		            <td colspan="2" id="tds">
		                <textarea rows="10" cols="30" id="ir1" name="content" style="width:100%; height:350px;">
		                <%=contents %>
		                </textarea>
		            </td>
		        </tr>
				</table>
				<input type="hidden" name="urls" value="<%=urls %>"/>
				<input type="hidden" name="streinger" value="<%=writer %>"/>
			
			</form>
	  </div>
	</div>
	
	<!--  footer -->
		<jsp:include page="../footer/component/footer.jsp" />
	
	
</body>

</html>