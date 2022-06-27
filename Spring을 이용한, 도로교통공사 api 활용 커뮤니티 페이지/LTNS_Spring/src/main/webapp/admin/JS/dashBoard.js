
$(document).ready(function() {
    total_Acount();
    today_acount();
    today_post();
    Best();
    monthPostChart();

  
   
    
})


//신고 게시물 팝업 
function POPUP_REPORT(){
    var url = "repostPopup";
    var name = "repostPopup";
    var option = "width = 800, height = 600 left = 200, top=150,location=no";
    window.open(url,name,option)
}



//일일 토탈 ajax 
function total_Acount(){

    $.ajax({
        url : "total_Acount",
        type : "POST",
        cashe : false,
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        success : function(data,status){
            if(data.status == "OK"){
                totaldata(data)
            }
        }
    });

    function totaldata(JsonObj){
        var count = JsonObj.count;
        if(count == null ){
            alert("데이터가 없습니다")
        }

        $("#numbers_acount").text("총 회원수 " + count  + " 명");

    }
}

function today_acount(){
    $.ajax({
        url : "today_acount",
        type : "POST",
        cashe : false,
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        success : function(data,status){
            if(data.status == "OK"){
                today_acounts(data)
            }else{
               
                $("#numbers_today").html( "<span class='text-info'>" + "텅" + "</span>");
            }
        }
    });

    function today_acounts(JsonObj){
        var count = JsonObj.count;
       
            $("#numbers_today").html( "<span class='text-info'>" + count + "</span>");
        
    }
}


function today_post(){
    $.ajax({
        url : "today_post",
        type : "POST",
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        cashe : false,
        success : function(data, status){
            if(data.status == "OK"){
                $("#today_post").html( "<span class='text-success'>" + data.count + "</span>");
                
            }else{
                $("#today_post").html("<span class='text-success'>"+ " 텅 " + "</span>");
            }
        }

    });

    
    function  total_post(){
        $.ajax({
            url : "total_post",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data,status){
                if(data.status=="OK"){
                    $("#total_post").text("총 게시글 수 " + data.count);
                }
            }
        })
    }

    function today_report(){
        $.ajax({
            url : "today_report",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data,status){
                if(data.status=="OK"){


                    
                    $("#today_reports_post").html("<span id='reports_span' onclick='POPUP_REPORT();'>"  + data.count + " 개 </span>");
                    
                    if(data.count <= 50){
                        $("#icons_report").html("<i class='far fa-smile text-success'></i>");
                    }else if(data.count <= 50  || data.count >= 100){
                        $("#icons_report").html("<i class='far fa-surprise text-warning'></i>");
                    }else if(data.count <= 100 ){
                        $("#icons_report").html("<i class='far fa-angry text-danger'></i>");
                    }
                
                
                }else{
                    $("#icons_report").html("<span class='text-danger'> 텅</span>");
                    
                }
            }

        })
    }


    function ChipGas(){
        //가장 저렴한 주유소
        $.ajax({
            url : "ChipGas",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data,status){
                if(data.status=="OK"){
                    $("#chip_gas").html("<span>" + data.list[0].gs_NAME + "</span>");
                    $("#chip_gas_D").text("경유 : " + data.list[0].gs_DIESEL);
                    $("#chip_gas_O").text("휘발류 : " + data.list[0].gs_GASOLINE);
                    $("#chip_gas_L").text("LPG : " + data.list[0].gs_LPG);
                }
            }
        });
    }

    
    //호출
    ChipGas();
    today_report();
    total_post();
}

