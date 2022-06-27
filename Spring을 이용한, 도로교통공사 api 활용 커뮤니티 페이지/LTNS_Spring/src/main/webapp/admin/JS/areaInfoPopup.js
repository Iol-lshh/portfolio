$(document).ready(function() {

    var id = opener.$("#parent").val();
    var data = $("#datas_name").val(id);

    var objectsData = {
        names :  id
    }

    $.ajax({
        url : "XYDATAREAD",
        data : JSON.stringify(objectsData),
        type : "POST",
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
		dataType : "JSON",
		contentType:'application/json;',		
        success : function(data,status){
            if(data.status=="OK"){
                loadMap(data)
            }
        }    
    })


    function loadMap(JsonObj){ 

        var y = JsonObj.list[0].ra_YVALUE;
        var x = JsonObj.list[0].ra_XVALUE;
        console.log(y + ":" + x);
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(y, x), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };  
         // 지도를 생성합니다    
          var map = new kakao.maps.Map(mapContainer, mapOption); 


        //마커생성
        var markerPosition  = new kakao.maps.LatLng(y, x); 
    
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        marker.setMap(map);


        var iwContent = "<div style='padding:5px;'>"+id+"</div>", // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
            position : iwPosition, 
            content : iwContent 
        });
        
        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker); 


    
    }
  
})

