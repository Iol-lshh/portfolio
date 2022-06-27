/*fm 펼치기 버튼 토글*/
function fmbox_toggle(){
    if($('#fmbox>button').html()=='펼치기'){
        $('#inner_fmbox').css('max-height','');
		$('#fmbox>button').html('접기')
    }else{
		$('#inner_fmbox').css('max-height','300px');
		$('#fmbox>button').html('펼치기')
    }
}

/*closeModal 버튼 */
function closeModal(){
	$.when($('#fmbox>button').html('접기')).done(function(){fmbox_toggle();});
	post_cancle();//post 초기화
	$('.like_btn').removeClass('like');//like 버튼 초기화
	$('#restareaview').hide();
}

/*팝업*/
function setPopup(ra_code){
	
    //ra_code로 리스트를 ajax로 받아, 파싱, 리스트로 모달로 띄워준다
    $.ajax({
        url:BASE_URL+"/board/"+ra_code,
        type:"GET",
        cache:false,
        success:function(data,status){
            if(status=="success"){
                setView(data);
            }
        }

    });
    
}

function setView(jsonObj){
    rabox_str='<h1>'+jsonObj.obj.raDTO.ra_name+'</h1>'+
	'<span><button id="ra_like_btn" class="like_btn" onclick="like_btn(\'ra\', \''+jsonObj.obj.raDTO.ra_code+'\')"><image src="'+BASE_URL+'/resources/img/like.png"></image></button> <span id="ra_like_cnt_text">'+jsonObj.obj.raDTO.ra_like_cnt+'</span></span>'+
    '<p>노선 : '+jsonObj.obj.raDTO.ra_routeName+
    ' <br>방향 : '+jsonObj.obj.raDTO.ra_destination+
    ' <br>위치(경도,위도) : '+jsonObj.obj.raDTO.ra_xValue+', '+jsonObj.obj.raDTO.ra_yValue+
    '</p>';
    
    gsbox_str='';
    row=jsonObj.obj.gsDTOs;
    for(i=0;i<row.length;i++){
        gsbox_str+='<h4>'+row[i].gs_name+'</h4>'+
	'<span id="gs_code_span" class="gs_id_span undisplay">'+row[i].gs_code+'</span>'+
	'<span><button id="gs_like_btn" class="like_btn" onclick="like_btn(\'gs\', \''+row[i].gs_code+'\')"><image src="'+BASE_URL+'/resources/img/like.png"></image></button> <span id="gs_like_cnt_text">'+row[i].gs_like_cnt+'</span></span>'+
    '    <p>휘발유 가격 : '+row[i].gs_gasoline+
    '       <br>경유 가격 : '+row[i].gs_diesel+
    '       <br>LPG 가격 : '+row[i].gs_lpg+
    '    </p>';
    }
    
    fmbox_str='';
    row=jsonObj.obj.fmDTOs;
    for(i=0;i<row.length;i++){
        fmbox_str+='<li>'+
    '    <p>'+row[i].fm_name+'</p>'+
	'<span id="fm_id_span_'+row[i].fm_id+'" class="fm_id_span undisplay">'+row[i].fm_id+'</span>'+
	'<span><button id="fm_like_btn_'+row[i].fm_id+'" class="like_btn" onclick="like_btn(\'fm\', \''+row[i].fm_id+'\')"><image src="'+BASE_URL+'/resources/img/like.png"></image></button> <span id="fm_like_cnt_text_'+row[i].fm_id+'">'+row[i].fm_like_cnt+'</span></span>'+
    '    <p>소비자가 : '+row[i].fm_price+'원'+
    '    <br>재료 : '+row[i].fm_material+
    '    <br>'+row[i].fm_etc+'</p>'+
    '</li><br>';
    }
	$('#ra_code').html(jsonObj.obj.raDTO.ra_code);
    $('#rabox').html(rabox_str);
    $('#gsbox').html(gsbox_str);
    $('#fmbox ul').html(fmbox_str);

    
	//위치 : postboard.js
	ra_code=jsonObj.obj.raDTO.ra_code;
	orderBy='default';
	postboard_refresh();
	
	
	place={x:jsonObj.obj.raDTO.ra_xValue,y:jsonObj.obj.raDTO.ra_yValue,place_name:jsonObj.obj.raDTO.ra_name};
	
	$('#restareaview').show();
	kakaoMap(place.y,place.x);
	displayMarker(place);
	panTo(place);
	chk_all_your_like();
}

/* 카카오 맵 */
var kakao_map=null;

