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
				
				 
				var data = [{
					  values: [100-value,value],
					  labels: ['UNCOMPLETENESS',dqName],
					  marker:{  colors:['rgb(215, 56, 24)']},
					  domain: {
					    x: [0, .55]
					  },
					  name: categType,
					  hoverinfo: 'label+percent+name+text',
					  hole: .4,
					  type: 'pie'
					}];

					var layout = {
					  title: serviceName+" "+country+"<br>"+info,
					  height: 500,
					  width: 500
					
					  
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
			function lineChart(){		
				var data = [
					  {
					    x: ['2013-10-04 22:23:00', '2013-11-04 22:23:00', '2013-12-04 22:23:00'],
					    y: [1, 3, 6],
					    type: 'scatter'
					  }
					];
				Plotly.newPlot('timeSeries', data);
			}
			
			<!-- Heatmap chart-->
			function heatMap(){
				
				var data = [
					  {
					    z: [[1, 20, 30], [20, 1, 60], [30, 60, 1]],
					    type: 'heatmap'
					  }
					];

					Plotly.plot('myDiv', data);
			}
			
			