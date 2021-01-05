var chk=null;

function emailSend() {
	 let emailck = $("#emailAC").val();
	 var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	 
	 if(!getemail.test($("#um_username").val())){alert("이메일 확인바람"); return }
	 if(getemail.test($("#um_username").val())){alert("이메일 전송"); $("#emailACbtn").attr("disabled", false);
	 	$( '#um_username' ).attr( 'readonly', true );
	 }

	
		$('#message').val('findByUsername');
		$('#kind').val('authentication');
		$('#emailCK').attr('disabled', true);




	$.ajax({
		
		type: "post",
		url: "/rest_area/auth/user/email",
		data: $('#signup').serialize(),
		success : function(data) {
			console.log(data)
			chk=data.count;
			
			
			
		},
		error : function() {
			alert('이메일 오류');
			
		}
		
		
	});	
	
}

function emailChk() {
	
		
	var emailck = $("#emailAC").val();
	
	if (chk == emailck) {
		
		alert("이메일 인증완료")
		
		chk=null;
		
		$("#emailAC").attr("disabled", true);
				$("#submit").attr("disabled", false);
		$("#emailACbtn").attr("disabled", true);
		
		
	}else{
		
		alert("이메일 인증실패")
	}
	
	

	
	
	
	
}
