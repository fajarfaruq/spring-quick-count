
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                <meta http-equiv="refresh" content="30">
		<title>Perolehan Suara Per Kecamatan</title>

		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chart/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chart/modules/exporting.js"></script>
		<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Perolehan Suara Per Kecamatan'
            },
            subtitle: {
                text: 'Real Count Kabupaten Nganjuk'
            },
            xAxis: {
                categories: ${listkecamatan},
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Jumlah DPT',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' Pemilih'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -100,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: ${datahasil}
        });
    });
    

		</script>
	</head>
	<body>
<div id="container" style="min-width: 400px; height: 650px; margin: 0 auto"></div>

	</body>
</html>
