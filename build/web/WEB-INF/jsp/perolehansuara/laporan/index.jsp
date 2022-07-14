<html>
    <title>Print Data</title>
    <head>
        <style>
            body{
                width: 100%;
                height: 100%;
                margin: auto 0 ;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th{
                padding: 4px;
            }
            td {
                border: 1px solid #000000;
                padding: 2px;
            }
            tbody tr td + td + td{
                text-align: right;
            }
            .total{
                float: left;
            }
            .totallabel{
                width: 200px;
                font-weight: bold;
                float: left;
                
            }
        </style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/json-to-table.js"></script>
    </head>
    <body onload="window.print()">
        <br />
        <div align="center">
            <img style="width: 100px;float: left;" src="${pageContext.request.contextPath}/resources/images/icons/logongk.png">             
            <b>LAPORAN HASIL REAL COUNT
            PEMILIHAN GUBERNUR &amp; WAKIL GUBERNUR JATIM 2013
            DAPIL KABUPATEN NGANJUK</b><br/><br/><br/>
        <script type="text/javascript">
        //Example data, Object 
        var objectArray = [${data}];

        var jsonHtmlTable = ConvertJsonToTable(objectArray, 'jsonTable', null, 'Download');
        document.write(jsonHtmlTable);
        </script>
        <table border="0" style="width: 100%;margin-top: -1px;">
            <tr>
                <td>
                    <div class="total"><div class="totallabel">Jumlah DPT </div><b>: ${totaldpt} </b></div><br/>
                    <div class="total"><div class="totallabel">Total Suara Sah </div><b>: ${totalsuarasah} </b></div><br/>
                    <div class="total"><div class="totallabel">Total Suara Tidak Sah</div><b>: ${totalsuaratidaksah}</b></div><br/>
                </td>
            </tr>
        </table>
        </div>
    </body>
</html>