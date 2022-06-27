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
		
		
		console.log( "/sign-up.ajax?id="+$('#id').val());
		
		$.ajax({
			type : "GET",
			url :  "sign-up.ajax?id="+$('#id').val(),
			success : function(data){
				console.log(data);
				
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
		
			
		
	$("#pw").focusin(function() { 
		$("#pwerror").attr('style','color:red');
		$("#pwerror").attr('class','error d-none');
		});	
		
	
	$("#pw").focusout(function() {  //비밀번호 유효성 1
			
		var getpwCheck= RegExp(/[^0-9a-zA-Z#?!@$%^&*-]/);            
        var getpwCheck2= RegExp(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$/);
		
			
			$("#pwerror").attr('class','error');

			
			if($("#pw").val() == ""){  $("#pwerror").html("필수 정보입니다."); return;}
			
			
			
			if(getpwCheck.test($("#pw").val()) || !getpwCheck2.test($("#pw").val()) ){ $("#pwerror").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); return;}
			
			else{$("#pwerror").html(""); return;}
		
			
			
				 
			if($("#id").val() == $("#pw").val()){ $("#pwerror").html("아이디와비밀번호가 같습니다"); return; }
						
				
			
			
			
		});//비밀번호 끝
		
//		$("#pwck").focusin(function() { 
//		$("#pwerror").attr('style','color:red');
//		$("#pwerror").attr('class','error d-none');
//		});	
	
	$("#pwck").focusout(function() {  //비밀번호확인 유효성 2
		
		var getpwCheck= RegExp(/[^0-9a-zA-Z#?!@$%^&*-]/);            
        var getpwCheck2= RegExp(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,16}$/);
		
		
			
			$("#pwerror").attr('class','error');

			if($("#pwck").val() == ""){  $("#pwerror").html("필수 정보입니다."); return;}
			 
			if($("#pw").val() != $("#pwck").val()){$("#pwerror").html("비밀번호가 다릅니다."); return; }
			
			if($("#pw").val() == $("#pwck").val()){
				
				if(getpwCheck.test($("#pw").val()) || !getpwCheck2.test($("#pw").val()) ){ $("#pwerror").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); return;}			
				
				else{ $("#pwerror").attr('style','color:blue'); $("#pwerror").html("비밀번호완벽");  return; }
				
			
			}
			
			
			
			
				
			
			
			
		});//비밀번호확인 끝
	
	$("#nik").focusin(function() { 
		$("#nikerror").attr('style','color:red');
		$("#nikerror").attr('class','error d-none');
		});	
	
	$("#nik").focusout(function() { //닉네임 유효성 
		
		var getNik= RegExp(/^[a-zA-Z0-9가-힣]{2,5}$/);
		
		$("#nikerror").attr('class','error');
		
		if($("#nik").val() == ""){ $("#nikerror").html("필수 정보입니다."); return;}
		
		
		if(!getNik.test($("#nik").val())){  $("#nikerror").html("2 ~ 5 글자 한글,영어,숫자가능"); return;} 
		
		
		
		 console.log( "/sign-up.ajax?nik="+$('#nik').val());
		 
		 $.ajax({
				type : "GET",
				url :  "sign-up.ajax?nik="+$('#nik').val(),
				success : function(data){
					console.log(data);
					
					if(data != 0){
						
						$("#nikerror").html("사용중인 닉네임입니다");
						
						
					}else if(data == 0){
						$("#nikerror").attr('style','color:blue');
						$("#nikerror").html("닉네임 완벽함");
					}
					
				},
				error : function() {
					alert("실패");
				}
		 });

		
		
	});
	
	$("#email").focusin(function() { 
		$("#emailerror").attr('style','color:red');
		$("#emailerror").attr('class','error d-none');
		$("#emailCK").attr("disabled", true);
		});	
	
	$("#email").focusout(function() { //이메일 유효성
		
		var getemail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		
		$("#emailerror").attr('class','error');
		
		if($("#email").val() == ""){ 
			 $("#emailerror").html("필수 정보입니다.");
			 
			 return;
		}
		
		
		if(!getemail.test($("#email").val())){  $("#emailerror").html("이메일 주소를 다시 확인해주세요."); return;} 
		
		
		$.ajax({
			type : "GET",
			url :  "sign-up.ajax?email="+$('#email').val(),
			success : function(data){
				console.log(data);
				
				if(data != 0){
					
					$("#emailerror").html("사용중인 이메일입니다");
					$("#emailCK").attr("disabled", true);
					
					return;
					
				}else if(data == 0){
					$("#emailerror").attr('style','color:blue');
					$("#emailerror").html("이메일 완벽함");
					$("#emailCK").attr("disabled", false);
				}
				
			},
			error : function() {
				alert("실패");
			}
		});
		
			
		
		
		
	});
	
	
	

	



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
		 if($("#pw").val() == ""){ $("#pw").focus(); return false; } 
		
		//아이디 비밀번호 같음 확인 
		if($("#id").val() == $("#pw").val()){ $("#pw").focus(); return false; } 
		
		//비밀번호 유효성검사 
		if(getpwCheck.test($("#pw").val()) || !getpwCheck2.test($("#pw").val()) ){  $("#pw").focus(); return false;}
		
		//비밀번호 확인란 공백 확인 
		if($("#pwck").val() == ""){ $("#pwck").focus(); return false; } 
		
		//비밀번호 서로확인 
		if($("#pw").val() != $("#pwck").val()){ $("#pw").focus(); return false; } 
		
		//닉네임 공백 확인
		if($("nik").val() ==""){ $("#nik").focus(); return false;}
		
		//닉네임 유효성 검사
		if(!getNik.test($("#nik").val())){ $("nik").focus(); return false;}
		
		//이메일 공백 확인 
		if($("#email").val() == ""){ $("#email").focus(); return false; } 
		
		//이메일 유효성 검사 
		if(!getemail.test($("#email").val())){ $("#email").focus(); return false; }
		
		
	
	}
	
	