var	url=window.location.protocol+"/rest_area";
var lastIndex=0;
var lastIndexBoolean=false;
var listSort;
var routeName;
var destination;
var orderBy;

function top_arrow_fadeout() {
	if ($(this).scrollTop() > 200) {
		$('#top_arrow').fadeIn();
	} else {
		$('#top_arrow').fadeOut();
	}
}

function gototop() {
	$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
	return false;
}

function scrollInfinteList(){
	//	/{listSort}/{routeName}/{destination}/{orderBy}/{numOfRows}/{lastIndex}
	var path='/restarea/'+listSort+"/"+routeName+"/"+destination+"/"+orderBy+"/1/"+lastIndex;
	if(lastIndexBoolean){
		return false;
		}
	lastIndexBoolean=true;
	lastIndex++;
	$.ajax({
		url:BASE_URL+path,
		type:'GET',
		cache:'false',
		success:function(data,status){ 
			if(status=="success"){
				
				if(data.count==0){
				}
				else if(listSort=="ra"){
					pageListReLoadToRA(data);					
				}else if(listSort=="gs"){
					pageListReLoadToGS(data);
				}else if(listSort=="fm"){
					pageListReLoadToFM(data);
				}
			}
		}
	});//ajax 끝
}

function inputValueChk(){
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
}
function searchBtnClick(){
	inputValueChk();
	searchBtnClickLogic();
}
function searchBtnClickLogic(){
	lastIndex=0;

//	console.log('listSort : '+listSort+', routeName : '+routeName+', destination : '+destination+', orderBy : '+orderBy);
	
	//{listSort}/{routeName}/{destination}/{orderBy}
	var path='/restarea/'+listSort+"/"+routeName+"/"+destination+"/"+orderBy;
	$.ajax({
		url:url+path,
		type:'GET',
		cache:'false',
		success:function(data,status){ 
			if(status=="success"){
				lastIndex+=data.count;
				$('#searchResultList').html("");
				if(data.count==0){
					applyWebPage("데이터가 없습니다!");
				}
				else if(listSort=="ra"){
					pageListReLoadToRA(data);					
				}else if(listSort=="gs"){
					pageListReLoadToGS(data);
				}else if(listSort=="fm"){
					pageListReLoadToFM(data);
				}
			}
		}
	});//ajax 끝
}

function pageListReLoadToRA(jsonObj){
	let str="";
	let row=jsonObj.list;
	for(i=0;i<row.length;i++){
		str+=
		'<a class="listelement" onclick="setPopup(\''+row[i].ra_code+'\')" href="javascript:void(0);" >'+
		'<p class="ra_code undisplay">'+row[i].ra_code+'</p>'+
		'	<div class="card">			'+
	  	'	<img src="..." alt="" />	'+
	  	'	<div class="card-body">		'+
	    '	<h5 class="card-title">'+row[i].ra_name+'</h5>'+
	    '	<p class="card-text">위도 : '+row[i].ra_xValue+', 경도 : '+row[i].ra_yValue+'</p>'+
		'	  </div>	'+
		'	</div>		'+
		'</a>			';
	}
	applyWebPage(str);
}
function pageListReLoadToGS(jsonObj){
	let str="";
	let row=jsonObj.list;
	for(i=0;i<row.length;i++){
		str+=
		'<a class="listelement" onclick="setPopup(\''+row[i].ra_code+'\')" href="javascript:void(0);" >	'+
		'<p class="ra_code undisplay">'+row[i].ra_code+'</p>'+
		'	<div class="card">				'+
		'	  <img src="..." alt="" />		'+
		'	  <div class="card-body">		'+
		'	  	<p class="card-text">'+row[i].ra_name+'</p>	'+
		'	    <h5 class="card-title">'+row[i].gs_name+'</h5>	'+
		'	    <p class="card-text">경유 가격 : '+row[i].gs_diesel+'원,  휘발유 가격 : '+row[i].gs_gasoline+'원,  LPG 가격 : '+row[i].gs_lpg+'원</p>	'+
		'	  </div>	'+
		'	</div>		'+
		'</a>			';
	}
	applyWebPage(str);
}
function pageListReLoadToFM(jsonObj){
	let str="";
	let row=jsonObj.list;
	for(i=0;i<row.length;i++){
		str+=
		'<a class="listelement" onclick="setPopup(\''+row[i].ra_code+'\')" href="javascript:void(0);" >			'+
		'<p class="ra_code undisplay">'+row[i].ra_code+'</p>'+
		'	<div class="card">						'+
		'	  <img src="..." alt="" />				'+
		'	  <div class="card-body">				'+
		'	  	<p class="card-text">'+row[i].ra_name+'</p>		'+
		'	    <h5 class="card-title">'+row[i].fm_name+'</h5>	'+
		'	    <p class="card-text">가격 : '+row[i].fm_price+'</p>'+
		'  	    <p class="card-text">재료 : '+row[i].fm_material+'</p>	'+
		'   	    <p class="card-text">'+row[i].fm_etc+'</p>'+
		'	  </div>	'+
		'	</div>		'+
		'</a>			';
	}
	applyWebPage(str);
}
function applyWebPage(str){
	$('#searchResultList').append(str);
	lastIndexBoolean=false;
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
	$('#destination').val("");

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
