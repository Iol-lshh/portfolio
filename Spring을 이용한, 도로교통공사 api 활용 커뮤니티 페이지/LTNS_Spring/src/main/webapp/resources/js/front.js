var	BASE_URL="http://localhost:8089/rest_area";

$(document).ready(function(){
	
	/* 페이지 로딩시, 노선 입력에 노선 값들을 집어 넣기 */
	$.ajax({
		url:BASE_URL+"/restarea/routeName",
		type:'GET',
		cache:false,
		success:function(data,status){
			if(status=="success"){
				var list=data.list;
				var htmlval="";
				for(i=0;i<list.length;i++){
					htmlval+='<li><a class="dropdownRouteNameList" tabindex="-1" role="button" onclick="changeRouteNameValue(\''+list[i].ra_routeName+'\')">'+list[i].ra_routeName+'</a></li>';
				}
				$("#routeNameList").html(htmlval);
			}
		}
	});
	
})//end - $(document).ready

function searchBtnClickLogic(){
	listSort=$("#listSortBtn").attr('value');
	routeName=$("input[name=routeName]").val();
	destination=$("input[name=destination]").val();
	orderBy=$("#orderByRadio .custom-control-input:checked").val();

	if(!(routeName.length>0)||routeName==""){
		alert("노선 입력은 필수 입니다!");
		return false;
	}else if(!$('.dropdownRouteNameList').text().includes(routeName)){	
		alert("잘못된 노선 입력입니다.");
		$("input[name=routeName]").val("");		
		return false;
	}
	
	if(!(destination.length>0)||destination==""){
		destination='ALL';
	}else if(!$('.dropdownDestinationList').text().includes(destination)){
		alert("잘못된 방향 입력입니다.");
		$("input[name=destination]").val("");
		return false;
	}
	
		//{listSort}/{routeName}/{destination}/{orderBy}
	var value_url="/"+listSort+"/"+routeName+"/"+destination+"/"+orderBy;
	let path_url='./list'+value_url;
	location.replace(path_url);
}


function changeListSortValue(listSort){
	let sortval_str;
	let html_str;
	if(listSort=='휴게소'){
		sortval_str='ra';
		html_str=
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">'+
		'	<label class="custom-control-label" for="jb-radio-1">경로 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">'+
		'	<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>'+
		'</div>';
	}
	else if(listSort=='주유소'){
		sortval_str='gs';
		html_str=
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">'+
		'	<label class="custom-control-label" for="jb-radio-1">경로 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">'+
		'	<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_DIESEL">'+
		'	<label class="custom-control-label" for="jb-radio-3">경유 가격 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-4" class="custom-control-input" name="orderBy" value="BY_GASOLINE">'+
		'	<label class="custom-control-label" for="jb-radio-4">휘발유 가격 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-5" class="custom-control-input" name="orderBy" value="BY_LPG">'+
		'	<label class="custom-control-label" for="jb-radio-5">LPG 가격 순서</label>'+
		'</div>';
	}
	else if(listSort=='음식메뉴'){
		sortval_str='fm';
		html_str=
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-1" class="custom-control-input" name="orderBy" value="default" checked="checked">'+
		'	<label class="custom-control-label" for="jb-radio-1">경로 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-2" class="custom-control-input" name="orderBy" value="BY_LIKE">'+
		'	<label class="custom-control-label" for="jb-radio-2">좋아요 순서</label>'+
		'</div>'+
		'<div class="custom-control custom-radio">'+
		'	<input type="radio" name="jb-radio" id="jb-radio-3" class="custom-control-input" name="orderBy" value="BY_PRICE">'+
		'	<label class="custom-control-label" for="jb-radio-3">가격 순서</label>'+
		'</div>';
	} 
	$("#listSortBtn").attr('value',sortval_str);
	$("#listSortBtn").html(listSort);
	$("#orderByRadio").html(html_str);
}

function changeRouteNameValue(routeName){
	
	$('#routeName').val(routeName);

	$.ajax({
		url:BASE_URL+'/restarea/destination/'+routeName,
		type:'GET',
		cache:false,
		success:function(data,status){ 
			if(status=="success"){
				$('#destination').attr('value',null);
				var row=data.list;
				html_str="";
				for(i=0;i<row.length;i++){
					html_str+=' <li><a class="dropdownDestinationList" tabindex="-1" role="button" onclick="changeDestinationValue(\''+row[i].ra_destination+'\')">'+row[i].ra_destination+'</a></li>';
				}
				$("#destinationList").html(html_str);
			}
		}
	});//ajax 전체 노선 불러오기
	
	//리스트 깔기
	
}

function changeDestinationValue(destination){
	$('#destination').val(destination);
}
