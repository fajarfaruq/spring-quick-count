<div id="breadcumb">
    <h4>TPS > Manajemen Data</h4>
</div>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/demo.css">
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
        width:80px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var url;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah TPS');
        $('#fm').form('clear');
        url = '${pageContext.request.contextPath}/tps/managedata/tambah';
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit TPS');
            $('#fms').form('load', row);
            url = '${pageContext.request.contextPath}/tps/managedata/edit';
        }
    }

    //for save
    function saveUser() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function() {
                return $(this).form('validate');
            },
            success: function(result) {
                var result = eval('(' + result + ')');
                if (result.success) {
                    //$('#dlg').dialog('close');		// close the dialog
                    $('#dg').datagrid('reload');	// reload the user data
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            }
        });
        $('#fm').form('clear');
    }
    //for edit
    function updateUser() {
        $('#fms').form('submit', {
            url: url,
            onSubmit: function() {
                return $(this).form('validate');
            },
            success: function(result) {
                var result = eval('(' + result + ')');
                if (result.success) {
                    $('#dlgedit').dialog('close');		// close the dialog
                    $('#dg').datagrid('reload');	// reload the user data
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            }
        });
    }


    function removeUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus tps tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/tps/managedata/hapus', {id: row.kodetps}, function(result) {
                        if (result.success) {
                            $('#dg').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({// show error message
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }
    function doSearch() {
        $('#dg').datagrid('load', {
            kodetps: $('#kodetps').val(),
            kodekecamatan : $('#cc2').combobox('getValue'),
            kodekelurahan: $('#cc').combobox('getValue')
        });
    }
    function doClearSearch() {
        $('#cariform').form('clear');
        doSearch();
    }

</script>
    <div class="manajemendata">
        <table id="dg" class="easyui-datagrid" 
               url="${pageContext.request.contextPath}/tps/tampil"
               toolbar="#tb"
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="namakecamatan" width="60">Nama Kecamatan</th>
                    <th field="namakelurahan" width="60">Nama Kelurahan</th>
                    <th field="nomortps" width="60">Nomor TPS</th>
                    <th field="jmldpt" width="60">Jumlah DPT</th>
                </tr>
            </thead>
        </table>
        <div id="tb" style="padding:3px">
            <div>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah TPS</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit TPS</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus TPS</a>
            </div>
            <div style="margin-left: 5px">
                <form id="cariform">
                    <span><b>Kode TPS:</b></span>
                    <input id="kodetps" maxlength="5">
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

        <div id="resize" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
             closed="true" buttons="#dlg-buttons">
        </div>
        <div id="dlg-buttons">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Simpan</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Batal</a>
        </div>   

        <div id="dlg-buttons1">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateUser()">Simpan</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgedit').dialog('close')">Batal</a>
        </div>
    </div>
<div id="dlg" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Informasi TPS</div>
    <form id="fm" method="post" novalidate>
      <!--  <div class="fitem">
            <label>Kode TPS:</label>
            <input name="kodetps" class="easyui-validatebox" maxlength="5" required="true" >
        </div> -->
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="ccinsert1" class="easyui-combobox" name="kodekecamatan" data-options="  
                   valueField: 'kodekecamatan',  
                   textField: 'namakecamatan',  
                   url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',  
                   onSelect: function(rec){  
                   var url = '${pageContext.request.contextPath}/kelurahan/listkelurahan/'+rec.kodekecamatan;  
                   $('#ccinsert2').combobox('clear');
                   $('#ccinsert2').combobox('reload', url);  
                   }" />  
        </div>
        <div class="fitem">
            <label>Nama Kelurahan:</label>
            <input id="ccinsert2" class="easyui-combobox" name="kodekelurahan" data-options="valueField:'kodekelurahan',textField:'namakelurahan'" /> 
        </div>  
        <div class="fitem">
            <label>Nomor TPS:</label>
            <input name="nomortps" class="easyui-numberbox" maxlength="5" required="true" >
        </div>   
        <div class="fitem">
            <label>Jumlah DPT:</label>
            <input name="jmldpt" class="easyui-numberbox" maxlength="5" required="true" >
        </div>            
    </form>
</div>
<div id="dlgedit" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi TPS</div>
    <form id="fms" method="post" novalidate>
        <div class="fitem">
            <label>Kode Kecamatan:</label>
            <input name="kodetps" class="easyui-validatebox" maxlength="5" required="true" readonly>
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="ccinsert3" class="easyui-combobox" name="kodekecamatan" data-options="  
                   valueField: 'kodekecamatan',  
                   textField: 'namakecamatan',  
                   url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',  
                   onSelect: function(rec){  
                   var url = '${pageContext.request.contextPath}/kelurahan/listkelurahan/'+rec.kodekecamatan;  
                   $('#ccinsert4').combobox('clear');
                   $('#ccinsert4').combobox('reload', url);  
                   }" />  <span style="color: red;"> << Pilih Sekali Lagi</span>
        </div>
        <div class="fitem">
            <label>Nama Kelurahan:</label>
            <input id="ccinsert4" class="easyui-combobox" name="kodekelurahan" data-options="valueField:'kodekelurahan',textField:'namakelurahan'" /> <span style="color: red;"> << Pilih Sekali Lagi</span>
        </div>  
        <div class="fitem">
            <label>Nomor TPS:</label>
            <input name="nomortps" class="easyui-numberbox" maxlength="5" required="true" >
        </div>   
        <div class="fitem">
            <label>Jumlah DPT:</label>
            <input name="jmldpt" class="easyui-numberbox" maxlength="5" required="true" >
        </div>                      
    </form>
</div>