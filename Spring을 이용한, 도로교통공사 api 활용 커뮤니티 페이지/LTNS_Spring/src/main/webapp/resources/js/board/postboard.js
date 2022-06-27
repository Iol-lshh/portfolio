/* post list */
var ra_code;
var orderBy;
var pageNo=1;
var pagenationPage=10;
var PAGENATION_URL_STR='';
//ajax 요청
function postboard_ajax(){
	$.ajax({
		url:BASE_URL+'/board/post/list/'+ra_code+'/'+orderBy+'/'+PAGENATION_URL_STR,
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'POST',
		dataType:'JSON',
		contentType:'application/json',
		cache:false,
		success:function(data,status){
			setPostboard(data.list);
			let tmp_str=buildPagination(data.writePages, data.totalPage, pageNo, data.pagenationPage);
			$('#post_pagination').html(tmp_str);
		}
	});
}

//post list 초기화 
function postboard_refresh(){
	postboard_ajax();
}


/* 회원 확인 */
function chk_usermember(){
	if($('header #header_um_uid').html()==null){
		$('#btn_open_post_new').hide();
	}
}
/* post write */

//글작성 클릭 시
function open_post_new(){
    $('#btn_open_post_new').hide();
    $('#postwrite_new').show();
}

function post_new(){
	var PostVO={
		//post_id:'',
		post_title:$('.write_post_title').val(),
		post_contents:$('.write_post_content').val(),
		um_uid:$('header #header_um_uid').html(),
		um_username:$('header #header_um_username').html(),
		//post_regdate:'',
		ra_code:$('#restareaview p#ra_code').html()
		//post_reported:''
	};
	console.log(PostVO);
	//post 전송
	$.ajax({
		url:BASE_URL+'/board/post',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'PUT',
		dataType:'JSON',
		contentType:'application/json',
		data:JSON.stringify(PostVO),
		cache:false,
		success:function(data){
		    $('#btn_open_post_new').show();
    		$('#postwrite_new').hide();
			ra_code=$('#restareaview p#ra_code').html();
			orderBy='default';
			postview_refresh();
			postboard_refresh();	
		}
	});
}

function postview_refresh(){
    $('.write_post_title').val('');
    $('.write_post_content').val('');
}

function post_cancle(){
    postview_refresh();
    $('#postwrite_new').hide();
	$('#btn_open_post_new').show();
}

function post_update(post_id){
	var PostVO={
		post_id:post_id,
		post_contents:$('#postwrite_update textarea').val(),
		um_uid:$('header #header_um_uid').html(),
		um_username:$('header #header_um_username').html(),
		//post_regdate:'',
		ra_code:$('#restareaview p#ra_code').html()
		//post_reported:''
	};
	console.log(PostVO);
	//post 전송
	$.ajax({
		url:BASE_URL+'/board/post',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'PATCH',
		dataType:'JSON',
		contentType:'application/json',
		data:JSON.stringify(PostVO),
		cache:false,
		success:function(data){
		    $('#btn_open_post_new').show();
    		$('#postwrite_new').hide();
			ra_code=$('#restareaview p#ra_code').html();
			orderBy='default';
			postview_refresh();
			postboard_refresh();	
		}
	});
}
//delete
function post_delete(post_id){
	var PostVO={
		post_id:post_id,
//		post_contents:$('#postwrite_update textarea').val(),
		um_uid:$('header #header_um_uid').html(),
		um_username:$('header #header_um_username').html(),
		//post_regdate:'',
		ra_code:$('#restareaview p#ra_code').html()
		//post_reported:''
	};
	console.log(PostVO);
	//post 전송
	$.ajax({
		url:BASE_URL+'/board/post',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
		type:'DELETE',
		dataType:'JSON',
		contentType:'application/json',
		data:JSON.stringify(PostVO),
		cache:false,
		success:function(data){
		    $('#btn_open_post_new').show();
    		$('#postwrite_new').hide();
			ra_code=$('#restareaview p#ra_code').html();
			orderBy='default';
			postview_refresh();
			postboard_refresh();	
		}
	});
}
//textarea 글씨 갯수 검사
function post_chkTextlimit(textarea,limitChar){
    let charlength=textarea.value.length;
    if(charlength>=limitChar){
        let str=textarea.value.substr(0,limitChar);
        textarea.value=str;
    }
    $('#post_textLength').html(charlength);
}

/*post view*/
function postView(post_id){
	if(post_id==$('#postbody_post_id').html()){
	    $('tr.postbody').remove();
		return;
	}
    //기존 body가 있다면 지운다.
    $('tr.postbody').remove();
    //post_id의 ajax를 요청한다.
    $.ajax({
        url:BASE_URL+"/board/post/view/"+post_id,
        type:'POST',
		headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
        cache:false,
        success:function(data,status){
            if(status=="success"){
                postBody(data);
            }
        }
    });
}

