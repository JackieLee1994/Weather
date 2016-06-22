function showTemp() {
	 //获取数据
    var datas;
    var arrayTemp = new Array(24);
    $.ajax({
			type : "GET",
			content : "application/x-www-form-urlencoded;charset=UTF-8",
			url : "handler/Data/getData",
			dataType : "json",
			data : {},
			success : function(data) {
				datas = data.data.Datas;
				for (var i = 0; i < datas.length; i++) {
					arrayTemp[i]=datas[i].wd;
				}
			},
			error : function() {
				alert("error");
			}
		});	
    
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                	    title : {
                	        text: '今日温度变化',
                	        subtext: ''
                	    },
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['最高温度','最低温度']
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {show: true, type: ['line', 'bar']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            boundaryGap : false,
                	            data : ['00:00','01:00','02:00','03:00','04:00','05:00','06:00',
                	                    '07:00','08:00','09:00','10:00','11:00','12:00','13:00',
                	                    '14:00','15:00','16:00','17:00','18:00','19:00','20:00',
                	                    '21:00','22:00','23:00']
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value',
                	            axisLabel : {
                	                formatter: '{value}'
                	            }
                	        }
                	    ],
                	    series : [
                	        {
                	            name:'实时温度(℃)',
                	            type:'line',
                	            data:arrayTemp,
                	            markPoint : {
                	                data : [
                	                    {type : 'max', name: '最大值'},
                	                    {type : 'min', name: '最小值'}
                	                ]
                	            },
                	            markLine : {
                	                data : [
                	                    {type : 'average', name: '平均值'}
                	                ]
                	            }
                	        }
                	    ]
                	};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
}

window.onload = showTemp();
