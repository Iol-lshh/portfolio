	$("#id").focusin(function() { 
		$("#iderror").attr('style','color:red');
		$("#iderror").attr('class','error d-none');
		});
	
	$("#id").focusout(function() { //아이디 유효성
		
		
		var getidCheck= new RegExp(/^[a-z0-9]{5,20}$/);
		
		$("#iderror").attr('class','error');
			
		
		//아이디 공백 확인 
		if($("#id").val() == ""){  $("#iderror").html("필수 정보입니다.");  return; }
		
		
		//아이디 유효성검사 
		if(!getidCheck.test($("#id").val())){  $("#iderror").html("5~20자의 영문 소문자, 숫자만 사용 가능합니다."); return;} 
		

		
		$.ajax({
			type : "GET",
			url :  "sign-up.ajax?id="+$('#id').val(),
			success : function(data){
		
				if(data != 0){
					
					$("#iderror").html("이미 사용중인 아이디입니다.");
					
					
					
				}else if(data == 0){
					$("#iderror").attr('style','color:blue');
					$("#iderror").html("아이디 완벽함" );
					
				}
				
			},
			error : function() {
				alert("실패");
			}
		}); 
			
		}); //아이디 끝
		
			
		
	$("#um_password").focusin(function() { 
		$("#pwerror").attr('style','color:red');
		$("#pwerror").attr('class','error d-none');
		});	
		
	
	$("#um_password").focusout(function() {  //비밀번호 유효성 1
			
		var getpwCheck= RegExp(/[^0-9a-zA-Z#?!@$%^&*-]/);            
        var getpwCheck2= RegExp(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$/);
		
			
			$("#pwerror").attr('class','error');

			
			if($("#um_password").val() == ""){  $("#pwerror").html("필수 정보입니다."); return;}
			
			
			
			if(getpwCheck.test($("#um_password").val()) || !getpwCheck2.test($("#um_password").val()) ){ $("#pwerror").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); return;}
			
			else{$("#pwerror").html(""); return;}
		
			
			
				 
			if($("#id").val() == $("#pw").val()){ $("#pwerror").html("아이디와비밀번호가 같습니다"); return; }
						
				
			
			
			
		});//비밀번호 끝
		
//		$("#pwck").focusin(function() { 
//		$("#pwerror").attr('style','color:red');
//		$("#pwerror").attr('class','error d-none');
//		});	
	
	$("#passwordCheck").focusout(function() {  //비밀번호확인 유효성 2
		
		var getpwCheck= RegExp(/[^0-9a-zA-Z#?!@$%^&*-]/);            
        var getpwCheck2= RegExp(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$/);
		
		
			
			$("#pwerror").attr('class','error');

			if($("#passwordCheck").val() == ""){  $("#pwerror").html("필수 정보입니다."); return;}
			 
			if($("#um_password").val() != $("#passwordCheck").val()){$("#pwerror").html("비밀번호가 다릅니다."); return; }
			
			if($("#um_password").val() == $("#passwordCheck").val()){
				
				if(getpwCheck.test($("#um_password").val()) || !getpwCheck2.test($("#um_password").val()) ){ $("#pwerror").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); return;}			
				
				else{ $("#pwerror").attr('style','color:blue'); $("#pwerror").html("비밀번호완벽");  return; }
				
			
			}
			
			
			
			
				
			
			
			
		});//비밀번호확인 끝
	
	$("#um_nickname").focusin(function() { 
		$("#nikerror").attr('style','color:red');
		$("#nikerror").attr('class','error d-none');
		});	
	
	$("#um_nickname").focusout(function() { //닉네임 유효성 
		
		var getNik= RegExp(/^[a-zA-Z0-9가-힣]{2,5}$/);
		
		$("#nikerror").attr('class','error');
		
		if($("#um_nickname").val() == ""){ $("#nikerror").html("필수 정보입니다."); return;}
		
		
		if(!getNik.test($("#um_nickname").val())){  $("#nikerror").html("2 ~ 5 글자 한글,영어,숫자가능"); return;} 
		
		


		$('#message').val('checkNick');

		 
		 $.ajax({
				type : "post",
				url :  "/rest_area/auth/user/joinLookup",
				data: $('#signup').serialize(),
				success : function(data){
					
					if(data.status == 'OK'){
						
						$("#nikerror").attr('style','color:blue');
						$("#nikerror").html("닉네임 완벽함");
						
						
					}else if(data.status == 'FAIL'){
						$("#nikerror").html(data.message)
					} else {
						
						$("#nikerror").html("사용중인 닉네임입니다");
					}
					
				},
				error : function() {
					alert("실패");
				}
		 });

		
		
	});
	
	$("#um_username").focusin(function() { 
		
		$("#emailerror").attr('style','color:red');
		$("#emailerror").attr('class','error d-none');
		$("#emailCK").attr('disabled', true);
		});	
	
	$("#um_username").focusout(function() { //이메일 유효성
		
		var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		
		$("#emailerror").attr('class','error');
		
		if($("#um_username").val() == ""){ 
			 $("#emailerror").html("필수 정보입니다.");
			 
			 return;
		}
		
		
		if(!getemail.test($("#um_username").val())){  $("#emailerror").html("이메일 주소를 다시 확인해주세요."); return;} 
		
		
			$('#message').val('checkId');

		 
		 $.ajax({
				type : "post",
				url :  "/rest_area/auth/user/joinLookup",
				data: $('#signup').serialize(),
			success : function(data){
	
				
					if(data.status == 'OK'){
						
					$("#emailerror").attr('style','color:blue');
					$("#emailerror").html("아이디 (이메일) 완벽함");
					$("#emailCK").attr("disabled", false);
						
					}else if(data.status == 'FAIL'){
						$("#emailerror").html(data.message)
					} else {
							$("#emailerror").html("사용중인 아이디(이메일) 입니다");
					$("#emailCK").attr("disabled", true);
					
					}
				
			},
			error : function() {
				alert("실패");
			}
		});
		
			
		
		
	});
	
	
	$('#submit').click(function() {

		if($('#provisionYn2').is(":checked")) {
			alert('약관에 동의하지 않으셧습니다.');
			return;
		}
		
			$('#message').val('insertUser');
				 $.ajax({
				type : "post",
				url :  "/rest_area/auth/user/join",
				data: $('#signup').serialize(),
			success : function(data){
				console.log(data);
				
					if(data.status == 'OK'){
						
						alert('회원가입 되었습니다.');
					location.href="/rest_area/auth/user/login"
						
					}
					 else {
					alert('가입 실패 ' + data.message);
					
					}
				
			},
			error : function() {
				alert("실패");
			}
			}); 
	
	})
	
	
	
	
	
	
	function formCheck(signup) { //폼 유효성 검사
		
		
		var privacyCheck = $(":input:radio[name=provisionYn]:checked").val();
		var getidCheck= RegExp(/^[a-z0-9]{5,20}$/);
		var getpwCheck= RegExp(/[^0-9a-zA-Z#?!@$%^&*-]/);            
        var getpwCheck2= RegExp(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$/);
		var getNik= RegExp(/^[a-zA-Z0-9가-힣]{2,5}$/); 
		var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); 

		
		
		
		//약관동의 확인
		if(privacyCheck == "N" || privacyCheck == "" ){ alert("서비스 이용약관에 동의해 주세요."); return false;}
		
		//아이디 공백 확인 
		if($("#id").val() == ""){ $("#id").focus();return false; } 
		
		//아이디 유효성검사 
		if(!getidCheck.test($("#id").val())){ $("#id").focus(); return false; } 
		
		//비밀번호 공백 확인
		 if($("#um_password").val() == ""){ $("#um_password").focus(); return false; } 
		
		//아이디 비밀번호 같음 확인 
		if($("#id").val() == $("#um_password").val()){ $("#um_password").focus(); return false; } 
		
		//비밀번호 유효성검사 
		if(getpwCheck.test($("#um_password").val()) || !getpwCheck2.test($("#um_password").val()) ){  $("#um_password").focus(); return false;}
		
		//비밀번호 확인란 공백 확인 
		if($("#passwordCheck").val() == ""){ $("#passwordCheck").focus(); return false; } 
		
		//비밀번호 서로확인 
		if($("#um_password").val() != $("#passwordCheck").val()){ $("#um_password").focus(); return false; } 
		
		//닉네임 공백 확인
		if($("um_nickname").val() ==""){ $("#um_nickname").focus(); return false;}
		
		//닉네임 유효성 검사
		if(!getNik.test($("#um_nickname").val())){ $("um_nickname").focus(); return false;}
		
		//이메일 공백 확인 
		if($("#um_username").val() == ""){ $("#um_username").focus(); return false; } 
		
		//이메일 유효성 검사 
		if(!getemail.test($("#um_username").val())){ $("#um_username").focus(); return false; }
		
		
	}
	
	

	



	
	