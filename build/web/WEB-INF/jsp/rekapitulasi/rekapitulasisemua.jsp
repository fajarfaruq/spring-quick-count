<div id="breadcumb">
    <h4>Rekapitulasi > Hasil Akhir</h4>
 <br />
    <a href="javascript:poptastic('${pageContext.request.contextPath}/rekapitulasi/semua/print');" class="easyui-linkbutton" data-options="iconCls:'icon-print'">Cetak</a>
    <br />
</div>
<br/>
<div class="clear"></div> <!-- End .clear -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<style type="text/css">
    #fm{
        margin:0;
        padding:10px 20px;
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
        width:125px;
    }
</style>
<script language="JavaScript">
     var newwindow;
    function poptastic(url)
    {
	newwindow=window.open(url,'name','scrollbars=no,width='+screen.width+',height='+screen.height+',left=0,top=0');
	if (window.focus) {newwindow.focus()}
    }
    function Validate()
    {
        var image = document.getElementById("image").value;
        if (image != '') {
            var checkimg = image.toLowerCase();
            if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
                $.messager.show({
                    title: 'Error',
                    msg: 'File harus berupa gambar dengan ekstensi .jpg,.png,.jpeg'
                });
                document.getElementById("image").focus();
                return false;
            }
        }
        return true;
    }
    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData) {
            var cc = [];
            cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
            if (!frozen) {
                var aa = rowData.gambar;
                cc.push('<img src="${pageContext.request.contextPath}/upload/pasangancalon/' + aa + '" style="width:150px;float:left">');
                cc.push('<div style="float:left;margin-left:20px;"><table>');
                for (var i = 0; i < fields.length; i++) {
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
    $(function() {
        $('#dg').datagrid({
            view: cardview
        });
    });

</script>
<hr>
        <table style="height: 100px;">
            <tr>
                <td><strong>Total DPT</strong></td>
                <td style="padding: 0 5px; ">:</td>
                <td><strong>${TotalDPT}</strong></td>
            </tr>
            <tr>
                <td><strong>Total Suara Sah</strong></td>
                <td style="padding: 0 5px; ">:</td>
                <td><strong>${TotalSuaraSah}</strong></td>
            </tr>
            <tr>
                <td><strong>Total Suara Tidak Sah</strong></td>
                <td style="padding: 0 5px; ">:</td>
                <td><strong>${TotalSuaraTidakSah}</strong></td>
            </tr>
        </table>  
    <div class="manajemendata">  
        <table id="dg" class="easyui-datagrid" title="Detail Perolehan Pasangan Calon"
               url="${pageContext.request.contextPath}/rekapitulasi/semua/tampil" 
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="kodepasangancalon" width="60">Nomor Pasangan Calon</th>
                    <th field="gubernur" width="60">Gubernur</th>
                    <th field="wakilgubernur" width="60">Wakil Gubernur</th> 
                    <th field="suarasah" width="60">Perolehan Suara</th> 
                </tr>
            </thead>
        </table>

        <div id="resize" class="easyui-dialog" style="width:100px;height:200px;padding:10px 20px"
             closed="true" buttons="#dlg-buttons">
        </div>
    </div>

