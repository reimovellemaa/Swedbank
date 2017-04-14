function mapChart(divTag,country,measures,extrainfo){
				
			
				var data = [{
				    type: 'scattergeo',
				    mode: 'markers+text',
				    locations: country,
				    marker: {
				        size: [10, 30, 15, 10],
				        color: [10, 20, 40, 50],
				        cmin: 0,
				        cmax: 100,
				        hoverinfo: 'label+percent',
				        colorscale: 'Greens',
				        colorbar: {
				            title: 'Completeness',
				            ticksuffix: '%',
				            showticksuffix: 'first'
				        },
				        line: {
				            color: 'black'
				        }
				      
				    },
				 
				}];

				var layout = {
				    	title:extrainfo,
						'geo': {
				        'scope': 'europe',
				        'resolution': 1000
				    }
				};

			


			      Plotly.plot(divTag, data, layout, {showLink: false});
				
				
			}
			
		
			
			function circleChart(divTag,value,serviceName,country,dqName,info,categType){
				
			    if(country==="GR"){
		        	   country="All";
		        }
				 
				var data = [{
					  values: [value],
					  labels: [dqName],
					  marker:{  colors:['rgb(122, 181, 87)']},
					  domain: {
					    x: [0, .65]
					  },
					  name: categType,
					  hoverinfo: 'label+percent+name+text',
					  hole: .4,
					  type: 'pie'
					}];

					var layout = {
					  title: serviceName+" "+country+"<br>"+info,
					  height: 300,
					  width: 400
					
					  
					};
					
				
						Plotly.newPlot(divTag, data, layout);
					
				
				
				
			}
			
			
			
			function barChart(divTag,dataX,dataY,serviceName,extraInfo,info,country){
				var trace1 = {
						  x:dataX,
						  y: dataY,
						  marker:{
						    color: ['rgba(204,204,204,1)', 'rgba(222,45,38,0.8)', 'rgba(204,204,204,1)', 'rgba(204,204,204,1)', 'rgba(204,204,204,1)']
						  },
						  type: 'bar'
						};
		
						var data = [trace1];
		
						var layout = {
						  title:country+" "+serviceName+"<br>"+info
						  
						};
		
						Plotly.newPlot(divTag, data, layout);
				
				
			}
			<!-- Line chart-->
			function lineChart(divTag,dataX,dataY){		
				var data = [
					  {
					    x: dataX,
					    y: dataY,
					    type: 'scatter'
					  }
					];
				Plotly.newPlot(divTag,data);
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

				Plotly.newPlot(divTag, data, layout);
			}
			
			