//지도 생성
function kakaoMap(yValue,xValue){
	var container = document.getElementById('kakao_map');
	var options = {
		center: new kakao.maps.LatLng(yValue,xValue),
		level: 5
	};
	if(kakao_map==null){
		kakao_map=new kakao.maps.Map(container, options);
	}
}


// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: kakao_map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(kakao_map, marker);
    });
}

function panTo(place) {
	kakao_map.setLevel(5);
    // 이동할 위도 경도 위치를 생성합니다  
    let moveLatLon = new kakao.maps.LatLng(place.y, place.x);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    kakao_map.panTo(moveLatLon);          
}       

/* like_btn */
function like_btn(kind, kind_id){
	//토글 작업 => 해당 좋아요 테이블 조회 => 1.조회결과없으면 생성 2.조회결과있으면 삭제 => 해당 {kind}_like_cnt 값을 다시 받음. 초기화 할 것..
	let LikeVO={
		um_uid:$('header #header_um_uid').html(),
		kind:kind,
		kind_id:kind_id
	};
	$.ajax({
		url:BASE_URL+'/board/like',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'PATCH',
		dataType:'JSON',
		contentType:'application/json',
		data:JSON.stringify(LikeVO),
		cache:false,
		success:function(data){
			if(LikeVO.kind=='ra'){
				$('#ra_like_cnt_text').html(data.obj)
				if($('#ra_like_btn').hasClass('like')){
					$('#ra_like_btn').removeClass('like');
				}else{
					$('#ra_like_btn').addClass('like');
				}
			}else if(LikeVO.kind=='gs'){
				$('#gs_like_cnt_text').html(data.obj)
				if($('#gs_like_btn').hasClass('like')){
					$('#gs_like_btn').removeClass('like');
				}else{
					$('#gs_like_btn').addClass('like');
				}
			}else if(LikeVO.kind=='fm'){
				$('#fm_like_cnt_text_'+LikeVO.kind_id).html(data.obj)
				if($('#fm_like_btn_'+LikeVO.kind_id).hasClass('like')){
					$('#fm_like_btn_'+LikeVO.kind_id).removeClass('like');
				}else{
					$('#fm_like_btn_'+LikeVO.kind_id).addClass('like');
				}
			}else if(LikeVO.kind=='post'){
				$('#post_like_cnt_text_'+LikeVO.kind_id).html(data.obj)
				if($('#post_like_btn_'+LikeVO.kind_id).hasClass('like')){
					$('#post_like_btn_'+LikeVO.kind_id).removeClass('like');
				}else{
					$('#post_like_btn_'+LikeVO.kind_id).addClass('like');
				}
			}
		}
	});	
}

//view 첫 활성화시 실행용 - like 작업-이렇게하면 이부분은 restful이 아니여....ㅠㅠ
function chk_all_your_like(){
	$('.like_btn').removeClass('like');//일단 초기화
	//kind , kind_id 받기 => 요청 => um_id와 kind_id가 일치하는 요소 확인 => 있으면 빨갛게
	//ra_code에 해당하는 ra_like, gs_like, fm_like, post_like에 내 like를 조회
	chk_your_like('ra',$('#restareaview p#ra_code').html());//휴게소 좋아요 작업
	chk_your_like('gs',$('#restareaview #gs_code_span').html());//주유소 좋아요 작업

	//음식 좋아요 작업
	$('.fm_id_span').each(function(index,item){
		chk_your_like('fm',$(item).html());
	});
}

//like 첫 확인용 -post_view에서 직접 활용=>다음엔 ajax용 함수를 따로 만들어주자...후........
function chk_your_like(kind,kind_id){
	let um_uid=$('header #header_um_uid').html();
	
	let LikeVO={
		um_uid:$('header #header_um_uid').html(),
		kind:kind,
		kind_id:kind_id
	};
	$.ajax({
		url:BASE_URL+'/board/like',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'POST',
		dataType:'JSON',
		contentType:'application/json',
		data:JSON.stringify(LikeVO),
		cache:false,
		success:function(data){
			if(data.obj==0){return;}
			//페이지 파싱
			let btnstr='#'+LikeVO.kind+'_like_btn';
		//	let cntstr='#'+kind+'_like_cnt_text';
			if(LikeVO.kind=="fm"||LikeVO.kind=="post"){
				btnstr+='_'+LikeVO.kind_id;
		//		cntstr+='_'+kind_id;
			}
			
			$(btnstr).addClass('like');
		}
	});
	
	

}