//read
function postBody(jsonObj){
    //해당 post_id의 head 바로 아래 body를 만들어 ajax로 받아 내용을 띄워준다.
    str='<tr class="postbody"><td colspan="7">'+
		'<div id="postbody_post_id" class="undisplay">'+jsonObj.obj.post_id+'</div>'+
	    '<div class="post_content">'+
	    jsonObj.obj.post_contents.replaceAll('\n','<br>')+
	    '</div>'+
		'<span><button id="post_like_btn_'+jsonObj.obj.post_id+'" class="like_btn" onclick="like_btn(\'post\',\''+jsonObj.obj.post_id+'\')"><image src="'+BASE_URL+'/resources/img/like.png"></image></button> <span id="post_like_cnt_text_'+jsonObj.obj.post_id+'">'+jsonObj.obj.post_like_cnt+'</span></span>';
	if($('header #header_um_uid').html()==jsonObj.obj.um_uid){
		str+='<button id="post_deletebtn" class="btn right" onclick="post_delete('+jsonObj.obj.post_id+')">삭제</button>'+
	    '<button id="post_updatebtn" class="btn right" onclick="postBody_update()">수정</button>';
	}
	str+='<br></td></tr>';
    $('tr#posthead_'+jsonObj.obj.post_id).after(str);
	
	chk_your_like('post',jsonObj.obj.post_id);
}

//update
function postBody_update(){
	let tmpstr=$('.post_content').html().replaceAll('<br>','\n');
	console.log(tmpstr);
	str='<div id="postwrite_update" class="postbody">'+
		'<div id="postbody_post_id" class="undisplay">'+$('#postbody_post_id').html()+'</div>'+
		'	<form id="postwrite" name="post" method="post">'+
		'	    <div class="post_content">'+
		'	       	<textarea class="write_post_content" name="post_content" onkeyup="post_chkTextlimit(this,\'500\')" value="" placeholder="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다. 건전한 토론문화와 양질의 댓글 문화를 위해, 타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족, 종교 등을 비하하는 단어들은 표시가 제한됩니다.">'+tmpstr+'</textarea>'+
		'            <span class="text-muted right">/500</span> '+
		'            <span id="post_textLength" class="text-muted right">0</span>'+
		'            <div class="clear"></div>'+
		'	    </div>'+
		'	    <button id="post_deletebtn" class="btn danger right" onclick="post_update('+$('#postbody_post_id').html()+'); return false;">완료</button>'+
		'	    <button id="post_updatebtn" class="btn info right" onclick="$(\'tr.postbody\').remove(); return false;">취소</button>'+
		'    	<div class="clear"></div>'+
		'    </form>'+
		'</div> ';
	$('tr.postbody>td').html(str);
}



function setPostboard(jsonObj){
    postboard_str='';
    for(i=0;i<jsonObj.length;i++){
        postboard_str+='<tr id="posthead_'+jsonObj[i].post_id+'">'+
        '<td class="ra_code undisplay">'+jsonObj[i].ra_code+'</td>'+
        '<td class="post_id undisplay">'+jsonObj[i].post_id+'</td>'+
        '<td class="post_title"><a class="post_element" onclick="postView('+jsonObj[i].post_id+')" href="javascript:void(0);">'+jsonObj[i].post_title+'</a></td>'+
        '<td class="um_username">'+jsonObj[i].um_username+'</td>'+
        '<td class="post_regdate">'+jsonObj[i].post_regdate+'</td>'+
		'<td class="post_like_cnt">'+jsonObj[i].post_like_cnt+'</td>'+
        '<td class="post_reported"><button class="reported_btn" onclick="textPopup('+jsonObj[i].post_id+');"><image src="'+BASE_URL+'/resources/img/redalert.png"></button></td>'+
    '</tr>';
    }
    $('#post_frmlist tbody').html(postboard_str);
    
}

/*페이지네이션*/
function buildPagination(writePages, totalPage, curPage, pageRows){
	var str = "";   // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성
	
	// 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
    var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
    var end_page = start_page + writePages - 1;

    if (end_page >= totalPage){
    	end_page = totalPage;
    }
    
  //■ << 표시 여부
	if(curPage > 1){
		str += "<li><a onclick='loadPage(" + 1 + ")' class='tooltip-top' title='처음'><image src='"+BASE_URL+"/resources/img/chevron_double_left_icon_143629.png'></image></a></li>\n";
	}
	
  	//■  < 표시 여부
    if (start_page > 1) 
    	str += "<li><a onclick='loadPage(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><image src='"+BASE_URL+"/resources/img/chevron_left_icon_143625.png'></image></a></li>\n";
    
    //■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
	    for (var k = start_page; k <= end_page; k++) {
	        if (curPage != k)
	            str += "<li><a onclick='loadPage(" + k + ")'>" + k + "</a></li>\n";
	        else
	            str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
	    }
	}
	
	//■ > 표시
    if (totalPage > end_page){
    	str += "<li><a onclick='loadPage(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><image src='"+BASE_URL+"/resources/img/chevron_right_icon_143624.png'></image></a></li>\n";
    }

	//■ >> 표시
    if (curPage < totalPage) {
        str += "<li><a onclick='loadPage(" + totalPage + ")' class='tooltip-top' title='맨끝'><image src='"+BASE_URL+"/resources/img/chevron_double_right_icon_143628.png'></image></a></li>\n";
    }

    return str;
} // end buildPagination

function loadPage(page){
	pageNo=page;
	PAGENATION_URL_STR='10/'+((pageNo-1)*pagenationPage)+'/'+pageNo;
	postboard_ajax();
}



function textPopup(data){
    var url = "../../../../../admin/Report";
    var name = "ReportPopup";
    var option = "width = 800, height = 400 left = 400, top=100,location=no";
    var _chiled;
    $("#parent2").val(data);
    _chiled = window.open(url,name,option)
    
}