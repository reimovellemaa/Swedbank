  
function optionsForCountryFilter(divTag){
		
			$.get('rest/andmed/getDistinctCountrys', {
               }, function (responseText) {
              
               	
               	 var json = jQuery.parseJSON(responseText);;     
              		var sel = document.getElementById(divTag);
               for(var i = 0; i < json.length; i++) {
                   var opt = document.createElement('option');
                   if(json[i].country==="GR"){
                	   json[i].country="All";
                   }
                   opt.innerHTML = json[i].country;
                   opt.value = json[i].country;
                   sel.appendChild(opt);
               }
               
               });
			
}

function optionsForFilter(divTag,restCall){
	
	$.get(restCall, {
       }, function (responseText) {
       var json = jQuery.parseJSON(responseText);   
       var sel = document.getElementById(divTag);
       for(var i = 0; i < json.length; i++) {
    	 
           var opt = document.createElement('option');
           opt.innerHTML =json[i].value;
           opt.value =json[i].value;
           console.log(json[i].value);
           sel.appendChild(opt);
       }
       
       });
	
}