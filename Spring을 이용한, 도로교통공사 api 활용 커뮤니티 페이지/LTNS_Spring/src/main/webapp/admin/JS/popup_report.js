
$(document).ready(function() {
 
	repostPopup();

})


function repostPopup(){
	    $.ajax({
	        url : "repostPopup",
	        type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
	        success : function(data, status){
	            if(data.status=="OK"){
	                popup_table(data)
	            }
	        }
	
	    });
	
}


function popup_table(JsonObj){
    window.page = JsonObj.pageNo;
    window.pageRows = JsonObj.numOfRows;
    var result = ""; 
    var count = JsonObj.count;
    var i;
    var items  = JsonObj.list; 
    for(i=0; i<count; i++){
        result += "<tr>\n";
        result += "<td>" + items[i].post_id + "</td>\n";
        result += "<td><span class='subject' data-post_id='" + items[i].post_id + "'>" + items[i].post_title + "</span></td>\n";
        result += "<td><span data-viewcnt='" + items[i].post_id + "'>" + items[i].ra_code + "</span></td>\n";
        result += "<td>" + items[i].um_USERNAME + "</td>\n"; 
        result += "<td>" + items[i].post_reported + "</td>\n"; 
        result += "</tr>\n";
    }

    $("#list tbody").html(result); //업데이트 
 
}

