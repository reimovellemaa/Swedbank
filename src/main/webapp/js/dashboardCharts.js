function bubbleChart(divTag,xValues,yValues,zValues,serviceGroupName,dataDate,country){
	
	
	var xValues =xValues;

	var yValues =yValues;

	var zValues = zValues;
	var colorscaleValue = [
	  [0, '#3D9970'],
	  [1, '#001f3f']
	];

	var textArr=[];	
	for ( var i = 0; i < yValues.length; i++ ) {
		if(yValues.length!=i){
			
			var	date=dataDate[i];
			var text=yValues[i];
			var data=text.concat(text+" date:"+xValues[i]+" result:"+zValues[i] +",");
			
		}else{
			var	size=dataDate[i];
			var text=yValues[i];
			var data=text.concat(text+" date:"+xValues[i]+" result:"+zValues[i]);
		}
		textArr.push(data)
	}
	var trace1 = {
			  x: zValues,
			  y: dataDate,
			  text:textArr,
			  mode: 'markers',
			  marker: {
				cmin: 0,
			    cmax: 100,
				colorscale: [['0','rgb(244,66,66)'],['0.5','rgb(220,30,30)'], ['1',' rgb(3,178,12)']],
				color:zValues,
			    size: zValues
			  }
			};

			var data = [trace1];

			var layout = {
			  title: serviceGroupName+" "+country,
			  showlegend: false,
			  height: 500,
			  width: 1400
			};

	Plotly.newPlot(divTag, data, layout);
	
	
}


function mapChart(divTag,country,measures,extrainfo,metricTypeName,serviceGroupName,date){
				
			
				var data = [{
				    type: 'scattergeo',
				    mode: 'markers+text',
				    locations: country,
				    z:measures,
				    text:measures,
				    marker: {
				        size: 25,
				        color:measures,
				        cmin: 0,
				        cmax: 100,
				        cauto: false,
				        hoverinfo: 'label+text',
				        colorscale: [['0','rgb(239,35,35)'], ['1',' rgb(53,183,20)']],
				        colorbar: {
				            title: metricTypeName,
				            ticksuffix: '%',
				            showticksuffix: 'first'
				        },
				        line: {
				            color: 'black'
				        }
				      
				    },
				 
				}];

				var layout = {
				    	title:extrainfo+" "+serviceGroupName+"<br>"+date,
				    	height: 500,
						width: 700,
						'geo': {
				        'scope': 'europe',
				        'resolution': 500,
				         projection: {
				              type: 'robinson'
				          }
				    }
				};

			


			      Plotly.plot(divTag, data, layout, {showLink: false, displaylogo: false});
				
				
			}
			
		
			
			function circleChart(divTag,value,serviceName,country,dqName,info,categType,date){
				var value2=0;
			    if(country==="GR"){
		        	   country="BB";
		        }
				if(categType==="DQERR"){
					if(value>0){
						value2=100-value;
				
					}else{
						value=100;
					}					
				}else{
					
					value2=100-value;
					
				}
			    var errorName;
				if(dqName==="ACCURACY TO SOURCE"){
					errorName="Inaccurate";
					dqName="Aaccurate";
				}else if(dqName==="DATAPROFILE"){
					errorName="Error";
					dqName="Good";
					
				}else if(dqName==="COMPLETENESS"){
					errorName="Incomplete";
					dqName="Complete";
				}else if(dqName==="CONSISTENCY"){
					errorName="Inonsistent";
					dqName="Consistent";
				}else if(dqName==="FORMAT  CONFORMANCY"){
					errorName="Non-conform";
					dqName="Conform";
				}
				
			    
				var data = [{
						values: [value,value2],
					  labels: [dqName,errorName],
					  marker:{  colors:['rgb(35,113,26)','rgb(194,63,56)']},
					  domain: {
					    x: [0, .65]
					  },
					  name: categType,
					  hoverinfo: 'label+percent+name+text',
					  hole: .5,
					  type: 'pie'
					}];

					var layout = {
							autosize: false,
							title: serviceName+" "+country+"<br>"+date,
							height: 300,
							width: 500,
							margin: {
								l: 50,
								r: 0,
								b: 0,
								t: 110,
								pad: 2
							},
							showlegend: true,
							  legend:{
								    xanchor:"center",
								    yanchor:"top",
								    y:0.9, // play with it
								    x:0.8   // play with it
								  }
					
					  
					};
					
				
						Plotly.newPlot(divTag, data, layout, {displaylogo: false});
					
						
				
				
			}
			
			
			
			function barChart(divTag,dataX,dataY,serviceName,extraInfo,info,country){
				var trace1 = {
						  x: dataX,
						  y: dataY,
						  marker:{
						    color: [
						    	'rgba(238,112,35,1)', 
						    	'rgba(238,112,35,1)', 
						    	'rgba(238,112,35,1)', 
						    	'rgba(238,112,35,1)', 
						    	'rgba(238,112,35,1)']
						  },
						  type: 'bar'
						};
		
						var data = [trace1];
		
						var layout = {
						  title:country+" "+serviceName+"<br>"+info
						  
						};
		
						Plotly.newPlot(divTag, data, layout, {displaylogo: false});
				
				
			}
<!-- Line chart-->
			function lineChart(divTag,dataX,dataY,country,serviceName,info,info2){		
				var data = [
					  {
					    x: dataX,
					    y: dataY,
					    type: 'scatter'
					  }
					];
				
				var layout = {
						  title:country+" "+serviceName+" "+info2 +"<br>"+info
						  
						};
				Plotly.newPlot(divTag,data,layout,{displaylogo: false});
			}
			
<!-- Heatmap chart-->
			function heatMap(divTag,xValues,yValues,zValues,serviceGroupName){
				
				var xValues =xValues;

				var yValues =yValues;

				var zValues = zValues;
				var colorscaleValue = [
				  [0, '#3D9970'],
				  [1, '#001f3f']
				];

				var data = [{
				  x: xValues,
				  y: yValues,
				  z: zValues,
				  type: 'heatmap',
				  colorscale: colorscaleValue,
				  showscale: true
				}];

				var layout = {
				  title: serviceGroupName,
				  margin: {
					   l: 220,
					    r: 80,
					    b: 80,
					    t: 170,
					    pad: 0
					  },
				  annotations: [],
				  xaxis: {
				    ticks: '',
				    side: 'top'
				  },
				  yaxis: {
				    ticks: '',
				    ticksuffix: ' ',
				    width: 700,
				    height: 700,
				    autosize: true
				  }
				};

				for ( var i = 0; i < yValues.length; i++ ) {
					
					//From O(n pow 3) to O(n) if needed could make it O(n log n) with divide and conguer
					
				    var currentValue = zValues[i];
				    var zVal=zValues[i];
				    if (currentValue >50) {
				      var textColor = 'white';
				    }else{
				      var textColor = 'black';
				    }
				   
				    
				    var result = {
				      xref: 'x1',
				      yref: 'y1',
				      x: xValues[i],
				      y: yValues[i],
				      text: zVal,
				      font: {
				        family: 'Arial',
				        size: 12,
				        color: 'rgb(50, 171, 96)'
				      },
				      showarrow: false,
				      font: {
				        color: textColor
				      }
				    };
				    layout.annotations.push(result);
				    
				  
				}

				Plotly.newPlot(divTag, data, layout, {displaylogo: false});
			}
			
			