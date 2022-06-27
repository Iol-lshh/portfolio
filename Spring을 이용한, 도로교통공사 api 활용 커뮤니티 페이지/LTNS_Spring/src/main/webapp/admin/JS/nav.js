
var cnt =0;
// popover 
function popover(){
    if(cnt==1 ){
    $(".popovers").hide();
    cnt=0;
    }else{
        $(".popovers").show();
        
        $.ajax({

            url : "alram",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data, status){
                if(data.status=="OK"){
                    if( data.count !=0 ){
                        $(".popupBody").html("오늘 작성된 공지사항이 " + data.count + " 개 있습니다.");
                    }
                }
            }

        })
        cnt ++;
    }
}