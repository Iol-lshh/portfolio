
var chk=null;

function emailSend() {
	
	//유효성 검사
	 let emailck = $("#emailAC").val();
	 var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	 
	 if(!getemail.test($("#email").val())){alert("이메일 확인바람"); return; }
	 
	
	 // 이메일 중복 체크
	$.ajax({
		type : "GET",
		url :  "sign-up.ajax?email="+$('#email').val(),
		success : function(data){
			console.log(data);
			
			if(data != 0){
				
				alert("이메일 전송완료");
				
				$( '#email' ).attr( 'readonly', true );
				
				$("#emailACbtn").attr("disabled", false);
				
				
			}else if(data == 0){
				
				alert("회원가입된 정보가 없습니다");
				
				return false;
				
			}
			
		},
		error : function() {
			alert("실패");
		}
	});

	
	//이메일 인증번호
	$.ajax({
		
		type: "post",
		url: "email.ajax?email="+$('#email').val(),
		success : function(data) {
			console.log(data);
			chk=data;
			
			
			
		},
		error : function() {
			alert('이메일 오류');
			
			return ;
			
		}
		
		
	});	
	
}

function emailChk() {
	console.log(chk);
	var emailck = $("#emailAC").val();
	
	if (chk == emailck) {
		
		alert("이메일 인증완료");
		
		chk=null;
		
		$("#submit").attr("disabled", false);
		
		
	}else{
		
		alert("이메일 인증실패")
		
		return ;
	}
	
	

	
	
	
	
}


