/*
 * sql명령어가 마지막 포스트의 post_id 미만의 게시글만 가져온다!
 */



//버튼을 누른다		  	=>ajax=>리스트를 비운다=>리스트를 채운다=>두개의 버튼색이 바뀐다(토글)
//꺽쇄 버튼=>+10 한다     	=>
//쌍꺽쇄    =>맨 끝으로 간다	=>
//맨 처음  : 이전 꺽쇄,쌍꺽쇄가 비활성화
//맨 마지만 : 다음 꺽쇄,쌍꺽쇄가 비활성화
//html을 써준다. (각 리스트 새로 생성)

//리스트 비우고, 만드는 총괄 함수
function mkLists(tag, jsonObj){
	$("#mypostboard .board-contents").empty();
	var data=jsonObj.data;
	console.log(jsonObj);
	for(var i=0;i<6;i++){
		const dto=data[i];
		$("#mypostboard .board-contents").append(mkList(dto.post_id,dto.title,dto.regdate));
		$("#mypostboard .board-contents").append("<hr>");
	}
}

//리스트 만듦(각각 한개씩)
function mkList(post_id,title,regdate){
	var card_list=$('<a id="whole_'+post_id+'" class="card_post article" href="../post/view.do?post_id='+post_id+'"></a>').html(
			'		   <div class="card_post-main"><p class="post_id" style="display:none">'+post_id +'</p><p class="sm_card_list-title title">'+title+'</p>'+
			'				<div class="sm_card_list-metadata"><time class="sm_card_list-regdate small">'+regdate+'</time></div>'+
			'		</a>');
	return card_list;
//	<a class="sm_card_list list" href="../post/view.do?post_id=${post_id }">
//	<p class="sm_card_list-title title">${param.title }</p>
//	<div class="sm_card_list-metadata"><time class="sm_card_list-regdate small">${param.regdate }</time></div>
//	</a>
}

function mkPagenation(count,clicked){
	$("#mypostboard ul#pagination").empty();

	$("#mypostboard ul#pagination").append('<li id="page_first" class="page_btn">&lt&lt</li><li id="page_before" class="page_btn">&lt</li>');	
	if(clicked==0){
		$("#mypostboard li#page_first").addClass('disable');
		$("#mypostboard li#page_before").addClass('disable');
	}
	for(const i=0 ;i<count;i++){
		if(i==clicked){//해당 페이지이면, chked 클래스 적용
			$("#mypostboard ul#pagination").append('<li id="page_${i }" class="page_btn chked">${i }</li>');
			continue;
		}
		$("#mypostboard ul#pagination").append('<li id="page_${i }" class="page_btn">${i }</li>');
	}
	$("ul#pagination").append('<li id="page_after" class="page_btn">&gt</li><li id="page_last" class="page_btn">&gt&gt</li>');
	if(clicked==0){
		$("#mypostboard li#page_last").addClass('disable');
		$("#mypostboard li#page_after").addClass('disable');
	}
}

//카운트를 매개변수로 받아,
//꺽쇄 비활성화, 카운트 숫자 계산하여 페이지네이션 숫자 생성
//10개(글이 10*10) 초과시, 다음 꺽쇄 활성화
//기본 꺽쇄는 만들어주기

//해당 버튼을 누르면 ajax에서 자바스크립트를 통해, 해당 버튼의 숫자에 해당하는 리스트로 갱신

$(document).ready(function(){

	//first
	$("#page_first.page_btn").click(function(){
		//ajax request를 보냄
		url="mypage_mypost.ajax?reqType=json&btn_id=1";
		sendByAJAX(url);
	});//end - $("#page_first.page_btn").click
	
	//before
	$("#page_before.page_btn").click(function(){
		//ajax request를 보냄
		url="mypage_mypost.ajax?reqType=json&btn_id=";
		sendByAJAX(url);
	});//end - $("#page_before.page_btn").click
	
	//after
	$("#page_after.page_btn").click(function(){
		//ajax request를 보냄
		url="mypage_mypost.ajax?reqType=json&btn_id=";
		sendByAJAX(url);
	});//end - $("#page_after.page_btn").click
	
	//last
	$("#page_last.page_btn").click(function(){
		//ajax request를 보냄
		url="mypage_mypost.ajax?reqType=json&btn_id=";
		sendByAJAX(url);
	});//end - $("#page_last.page_btn").click
	
	//else
	$(".page_btn.page_num_btn").click(function(){
		url="mypage_mypost.ajax?reqType=json&btn_id="+$(this).val();
		sendByAJAX(url);
	});
	
})//end - $(document).ready


//받은 JSON객체를 이용하여 post 추가
function parseJSON(jsonObj){
	console.log("json:"+jsonObj);
	mkLists("", jsonObj);
}

//각 페이지 버튼마다, ajax 실행 함수 실행!
//command_url은 mypage_mypost.ajax  mypage_mycomment.ajax  mypage_myempathize.ajax
function sendByAJAX(command_url ){
	//dao에 들어갈 값을 btn_id를 가공해서넣는다~!
	url=command_url;
	$.ajax({
		url:url,
		type:"GET",
		cache:false,
		success:function(data,status){
			if(status == "success")
				parseJSON(data);
		}
	});
};