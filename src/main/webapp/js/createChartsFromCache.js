 
function reGenerateChart(MetricType,serviceGroupName,country,qualityMetricName,date1,date2,service,validation,strUser,divTag){
	
            

	   if (strUser == "Map") {

       	
       	
           $.get('rest/andmed/getMapData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {

               var country = [];
               var measures = [];
               var extraInfo = [];
               var count = 0;
               var json = jQuery.parseJSON(responseText);
           
               if (json.length > 0) {

                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
						var parsedCountry;
                      
                       measures.push(obj.measure_amt);
                       country.push(obj.country);
                       extraInfo.push(obj.qualityMetricTypeComment);
                       
                       if(obj.country==="EST"){
           				parsedCountry="EE";
           			}
           			if(obj.country==="LVA "){
           				parsedCountry="LV";
           			}
           			if(obj.country==="LTU"){
           				parsedCountry="LT";
           			}
                       var data={
                           	 'metric_categ': MetricType,
                                'service_group_name': serviceGroupName,
                                'country': parsedCountry,
                                'metric_name': qualityMetricName,
                                'date1': date1,
                                'date2': date2,
                                'comment': obj.qualityMetricTypeComment,
                                'validation':validation
                           };
                       
                      
                       localStorage.setItem(divTag.concat(parsedCountry), JSON.stringify(data) );

                       document.getElementById("demo").innerHTML = "";
                   }
               
                   
                   
                   container = angular.element(document.querySelector("#container"));
                   childNode ='<div class="box-lrg"  ng-draggable="dragOptions"  style = "float:left; width: 50%;"><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div id="' + divTag + '""></div></div>';
                   container.prepend(childNode);
                   mapChart(divTag, country, measures, json[i - 1].qualityMetricTypeComment, qualityMetricName,serviceGroupName,date1.concat(" - "+date2));
                   count = 0;
                   
                   var mapPlot = document.getElementById(divTag);
                   
                   
                   
                   mapPlot.on('plotly_click', function(d) {
                   	//alert("Suka bljat"+JSON.stringify(d));
                   	//var obj=JSON.parse(d);
                   	console.log(JSON.stringify(d.points[0].location));
                   
                   	if(d.points[0].location===55.28){
                   		
                   		window.location.href ="advancedQuery.html?store=" + divTag.concat('LT') + "&result=2&country=LT";
                   		
                   	}else if(d.points[0].location===56.81){
                   		
                   		window.location.href ="advancedQuery.html?store=" + divTag.concat('LV') + "&result=2&country=LV";
                   	
                   	}else{
                   		window.location.href ="advancedQuery.html?store=" + divTag.concat('EE') + "&result=2&country=EE";
                   		
                   	}
                   	
                   	
                   	
                   });
                   
                   

               } else {
                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }
				
             
               	
               	
               	
             
           });


       }
       if (strUser == "Bar") {
	
           $.get('rest/andmed/getBarData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {

               //  var dd = JSON.parse(result);
               var dataX = [];
               var dataY = [];
               var extraInfo = [];

               var json = jQuery.parseJSON(responseText);
               if (json.length > 0) {
                   var divTag = divTag;
                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
                       dataX.push(obj.measure_date);
                       dataY.push(obj.measure_amt);
                       extraInfo.push(obj.service_main_group_name);

                       var data={
                          	 'metric_categ': MetricType,
                               'service_group_name': serviceGroupName,
                               'country': country,
                               'metric_name': qualityMetricName,
                               'date1': obj.measure_date,
                               'date2': obj.measure_date,
                               'comment': obj.qualityMetricTypeComment,
                               'validation':validation
                          };
                       localStorage.setItem(divTag.concat(obj.measure_date), JSON.stringify(data) );

                   }
              
        
                   container = angular.element(document.querySelector("#container"));
                   childNode ='<div class="box-lrg"  ng-draggable="dragOptions"  style = "float:left; width: 50%;"><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div id="' + divTag + '""></div></div>';
                   container.prepend(childNode);
                   barChart(divTag, dataX, dataY, serviceGroupName, extraInfo, obj.qualityMetricTypeComment, country);
                  
					var barPlot = document.getElementById(divTag);
                   
                   
                   
                   barPlot.on('plotly_click', function(d) {
                   	//alert("Suka bljat"+JSON.stringify(d));
                   	//var obj=JSON.parse(d);
                   	//alert(JSON.stringify(d.points[0]));
                   	console.log(d);
                    console.log(d.points[0].x);
                    window.location.href ="advancedQuery.html?store=" + divTag.concat(d.points[0].x) + "&result=2&country="+country+"";
                   	
                   	
                   	
                   });
                   
                   document.getElementById("demo").innerHTML = "";
               } else {
                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }


           });
       }
       if (strUser == "Line") {

           $.get('rest/andmed/getLineData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {

               //  var dd = JSON.parse(result);
               var dataX = [];
               var dataY = [];
               var extraInfo = [];

               var json = jQuery.parseJSON(responseText);
               if (json.length > 0) {

                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
                       dataX.push(obj.measure_date);
                       dataY.push(obj.measure_amt);
                       extraInfo.push(obj.service_main_group_name);



                   }

           
                   container = angular.element(document.querySelector("#container"));
                   childNode ='<div class="box-lrg"  ng-draggable="dragOptions"  style = "float:left; width: 50%;"><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div id="' + divTag + '""></div></div>';
                   container.prepend(childNode);
                   lineChart(divTag, dataX, dataY,country,serviceGroupName,MetricType,qualityMetricName);

                   document.getElementById("demo").innerHTML = "";
               } else {
                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }


           });
       }
       if (strUser == "Heat map") {

           $.get('rest/andmed/getHeatMapData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {

               //  var dd = JSON.parse(result);
               var dataX = [];
               var dataY = [];
               var dataZ = [];
               var dataZFull = [];
               var extraInfo = [];

               var json = jQuery.parseJSON(responseText);
               if (json.length > 0) {

                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
                       dataX.push(obj.qualityMetricTypeComment);
                       dataZFull.push(dataZ.push(obj.measure_amt));
                       dataY.push(obj.qualityMetricTypeName);
                       extraInfo.push(obj.service_main_group_name);




                   }

                
                   container = angular.element(document.querySelector("#container"));
                   childNode ='<div class="box-full"  ng-draggable="dragOptions"  style = "float:left; width: 100%;"><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div id="' + divTag + '""></div></div>';
                   container.prepend(childNode);
                   heatMap(divTag, dataX, dataY, dataZ, serviceGroupName);

                   document.getElementById("demo").innerHTML = "";
               } else {

                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }


           });
       }
       if (strUser == "Bubble") {

           $.get('rest/andmed/getHeatMapData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {

               //  var dd = JSON.parse(result);
               var dataX = [];
               var dataY = [];
               var dataZ = [];
               var dataDate = [];
               var dataZFull = [];
               var extraInfo = [];

               var json = jQuery.parseJSON(responseText);
               if (json.length > 0) {
            	
                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
                       dataX.push(obj.qualityMetricTypeComment);
                       dataZFull.push(dataZ.push(obj.measure_amt));
                       dataY.push(obj.qualityMetricTypeName);
                       extraInfo.push(obj.service_main_group_name);
						dataDate.push(obj.measure_date)

						  var data={
                       	 'metric_categ':MetricType,
                            'service_group_name':obj.service_main_group_name,
                            'country': country,
                            'metric_name':obj.qualityMetricTypeName,
                            'date1': date1,
                            'date2': date2,
                            'comment': obj.qualityMetricTypeComment,
                            'validation':validation
                       };
						   localStorage.setItem(divTag.concat(obj.measure_date), JSON.stringify(data) );
						  
						
                   }

                  
                   container = angular.element(document.querySelector("#container"));
                   childNode ='<div class="box-full"  ng-draggable="dragOptions"  style = "float:left; width: 100%;""><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div id="' + divTag + '""></div></div>';
                   container.prepend(childNode);
                   bubbleChart(divTag, dataX, dataY, dataZ, serviceGroupName,dataDate,country);
                  
                   
                   var myPlot = document.getElementById(divTag);
                   myPlot.on('plotly_click', function(d) {
                   	//alert("Suka bljat"+JSON.stringify(d));
                   	//var obj=JSON.parse(d);
                    	console.log(d);
                       console.log(d.points[0].x);
                       window.location.href ="advancedQuery.html?store=" + divTag.concat(d.points[0].x) + "&result=2&country="+country+"";
                   	
                   	
                  
                   });
                   
                   document.getElementById("demo").innerHTML = "";
               } else {

                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }


           });
       }
       if (strUser == "Donut") {

           $.get('rest/andmed/getDonutData', {
               metric_categ: MetricType,
               service_group_name: serviceGroupName,
               country: country,
               metric_name: qualityMetricName,
               date1: date1,
               date2: date2,
               service:service,
               validation:validation
           }, function (responseText) {
               var json = jQuery.parseJSON(responseText);

               //  var dd = JSON.parse(result);
               if (json.length > 0) {
                   //	gauges[key].redraw(obj.completness);
                   for (var i = 0; i < json.length; i++) {
                       var obj = json[i];
                  
                    /*  if(obj.measure_amt<80){
                   	  
                   	   $.post( "rest/andmed/sendEmailNotification", function( data ) {
                   		 	
                   		 });
                   
                   	   
                   	   
                      }*/
              
                       console.log("Tag in chartRegen "+divTag);
                       container = angular.element(document.querySelector("#container"));
                       childNode = '<div class="box"><button type="button" class="close" onclick="$(this).parent().remove();">×</button><div class="panel-body" id="' + divTag + '"" ></div></div>';
                       container.prepend(childNode);
                       circleChart(divTag, obj.measure_amt, obj.service_main_group_name, country, qualityMetricName, obj.qualityMetricTypeComment, MetricType, date1.concat(" - "+date2));
            
                       var data={
                            	 'metric_categ': MetricType,
                                 'service_group_name': serviceGroupName,
                                 'country': country,
                                 'metric_name': qualityMetricName,
                                 'date1': date1,
                                 'date2': date2,
                                 'comment': obj.qualityMetricTypeComment,
                                 'validation':validation
                            };
                      localStorage.setItem(divTag, JSON.stringify(data) );
                       
                      var myPlot = document.getElementById(divTag);
                       
                       
                       
                       myPlot.on('plotly_click', function(d) {
                       	//alert("Suka bljat"+JSON.stringify(d));
                       	//var obj=JSON.parse(d);
                       	console.log(JSON.stringify(d.points[0].label));
                       	var labelName=JSON.stringify(d.points[0].label);
                       	if(d.points[0].label===qualityMetricName){
                       		
                       		window.location.href ="advancedQuery.html?store=" + divTag + "&result=1";
                       	}else{
                       		window.location.href ="advancedQuery.html?store=" + divTag + "&result=0";
                       	
                       	}
                       	
                       	
                      
                       });
                       //localStorage.setItem('Charts', $('#form').html());
                   }
               
                   document.getElementById("demo").innerHTML = "";
               } else {
                   document.getElementById("noResult").innerHTML = "No result";
                   setTimeout(function(){document.getElementById("noResult").innerHTML = ""},NoResTimer);
               }
           });

                        }

}

