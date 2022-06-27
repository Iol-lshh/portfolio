var pageno = 1; 
var pagenationPage = 10; 

$(document).ready(function(){
    pageLoad(pageno)
    $(".viewList").hide();
    $("#writeNotices").hide();
    $("#updateNotices").hide();
})


  
function pageLoad(pageno){
        $.ajax({
            url : "./notice/" + pageno + "/" + pagenationPage,
            Type : "GET",
           /* headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },

            */
            dataType : "JSON",
            contentType:'application/json;',
            success : function(data, status){
                if(data.status=="OK"){
                    viewData(data);
                }
            }
        });
}// end pageLoad


function viewData(JsonObj){
        var result = "";
        var datas = JsonObj.list;
        var count = JsonObj.count;
        var i = 0 ;
        for (i; i<count; i++){
            result += "<tr> \n";
            result += "<th  >" + datas[i].notice_id; + "</th> \n";
            result += "<th colspan='3' id='btns' data-age='"+datas[i].notice_id+"' class='titles_notice'>" + datas[i].notice_subject; + "</th> \n"; 
            result += "<th >" + datas[i].notice_regdate; + "</th> \n";
            result += "<th >" + datas[i].notice_writer; + "</th> \n";
            result += "</tr> \n";
        }

        $("#list tbody").html(result);
         //페이징 정보 업데이트 
        var pagination = pageinateions(JsonObj.writePages, JsonObj.totalPage, JsonObj.pageNo, JsonObj.pagenationPage);
        $("#pagination").html(pagination);
        
        
        $("#list #btns").on("click",function(){
        	var data = $(this).attr('data-age');
        	view(data)
    	})
    	
    	
    	
        
}//end view Data


function pageinateions(writepages, totalPage, curPage, pageRows){
  
        
        var str ="";
        var start_page = ((parseInt( curPage -1 ) / writepages ) ) * writepages + 1;
        var end_page = start_page + writepages -1;

        if(end_page >= totalPage){
            end_page = totalPage;
        }

        if(curPage > 1){
            str += "<li><a onclick='pageLoad(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
        }
        
        if (start_page > 1) 
            str += "<li><a onclick='pageLoad(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
        
        if (totalPage > 1) {
            for (var k = start_page; k <= end_page; k++) {
                if (curPage != k)
                    str += "<li><a onclick='pageLoad(" + k + ")'>" + k + "</a></li>\n";
                else
                    str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
            }
        }
        
        if (totalPage > end_page){
            str += "<li><a onclick='pageLoad(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
        }
     
        if (curPage < totalPage) {
            str += "<li><a onclick='pageLoad(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
        }
     
        return str;


}// end pageNation



