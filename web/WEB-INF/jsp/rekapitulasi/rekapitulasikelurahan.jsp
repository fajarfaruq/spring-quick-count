<div id="breadcumb">
    <h4>Rekapitulasi > Kelurahan</h4>
    <br />
</div>
<div class="clear"></div> <!-- End .clear -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.edatagrid.js"></script>
<style type="text/css">
    #fm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        color:#666;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:150px;
    }
    fieldset{
        border: solid 1px #999999;
        padding: 5px 10px;
    }
</style>
<script type="text/javascript">
    function doSearch() {
        $('#dg').datagrid('load', {
            kodekecamatan: $('#cc2').combobox('getValue'),
            kodekelurahan: $('#cc').combobox('getValue')
        });
    }
    function doClearSearch() {
        $('#cariform').form('clear');
        doSearch();
    }

</script>
<script type="text/javascript">
    $(function() {
        $('#dg').datagrid({
            view: detailview,
            detailFormatter: function(index, row) {
                return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
            },
            onExpandRow: function(index, row) {
                $('#ddv-' + index).datagrid({
                    view: cardview
                });
                $('#ddv-' + index).datagrid({
                    url: '${pageContext.request.contextPath}/rekapitulasi/kelurahan/tampildetail/' + row.kodekelurahan,
                    fitColumns: true,
                    singleSelect: true,
                    rownumbers: true,
                    loadMsg: '',
                    height: 'auto',
                    columns: [[
                            {field: 'gambar', title: 'Gambar', width: 200},
                            {field: 'gubernur', title: 'Gubernur', width: 100, align: 'right'},
                            {field: 'wakilgubernur', title: 'Wakil Gubernur', width: 100, align: 'right'},
                            {field: 'suarasah', title: 'Perolehan Suara', width: 100, align: 'right'}
                        ]],
                    onResize: function() {
                        $('#dg').datagrid('fixDetailRowHeight', index);
                    },
                    onLoadSuccess: function() {
                        setTimeout(function() {
                            $('#dg').datagrid('fixDetailRowHeight', index);
                        }, 0);
                    }
                });
                $('#dg').datagrid('fixDetailRowHeight', index);
            }
        });
    });

    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData) {
            var cc = [];
            cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
            if (!frozen) {
                var aa = rowData.gambar;
                cc.push('<img src="${pageContext.request.contextPath}/upload/pasangancalon/' + aa + '" style="width:150px;float:left">');
                cc.push('<div style="float:left;margin-left:20px;"><table>');
                for (var i = 1; i < fields.length; i++) {
                    var copts = $(target).datagrid('getColumnOption', fields[i]);
                    cc.push('<tr><td style="padding:5px;border:none;"><strong>' + copts.title + '</strong></td><td style="padding:5px;border:none;"><strong>:</strong></td>\n\
                    <td style="padding:5px;border:none;"><strong>' + rowData[fields[i]] + '</strong></td>');
                }
                cc.push('</table></div>');
            }
            cc.push('</td>');
            return cc.join('');
        }
    });
</script>
<div class="manajemendata">
    <table id="dg" class="easyui-datagrid" 
           url="${pageContext.request.contextPath}/rekapitulasi/kelurahan/tampil" toolbar="#tb"
           singleSelect="true" pagination="true" fitColumns="true" fit="true">
        <thead>
            <tr>
                <th field="namakecamatan" width="60">Kecamatan</th>
                <th field="namakelurahan" width="60">Kelurahan</th>
                <th field="kodekelurahan" width="60">Kode Kelurahan</th>
                <th field="jmldpt" width="60">Jumlah DPT</th>
                <th field="suarasah" width="60">Suara Sah</th>
                <th field="suaratidaksah" width="60" >Suara Tidak Sah</th>    
            </tr>
        </thead>
    </table>
    <div id="resize" class="easyui-dialog" style="width:400px;height:330px;padding:10px 20px"
         closed="true" buttons="#dlg-buttons">
    </div>
    <div id="tb" style="padding:3px">
        <div style="margin-left: 5px">
            <form id="cariform">
                <span><b>Nama Kecamatan:</b></span>
                <input id="cc2" class="easyui-combobox" data-options="  
                       valueField: 'kodekecamatan',  
                       textField: 'namakecamatan',  
                       url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',  
                       onSelect: function(rec){  
                       var url = '${pageContext.request.contextPath}/kelurahan/listkelurahan/'+rec.kodekecamatan;  
                       $('#cc').combobox('clear');
                       $('#cc').combobox('reload', url);  
                       }" />  
                <span><b>Nama Kelurahan:</b></span>
                <input id="cc" class="easyui-combobox" data-options="valueField:'kodekelurahan',textField:'namakelurahan'" />  
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">Cari</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="doClearSearch()">Refresh</a>
            </form>
        </div>
    </div>                        
</div>                        



