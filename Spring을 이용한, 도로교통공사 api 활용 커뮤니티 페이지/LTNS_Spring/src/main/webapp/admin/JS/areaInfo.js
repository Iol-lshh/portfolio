var pageNo =1 ;
var pagenationPage = 10;
var pagination = "" ;
var gas = "";
var rest = "";
var food = "";
var length = $("#heads").children().length
$(document).ready(function() {
    
    pageLoad(pageNo);
  
})


function pageLoad(pageNo){
	$.ajax({
    	  url : "./areaInfo/" + pageNo + "/" + pagenationPage,
          type : "GET",
          cache : false,
          headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		 },
          contentType : 'application/json; charset=UTF-8',
          dataType : 'json',
      	  success : function(data, status){
            if(status == "success"){
                thead(data);
                if(updateList(data)){
                    popUp();
                }
                
            }
            
        }
    });
	
}

function thead(JsonObj){


    if(JsonObj.list[0].gs_LPG != null){
        var data = ['주유소 이름','주유소 코드', '경유','휘발류','LPG']
        $("thead").attr("class", 'bg-danger text-white')
    }else if(JsonObj.list[0].ra_ROUTENO != null){
        $("thead").attr("class", 'bg-info text-white')
        var data = ['휴게소 이름','휴게소 코드','고속도로 이름','휴게소 방향','고속도로 번호']
    }else{
        $("thead").attr("class", 'bg-success text-white')
        var data = ['휴게소 이름','휴게소 코드','메뉴 코드','메뉴 이름','메뉴 가격']
    }
   
    var length = $("#heads").children().length
    for(var i =0; i< length; i++){
        $("#heads").children().eq(i).html(data[i]);
    }
}




