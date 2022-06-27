window.onload = function(){
	var imgs = document.getElementById("img_id")
	var hidens = document.getElementById("hidden_btn")
	var cnt =0;
	var cnts =0;
	 
	
	imgs.onclick = function(){
	    cnt ++;
	    console.log(cnt);
		
	    

	    if(cnt == 10){
	    
	    	var divs =  document.getElementById("img_div");
	    	divs.removeChild(imgs)
	    	divs.removeAttribute("src");	
	    
	    	
	    	var imgs_ele = document.createElement("img");
	    	imgs_ele.setAttribute("id", "img_id");
	    	imgs_ele.setAttribute("src", "../images/tenor.gif");
	    
	    	
	    	divs.appendChild(imgs_ele);	    		
	    }
	    
	}
	
	
	
	hidens.onclick = function(){
		alert("Unicon Mode")
		var divs =  document.getElementById("img_div");
    	divs.removeChild(imgs)
    	divs.removeAttribute("src");
    	
    	var imgs_ele = document.createElement("img");
    	imgs_ele.setAttribute("id", "img_id");
    	imgs_ele.setAttribute("src", "../images/unicon.gif");
    	divs.appendChild(imgs_ele);	 
	}

}