$(document).ready(function(){
	

	
	  $("#submitbtn").click(function(){
			var myform = document.forms = ['frm']
			var id = frm['id'].value.trim();
			var pw = frm['password'].value.trim();
			
			
			if(id == "" ){
				alert("아이디를 입력해주세요.")
				frm['id'].focus();
				return false;
			}
			
			if(pw == "" ){
				alert("비밀번호를 입력해주세요")
				frm['password'].focus();
				return false;
			}
			
			
			return true;
			
			
	  }); 

	
})