//view (update, delete)
function view(id){
	$("#list").hide();
	$(".viewList").show();
	$.ajax({
		url : "./notice/" + id,
		data : "GET",
		 dataType : "JSON",
         contentType:'application/json;',
         success : function(data, status){
                if(data.status=="OK"){
                    var notice_id = data.list[0].notice_id ;
                    var notice_subject = data.list[0].notice_subject;
                    var content_D = data.resultData;
                        $(".viewTitle").text(data.list[0].notice_subject);
                        $(".viewNo").text("[" + notice_id+ "]");
                        $("#viewDate").text("[" +data.list[0].notice_regdate + "]");
                        $("#viewId").text(data.list[0].notice_writer + " 관리자 님");
                        $(".viewContent").html(data.resultData);
                        

                    var ids = $("#hidens").val();
                    if(data.list[0].notice_writer == ids){
                        $("#updatesBtn").attr("class","d-lineblock btn btn-outline-info ");
                        $("#deleteBtn").attr("class","d-lineblock btn btn-outline-danger");
                        $("#updatesBtn").click(function(){
                            $(".viewList").hide();
                            $("#writeNotices").hide();
                            $("#updateNotices").show();
                            $("input:text").val(notice_subject);
                            CKEDITOR.instances["editor2"].setData(content_D);
                        
                        })
                        //update
                        $("#updateGo").click(function(){
                            
                            var title = $("#title2").val();
                            if(title =="" || title == null){
                                alert("제목이 비었습니다")
                                $("#title2").focus();
                                return false;
                        
                            }
                        
                            var maxlength = 20;
                            if($("#title2").val().length > maxlength){
                                alert("글씨가 너무 많습니다.");
                                return false;
                            }
                        
                            var data = CKEDITOR.instances["editor2"].getData();
                            if(CKEDITOR.instances["editor2"].getData().length < 1){
                                alert("내용을 입력해 주세요.");
                                return false;
                            }


                            var chen = CKEDITOR.instances["editor2"].getData(content_D);
                            var jsondata = {
                                title : $("#title2").val(),
                                n_id : notice_id,
                                changeD : chen
                            }
                        
                        
                            $.ajax({
                                url : "updateNotice",
                                type : "POST",
                                cashe : false,
                                data : JSON.stringify(jsondata),
                                headers: {
                                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                                },
                                contentType:'application/json;',
                                success : function(data,status){
                                    if(data.status== "OK"){
                                        alert("수정이 완료되었습니다.");
                                        location.reload();
                                        $("#list").hide();
                                        $("#writeNotices").show();
                                    }else{
                                        alert("수정 오류");
                                    }
                                } // end succ
                        
                             })// end ajax
                         
                        })


                        //delete
                        $("#deleteBtn").click(function(){
                                 
                            var jsondata = {
                                n_id : notice_id
                            }

                            $.ajax({
                                url : "DeleteNotice",
                                type : "delete",
                                data : JSON.stringify(jsondata),
                                cashe : false,
                                headers: {
                                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                                },
                                contentType:'application/json;',
                                success : function(data,status){
                                    if(data.status=="OK"){
                                        alert("삭제되었습니다.")
                                        location.reload();
                                    }
                                }


                            })

                        })



                    
                    }// end 작성자 일때 
                }//end success
        }
    });
}



//write
function writeNotice(){
    $("#list").hide();
    $("#writeNotices").show();
    
}


function writeOk(){
    var title = $("#title").val();
    if(title =="" || title == null){
        alert("제목이 비었습니다")
        $("#title").focus();
        return false;

    }

    var maxlength = 20;
    if($("#title").val().length > maxlength){
        alert("글씨가 너무 많습니다.");
        return false;
    }

    var data = CKEDITOR.instances["editor1"].getData();
    if(CKEDITOR.instances["editor1"].getData().length < 1){
        alert("내용을 입력해 주세요.");
        return false;
    }


    Date.prototype.yyyymmdd = function() {
        var mm = this.getMonth() + 1;
        var dd = this.getDate();
      
        return [this.getFullYear(),
                (mm>9 ? '' : '0') + mm,
                (dd>9 ? '' : '0') + dd
               ].join('');
     };

     Date.prototype.hhmmss = function() {
        var hh = this.getHours();
        var mm = this.getMinutes();
        var ss = this.getSeconds();
      
        return [(hh>9 ? '' : '0') + hh,
                (mm>9 ? '' : '0') + mm,
                (ss>9 ? '' : '0') + ss,
               ].join('');
      };

    Date.prototype.yyyymmddhhmmss = function() {
        return this.yyyymmdd() + this.hhmmss();
      };
      
    var date = new Date();

    
    var jsondata = {
        title : $("#title").val(),
        content :  data, 
        id : $("#hidens").val(),
        dataSet :  date.yyyymmddhhmmss()
    }


    $.ajax({
        url : "insertNotice",
        type : "POST",
        cashe : false,
        data : JSON.stringify(jsondata),
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        contentType:'application/json;',
        success : function(data,status){
            if(data.status== "OK"){
                alert("작성이 완료되었습니다.");
                location.reload();
                $("#list").hide();
                $("#writeNotices").show();
            }else{
                alert("작성 오류");
            }
        }

    })
    
}


/* modal 

*/
function noticeModal(){
    $('a[href="#ex1"]').show(function(event) {
        $(this).modal({
          fadeDuration: 250
        });
      });
}

function modalClose(){
    $.modal.close();
}