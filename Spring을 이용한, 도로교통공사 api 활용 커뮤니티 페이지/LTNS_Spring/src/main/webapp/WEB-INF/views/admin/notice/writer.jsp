<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="csrf-token" content="${_csrf.token}">
<title>writer</title>
</head>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>

<body>
<div id="writeNotices">		
<script src='${pageContext.request.contextPath }/admin/JS/notice.js'></script>


<form id="writeForm">
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						placeholder="관리자 공지사항 제목 입니다." id ="title"  name="title">
					<div class="input-group-append"></div>
				
					<input type="hidden" name="${id}" id="hidens" value="${id}">
				</div>
				
				
				<textarea id="editor1" name="editor1" ></textarea>
				
                <script>
                CKEDITOR.replace( 'editor1',{
                	height:400,
             		
					//ckedit 옵션
                	toolbar : [ 
                	{ name: 'clipboard', items: ['Paste', 'PasteText', 'PasteFromWord', '-'] }, 
                	{ name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-' ] }, 
                	{ name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] }, 
                	'/', 
                	{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] }, 
                	{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] }, 
                	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] }, 
                	{ name: 'insert', items: ['Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] }, 
                	'/', 
                	{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] }, 
                	{ name: 'colors', items: [ 'TextColor', 'BGColor' ] }, 
                	{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] }, 
                	
                	]
                	
                
            
                	
                } );
          	   </script>
        
        		<button type="button" onclick="writeOk();" class="mt-2  float-right btn btn-outline-success"> 작성 </button>
				<button type="button" class="mt-2 mr-2 float-right btn btn-outline-danger" onclick="location.href='${pageContext.request.contextPath}/admin/notice'" > 취소 </button>
			</form>
			</div>
</body>
</html>