function Best(){
    bestArea()
    bestFood()
    bestGas()
    totalLike()
    function bestArea(){
        $.ajax({
            url : "bestArea",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data, status){
                if(data.status=="OK"){
                    var items = data.list;
                    $("#Best1").html( "<i class='far fa-hand-peace'></i> " +items[0].ra_name + "</span>");
                    $("#Best2").html( items[1].ra_name);
                    $("#Best3").html( items[2].ra_name);    
                    $("#recomend_area").html(" Best : " +  "<span class='text-info'>"+items[0].best_RA + "<span>" );
                }
            }

        });
    }

    function bestFood(){
        $.ajax({
            url : "bestFood",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data, status){
                if(data.status=="OK"){
                    var items = data.list;
                
                    $("#Food_name").html( "<i class='far fa-hand-peace'></i> " +items[0].ra_name + "</span>");
                    $("#Food_price").html( " 가격: " +  items[0].fm_PRICE);
                    $("#Food_raea").html( items[0].fm_name);   
                    $("#recomend_food").html(" Best : " +  "<span class='text-success'>"+items[0].best_RA + "<span>" ); 
                    
                }
            }

        });
    }

    function bestGas(){
        $.ajax({
            url : "bestGas",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data, status){
                if(data.status=="OK"){
                    var items = data.list;
                
                    $("#gas_name").html( "<i class='far fa-hand-peace'></i> " +items[0].gs_name + "</span>");
                    $("#gas_price").html( " 디젤 : " +  items[0].gs_DIESEL + "   휘발류 :" + items[0].gs_GASOLINE);
                    $("#gas_price_lpg").html(" LPG :" + items[0].gs_LPG )
                    $("#recomend_gas").html(" Best : " +  "<span class='text-danger'>"+items[0].best_RA + "<span>" ); 
                }
            }

        });
    }



    function totalLike(){
        $.ajax({
            url : "totalLike",
            type : "POST",
            cashe : false,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success : function(data, status){
                if(data.status=="OK"){
                    var items = data.list;
                    var totaldata = items[0].ralike  + items[0].fmlike + items[0].gslike;
                   
                    $("#total_recomend").html( "<span class='text-warning'>"  + totaldata + "</span>") ;
                    $("#total_area").html( "휴게소  : " +  items[0].ralike );
                    $("#total_food").html(" 음식 :" + items[0].fmlike )
                    $("#total_gas").html(" 주유소 : "   +items[0].gslike  ); 
                }
            }

        });
    }

}



//회원가입 차트 
function monthPostChart() {
    memberListChart()

    $.ajax({
        url : "monthPostChart",
        type : "POST",
        cashe : false,
        headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		},
        success : function(data, status){
            if(data.status="OK"){
                if(data.list != null)  {
                    monthChart(data)    
                }else{
                    alert("데이터가 없습니다.");
                } 
            }
        }
    });


    function monthChart(JsonObj){
        var cnt = JsonObj.count;
        var items  = JsonObj.list;
        var i  =0;
        var ctxMember = document.getElementById('myChart');

        var config = {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: '일일 게시물 수',
                data: [
                ],
                backgroundColor: [
                  
                ],
                borderColor: [
                    '#8181F7',
                ],
                fill : false,
                lineTension: 0,
            }]
        },
        options: {
              legend: { display: false },
            responsive: false,
            title: {
                display: true,
                text: '월별 게시물 통계'
        },

            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 5,
                        maxTicksLimit: 3
                    }
                }]
            }
        }
    }; // end config



    for(i=0; i<cnt; i++){
        var dataset  = config.data.datasets ;
        var data = dataset[0].data;
        var backgroundColor = dataset[0].backgroundColor
        var label = config.data.labels;
        

        label.push(items[i].membersdays);
        data.push(items[i].month_total);
        backgroundColor.push('#8181F7');
    }

    

    var mymemberlist = new Chart(ctxMember,config)  

  

    } // end function



    //chart2 memberListChart

    function memberListChart(){
        $.ajax({
            url : "memberListChart",
            type : "POST",
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            cashe : false,
            success : function(data, status){
                if(data.status=="OK"){
                    if(data.count == null){
                        console.log("데이터가 없습니다")
                    }else{
                        memberChartsData(data);
                    }
                  
                }
            }
        });
    }

    function memberChartsData(JsonObj){
        var cnt = JsonObj.count;
        var items  = JsonObj.list;
        var i  = 0;
        var ctxAcount = document.getElementById('memberListAdd');
        var config = {
            type: 'line',
            data: {
                labels: [
                   ],
                datasets: [{
                    label: '일일 가입자 수 ',
                    data: [
                        
                    ],
                    backgroundColor: [
                        
                    ],
                    borderColor: [
                        '#FF0000'
                    ],
                    borderWidth: 1,
                    fill : false,
                     lineTension: 0,
                }]
            },
            options: {
                legend: { display: false },
                responsive: false,
                title: {
                    display: true,
                    text: '월별 유저 통계'
            },
    
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            stepSize: 1,
                            maxTicksLimit: 3
                        }
                    }]
                }
            }
        }; // end chart data


        


    for(i=0; i<cnt; i++){
        var dataset  = config.data.datasets ;
        var data = dataset[0].data;
        var backgroundColor = dataset[0].backgroundColor
        var label = config.data.labels;
        

        label.push(items[i].addAcount);
        data.push(items[i].addAcountTotal);
        backgroundColor.push('#FF0000');
    }

    

    var mymemberlist = new Chart(ctxAcount,config)  




    } // end function 
   
   
   
} // end function 





/* 
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
            success : function(data, status){
                if(data.status=="OK"){
                    if( data.count !=0 ){
                        $(".popupBody").html("읽지 않은 공지사항이 " + data.count + " 개 있습니다.");
                    }else{
                        $(".popupBody").html("오늘 공지사항은 없습니다.");
                    }
                }
            }

        })
        cnt ++;
    }
}

*/