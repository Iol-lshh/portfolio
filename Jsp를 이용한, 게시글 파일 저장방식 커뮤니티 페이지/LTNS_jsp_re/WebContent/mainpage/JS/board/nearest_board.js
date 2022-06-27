/**
 * sql명령어가 마지막 포스트의 post_id 이상의 게시글만 가져온다!
 */
$(document).ready(function(){

	$("button#nearest_board-add_post").click(function(){
		//ajax request를 보냄
		url="mainpage.ajax?reqType=json&regdate="+$("time.card_post-regdate").last().html()+"&post_id="+$('p.post_id').last().html();
		$.ajax({
			url:url,
			type:"GET",
			cache:false,
			success:function(data,status){
				if(status == "success")
					parseJSON(data);
			}
		});	
		
	})//end - $("button#nearest_board-add_post").click
	
	$(window).scroll( function () {
		if ( $(this).scrollTop() > 200 ) {
			$( '#top_arrow' ).fadeIn();
		} else {
			$( '#top_arrow' ).fadeOut();
		}
	})
	
	$( '#top_arrow' ).click( function() {
		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
		return false;
	} );
	
})//end - $(document).ready


//받은 JSON객체를 이용하여 post 추가
function parseJSON(jsonObj){
	var data=jsonObj.data;
	let card_post = '';
	var post_i=$("a.card_post").last().attr("id");
	post_i=Number(post_i.slice(6));
	for(let i=0;i<3;i++){
		//게시글을 카운트 i=다음 인덱스가 된다!
		var temp_post_id=data[i].post_id;
		var temp_title=data[i].post_contents.title;
		var temp_picture=data[i].post_contents.thumbnailPath;
		var temp_writer=data[i].nickname;
		var temp_regdate=data[i].regdate;
		var temp_contents=data[i].post_contents.contentsText;
		post_i=post_i+1;
		card_post += '<a id="whole_'+post_i+'" class="card_post article" href="../post/view.do?post_id='+temp_post_id+'">'+
				'	<div class="card_post-picture"><img src="'+temp_picture+'"/></div>'+
				'	<div class="card_post-main">'+
				'		<p class="post_id" style="display:none">'+temp_post_id +'</p>'+
				'		<p class="card_post-title title">'+temp_title+'</p>'+
				'		<div class="sm_card_list-metadata"><p class="card_post-writer small">'+temp_writer+'</p><time class="card_post-regdate small">'+temp_regdate+'</time></div>'+
				'		<p class="card_post-contents small">'+temp_contents+'</p>'+
				'	</div></a><hr>';
		
//		$("div#nearest_board").append(card_post).hide();
//		$("div#nearest_board").append("<hr>");
	}
	$(card_post).hide().appendTo('div#nearest_board').fadeIn(1000);
}
