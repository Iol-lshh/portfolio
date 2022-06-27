

//crud 
function click_delete() {
		var url = "schdule/deletePopup";
		var name = "deletePopup";
		var option = "width = 800, height = 180 left = 200, top=150,location=no";
		window.open(url,name,option)

}; 


function click_add() {
	var url = "schdule/schedulePopup";
	var name = "schedulePopup";
	var option = "width = 600, height = 350 left = 200, top=150,location=no";
	window.open(url,name,option)
}; 


function click_update() {
	var url = "schdule/updatePopup";
	var name = "updatePopup";
	var option = "width = 600, height = 450 left = 200, top=150,location=no";
	window.open(url,name,option)
}; 



$(function () {
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',	//날짜 포맷
		showOtherMonths : true,		// 다른달 보여주기
		showMonthAfterYear : true,	// 년도가 월보다 먼저 표시
		changeYear : true, 	// 콤보박스로 년월일 선택
		changeMonth : true, 
		yearSuffix: "년", 
		monthNamesShort : ['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dateNamesMin : ['일','월','화','수','목','금','토'],
		dayNames : ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
	
	});  // 데이터피커 default 옵션 변경 
	
	
	$("#startDate").datepicker(); //시작일
	$("#endDate").datepicker(); // 끝일 
	
	$("#startDate").datepicker('setDate', 'today'); //시작일
	$("#endDate").datepicker('setDate', 'today'); 

	
	
	
})



//add schedule
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
    	var name = $.trim(this.name),
    		value = $.trim(this.value);
    	
        if (o[name]) {
            if (!o[name].push) {
                o[name] = [o[name]];
            }
            o[name].push(value || '');
        } else {
            o[name] = value || '';
        }
    });
    return o;
};

function click_ok(){
	
	var subject = $('#subject').val();
	if(subject == null || subject == ""){
		alert("제목이 비었습니다.");
		$('#subject').focus();
		return false; 
		
	}


	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();

	if( (new Date(startDate).getTime() - new Date(endDate).getTime()) > 0){
		alert("종료일은 시작일 이전으로는 할 수 없습니다.");
		return false;
	}
	var scheduleData = JSON.stringify($('form#scheduleData').serializeObject());
	var token =  $('input[name="csrfToken"]').attr('value'); 


	$.ajax({
		data : scheduleData,
		url : "schedule",
		type : "POST",
		dataType : "JSON",
		headers: {
			'X-CSRF-Token': token 
	    },
		contentType:'application/json;',
		success : function(data,status) {
			if(data.status == "OK"){
				alert("일정이 추가되었습니다.")
				opener.parent.location.reload();
				location.reload();
				window.close();
			}else{
				alert("에러 발생");
			}
		}
	});

	
};


function delete_ok(){
	var subject = $('#subject').val();
	if(subject == null || subject == ""){
		alert("제목이 비었습니다.");
		$('#subject').focus();
		return false; 
		
	}

	var token =  $('input[name="csrfToken"]').attr('value'); 
	var scheduleData = JSON.stringify($('form#deleteForm').serializeObject());
	$.ajax({
		data : scheduleData,
		url : "schedule",
		type : "DELETE",
		dataType : "JSON",
		// security token은 헤더에 실어서 보내야한다.
		headers: {
			'X-CSRF-Token': token 
	    },
		contentType:'application/json; charset=utf-8',
		success : function(data,status) {
			if(data.status =="OK"){
				alert("일정이 삭제되었습니다.")
				opener.parent.location.reload();
				location.reload();
				window.close();
			}else{
				alert("삭제할 일정이 없습니다.");
			}
		}
	});

	
};





function update_ok(){


	var add_subject = $('#add_subject').val();
	var subject = $('#subject').val();
	var token =  $('input[name="csrfToken"]').attr('value'); 
	if(add_subject == null || add_subject == ""){
		alert("기존 일정의 제목이 비었습니다.");
		$('#add_subject').focus();
		return false;
	}


	if(subject == null || subject == ""){
		alert("변경하실 일정의 제목이 비었습니다.");
		$('#subject').focus();
		return false; 
		
	}

	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();

	if( (new Date(startDate).getTime() - new Date(endDate).getTime()) > 0){
		alert("종료일은 시작일 이전으로는 할 수 없습니다.");
		return false;
	}







	var scheduleData = JSON.stringify($('form#updateForm').serializeObject());
	$.ajax({
		data : scheduleData,
		url : "schedule",
		type : "PUT",
		dataType : "JSON",
		contentType:'application/json;',
		headers: {
			'X-CSRF-Token': token 
	    },
		success : function(data,status) {
				if(data.status == "OK"){
					alert("일정이 수정되었습니다.")
					opener.parent.location.reload();
					location.reload();
					window.close();
				}else{
					alert("기존 일정의 제목이 없습니다.")
				}

		}
	});
	
};



  
 
 
 
