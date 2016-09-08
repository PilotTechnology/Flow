"use strict";
$(document).ready(function(){
	
	$.ajax({
		type : "POST",
		url  : '/portal/order!statisticsJson.action',
		async: false,
		success:function(data){
			makeSizeChart(data.sizeList);
			makeProChart(data.proList);
			makeDayChart(data.dayList);
		}
	});
	
	function makeSizeChart(data){
		var c=[];
		for(var a=0;a<data.length;a++){
			//构建流量大小饼状图数据
			c[a]={label : "流量大小:" + data[a].size , 
				  data  : data[a].successNum
				 }
		}
		$.plot("#chart_pie",
				c,
				$.extend(true,
						 {},
						 Plugins.getFlotDefaults(),
						 {
							 series:{
								 	pie:{
								 		show:true,
								 		radius:1,
								 		label:{show:true}
								 	}
							 },
							 grid:{hoverable:true},
							 tooltip:true,
							 tooltipOpts:{content:"%p.0%, %s",shifts:{x:10,y:0}}
						 }
				)
		)
		
	}
	
	function makeProChart(data){
		var c=[];
		for(var a=0;a<data.length;a++){
			//构建流量大小饼状图数据
			c[a]={label : data[a].province , 
				  data  : data[a].successNum
				 }
		}
		$.plot("#chart_day",
				c,
				$.extend(true,
						 {},
						 Plugins.getFlotDefaults(),
						 {
							 series:{
								 	pie:{
								 		show:true,
								 		radius:1,
								 		label:{show:true}
								 	}
							 },
							 grid:{hoverable:true},
							 tooltip:true,
							 tooltipOpts:{content:"%p.0%, %s",shifts:{x:20,y:0}}
						 }
				)
		)
		
	}
	function makeDayChart(data){
		var c=[];
		var d=[];
		for(var a=0;a<data.length;a++){
			//构建天数趋势图数据
			c[a]=[data[a].day ,data[a].successNum];
		}
		var tu1 = [ {
			label : "成功数",
			data : c,
			color : App.getLayoutColorCode("blue"),
			lines : {fill : true
			},
			points : {
				show : false
			}
		} ];
		
		$.plot("#chart_filled_blue",
				tu1,
				$.extend(true,{},
						 Plugins.getFlotDefaults(),
						 {series : {
									lines : {show : true},
									points: {show : true},
									grow  : {active : true}
								},
						  grid :{hoverable : true,
								 clickable : true
								},
						  tooltip : true,
						  tooltipOpts : {content : "%s: %y"}
						}
				)
		)
		
	}
});