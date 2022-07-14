<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="refresh" content="30">
        <title>Persentase Perolehan Suara</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#container').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: ''
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                        percentageDecimals: 1
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                formatter: function() {
                                     return '<b>' + this.point.name + '</b>: ' + this.percentage.toFixed(2) + ' %';
                                }
                            }
                        }
                    },
                    series: [{
                            type: 'pie',
                            name: 'Perolehan Suara',
                            data: [
            ${listhasilgrafik}
                            ]
                        }]
                });
            });


        </script>       
        <style>
            body{
                font-family: Arial, Helvetica, sans-serif;
            }
            #judul{
                width: 100%;
                text-align: center;
                font-size: 16px;
                color: #274B6D; 

            }
            #tampung{
                width: 1000px;
                padding-top: 10px;    
            }
            #sesi1{
                width: 300px;
                height: 500px;
                float: left;

            }
            #itemcalon{
                width: 250px;
                height: 70px;
                border: #000000 1px solid;
                -webkit-border-radius: 3px;
                -moz-border-radius: 3px;
                border-radius: 3px;
            }
            #itemcalon #gambar{
                width: 100px;
                padding: 3px;
            }
            #itemcalon #namacalon{
                font-size: 11px;
                color: #2F7526;
                margin-top: -2px;
                padding: 0 2px;
                margin-left: 105px;
                margin-right: 5px;
                padding-top: 1px;
                margin-top: -3px;
                color: #222;
                width: 150px;
                font-weight: bold;
            }
            #itemcalon #jmlsuara{
                font-size: 11px;
                color: #2F7526;
                margin-top: -2px;
                padding: 0 2px;
                margin-left: 105px;
                margin-right: 5px;
                padding-top: 1px;
                margin-top: -3px;
                color: #222;
                width: 150px;
                font-weight: bold;
                text-align: center;
            }
            #itemcalon #persentase{
                font-size: 32px;
                color: #2F7526;
                margin-left: 105px;
                margin-right: 5px;
                font-weight: bold;
                color: #000000;
                width: 150px;
                text-align: center;
                padding-top: 2px;
            }
        </style>
    </head>
    <body>
        <div id="judul">Persentase Perolehan Suara Sementara<br/>
            <span style="font-size: 11px;">Real Count Pilgub Jatim 2013 Kabupaten Nganjuk</span>
        </div>
        <br/>
        <hr>
        <div id="tampung">
            <div id="sesi1">
                ${listhasil}
                <br/>
            </div>
            <div id="container" style=" min-width: 300px; height: 200px;width: 700px; margin: 0 auto;margin-left:300px; "></div>    
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chart/highcharts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chart/modules/exporting.js"></script>
    </body>
</html>