var chk=null;

function emailSend() {
	 let emailck = $("#emailAC").val();
	 var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	 
	 if(!getemail.test($("#email").val())){alert("이메일 확인바람"); return }
	 if(getemail.test($("#email").val())){alert("이메일 전송"); $("#emailACbtn").attr("disabled", false);
	 	$( '#email' ).attr( 'readonly', true );
	 }

	
		
	$.ajax({
		
		type: "post",
		url: "email.ajax?email="+$('#email').val(),
		success : function(data) {
			console.log(data);
			chk=data;
			
			
			
		},
		error : function() {
			alert('이메일 오류');
			
		}
		
		
	});	
	
}

function emailChk() {
	console.log(chk);
	var emailck = $("#emailAC").val();
	
	if (chk == emailck) {
		
		alert("이메일 인증완료")
		
		chk=null;
		
		$("#submit").attr("disabled", false);
		
		
	}else{
		
		alert("이메일 인증실패")
	}
	
	

	
	
	
	
}