function updateList(JsonObj) {

    var result = ""; 

    if(JsonObj.status == "OK"){
        var count = JsonObj.count; 
        window.page = JsonObj.pageNo;
        window.pageRows = JsonObj.pagenationPage;
    var i;
    var items  = JsonObj.list;
    for(i=0; i<count; i++){
	
        result += "<tr>\n";
        result += "<td><a class='racodeD'  data-racode='"+items[i].ra_NAME+"' >" + items[i].ra_NAME + "</a></td>\n";
        result += "<td>" + items[i].ra_CODE +"</td>\n";
        result += "<td>" + items[i].ra_ROUTENAME + "</td>\n";
        result += "<td>" + items[i].ra_DESTINATION + "</td>\n";
        result += "<td>" + items[i].ra_ROUTENO+ "</td>\n";
        result += "</tr>\n";
    }

    $("#list tbody").html(result); //업데이트 
    $("#pageinfo").html( " 총 "+"<span class='text-warning' >" + JsonObj.totalCnt + "</span>" + " 명의 회원"  );

    //페이징 정보 업데이트 
  
    pagination = buildPagination(JsonObj.writePages, JsonObj.totalPage, JsonObj.pageNo, JsonObj.pagenationPage);
    $("#pagination").html("null");
    $("#pagination").html(pagination);
    

   



    return true;
   } else {
       alert("내용이 없습니다")
       return false;
   } 



    function buildPagination(writePages, totalPage, curPage, pageRows){
        var str = ""; 
        var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
        var end_page = start_page + writePages - 1;
    
        if (end_page >= totalPage){
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
    } 
}


function updateG(pageNo){
    $.ajax({
        url : "./Gasstion/" + pageNo + "/" + pagenationPage,
        type : "GET",
        cache : false,
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        contentType : 'application/json; charset=UTF-8',
        dataType : 'json',
          success : function(data, status){
          if(status == "success"){
              thead(data);
              updateGas(data);
            
          }
          
      }
  });
}










// 주유소 


function updateGas(JsonObj) {
  
    var result = ""; 
    if(JsonObj.status == "OK"){
        var count = JsonObj.count; 
        window.page = JsonObj.pageNo;
        window.pageRows = JsonObj.pagenationPage;
    var i;
    var items  = JsonObj.list;
    for(i=0; i<count; i++){
        result += "<tr>\n";
        result += "<td>" + items[i].gs_NAME + "</td>\n";
        result += "<td>" + items[i].gs_CODE + "</td>\n";
        result += "<td>" + items[i].gs_DIESEL + "</td>\n";
        result += "<td>" + items[i].gs_GASOLINE + "</td>\n";
        result += "<td>" + items[i].gs_LPG+ "</td>\n";
        result += "</tr>\n";
    }

    $("#list tbody").html(result); //업데이트 
    $("#pagination").html("");
    //페이징 정보 업데이트 
    pagination = Pageing(JsonObj.writePages, JsonObj.totalPage, JsonObj.pageNo, JsonObj.pagenationPage);
    $("#pagination").html(pagination);
    return true;

   } else {
       alert("내용이 없습니다")
       return false;
   } 

    function Pageing(writePages, totalPage, curPage, pageRows){
      
        var str = ""; 
        var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
        var end_page = start_page + writePages - 1;

        if (end_page >= totalPage){
            end_page = totalPage;
        }
        

        if(curPage > 1){
            str += "<li><a onclick='updateG(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
        }
        

        if (start_page > 1) 
            str += "<li><a onclick='updateG(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
        
        if (totalPage > 1) {
            for (var k = start_page; k <= end_page; k++) {
                if (curPage != k)
                    str += "<li><a onclick='updateG(" + k + ")'>" + k + "</a></li>\n";
                else
                    str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
            }
        }
        
        if (totalPage > end_page){
            str += "<li><a onclick='updateG(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
        }


        if (curPage < totalPage) {
            str += "<li><a onclick='updateG(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
        }

        return str;
    } 


}



// 메뉴



function updateM(pageNo){
    $.ajax({
        url : "./FoodMenu/" + pageNo + "/" + pagenationPage,
        type : "GET",
        cache : false,
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        contentType : 'application/json; charset=UTF-8',
        dataType : 'json',
          success : function(data, status){
          if(status == "success"){
                thead(data);
                updateMenu(data)
        }
          
      }
  });
}


function updateMenu(JsonObj) {
  
    var result = ""; 
    if(JsonObj.status == "OK"){
        var count = JsonObj.count; 
        window.page = JsonObj.pageNo;
        window.pageRows = JsonObj.pagenationPage;
    var i;
    var items  = JsonObj.list;
    for(i=0; i<count; i++){
        result += "<tr>\n";
        result += "<td>" + items[i].ra_NAME + "</td>\n";
        result += "<td>" + items[i].ra_CODE+ "</td>\n";
        result += "<td>" + items[i].fm_CODE + "</td>\n";
        result += "<td>" + items[i].fm_NAME + "</td>\n";
        result += "<td>" + items[i].fm_price + "</td>\n";

        result += "</tr>\n";
    }

    $("#list tbody").html(result); //업데이트 
    $("#pagination").html("");
    //페이징 정보 업데이트 
    pagination = Pageing(JsonObj.writePages, JsonObj.totalPage, JsonObj.pageNo, JsonObj.pagenationPage);
    $("#pagination").html(pagination);
    return true;

   } else {
       alert("내용이 없습니다")
       return false;
   } 

    function Pageing(writePages, totalPage, curPage, pageRows){
      
        var str = ""; 
        var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
        var end_page = start_page + writePages - 1;

        if (end_page >= totalPage){
            end_page = totalPage;
        }
        

        if(curPage > 1){
            str += "<li><a onclick='updateM(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
        }
        

        if (start_page > 1) 
            str += "<li><a onclick='updateM(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
        
        if (totalPage > 1) {
            for (var k = start_page; k <= end_page; k++) {
                if (curPage != k)
                    str += "<li><a onclick='updateM(" + k + ")'>" + k + "</a></li>\n";
                else
                    str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
            }
        }
        
        if (totalPage > end_page){
            str += "<li><a onclick='updateM(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
        }


        if (curPage < totalPage) {
            str += "<li><a onclick='updateM(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
        }

        return str;
    } 


}




//검색 


function info_Serch(){
    
    var tex = $("#sele_option").val();
    var text_info = $(".input_seach").val();

    if(text_info == null || text_info ==""){
        alert("검색 내용이 비었습니다.");
        $(".input_seach").focus();
		return false;
	}
	
	var data = { 
        target : tex ,
        content : text_info
    }
    
	$.ajax({
		data : JSON.stringify(data), 
		url : "areaInFoSeach",
        type : "POST",
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
		dataType : "JSON",
		contentType:'application/json;',
		 success : function(data, status){
	            if(status == "success"){
                    
                    if(data.count == 0){
                        alert("검색 결과가 없습니다.");
                        return false;
                    }

                    if(tex == "values_1"){
                        updateList(data);
                        thead(data);
                        popUp()
                    }else if(tex == "values_2"){
                         updateGas(data);
                         thead(data);
                    }else{
                        updateMenu(data);
                        thead(data);
                    }
                
	             }
	         }
        });
    
}




function popUp(){
    $("#list .racodeD").click(function(){
        var data = $(this).attr('data-racode');
        var url = "areaInfoPopUp/areaInfoUpdate";
		var name = "areaInfoUpdatePopUp";
        var option = "width = 800, height = 600 left = 400, top=100,location=no";
        var chiled;
        $("#parent").val(data);

        chiled = window.open(url,name,option)
        

      
    })
}


function dataUpdate(){

    if(confirm("데이터를 업데이트 하시겠습니까?")){
        
        $.ajax({
            url : "../api/allapi",
            Type : "GET",
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data,status){
                if(data.status=="OK"){
                    alert("업데이트 완료");
                }
            }


        })
    } 

    return false;


}



