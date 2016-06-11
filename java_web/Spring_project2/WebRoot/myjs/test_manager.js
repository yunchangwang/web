$(function () {
	    $('#container').highcharts({
	        data: {
	            table: 'datatable'
	        },
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: ''
	        },
	        yAxis: {
	            allowDecimals: false,
	            title: {
	                text: '测试结果'
	            }
	        },
	        tooltip: {
	            formatter: function () {
	                return '<b>' + this.series.name + '</b><br/>' +
	                    this.point.y + ' ' + this.point.name.toLowerCase();
	            }
	        }
	    });
	});