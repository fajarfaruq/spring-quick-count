<div id="breadcumb">
    <h4>Kelurahan > Manajemen Data</h4>
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
        function getidakhir(){
                $.getJSON('${pageContext.request.contextPath}/kelurahan/managedata/idselanjutnya', function(data) {
            var idsel = data.idselanjutnya;
           document.getElementById('lanjut').value = idsel;
        });

    }
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah Kelurahan');
        $('#fm').form('clear');
        getidakhir();
        url = '${pageContext.request.contextPath}/kelurahan/managedata/tambah';
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit Kelurahan');
            $('#fms').form('load', row);
            url = '${pageContext.request.contextPath}/kelurahan/managedata/edit';
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
        setTimeout('getidakhir()',500);        
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
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus kelurahan tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/kelurahan/managedata/hapus', {id: row.kodekelurahan}, function(result) {
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
            kodekelurahan: $('#kodekelurahan').val(),
            kodekecamatan: $('#cc2').combobox('getValue'),
            namakelurahan: $('#namakelurahan').val()

        });
    }
    function doClearSearch() {
        $('#cariform').form('clear');
        doSearch();
    }
    $(function() {
        $('#cc').combobox({
            url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',
            valueField: 'kodekecamatan',
            textField: 'namakecamatan'
        });
    });
    $(function() {
        $('#cc1').combobox({
            url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',
            valueField: 'kodekecamatan',
            textField: 'namakecamatan'
        });
    });

</script>
    <div class="manajemendata">
        <table id="dg" class="easyui-datagrid" 
               url="${pageContext.request.contextPath}/kelurahan/tampil"
               toolbar="#tb"
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="kodekelurahan" width="60">Kode Kelurahan</th>
                    <th field="kodekecamatan" width="60">Kecamatan</th>
                    <th field="namakelurahan" width="60">Nama Kelurahan</th>
                </tr>
            </thead>
        </table>
        <div id="tb" style="padding:3px">
            <div>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah Kelurahan</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit Kelurahan</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus Kelurahan</a>
            </div>
            <div style="margin-left: 5px;">
                <form id="cariform">
                    <span><b>Kode Kelurahan:</b></span>
                    <input id="kodekelurahan" maxlength="5">
                    <span><b>Nama Kecamatan:</b></span>
                    <input id="cc2" class="easyui-combobox" name="kodekecamatan"  
                           data-options="valueField:'kodekecamatan',textField:'namakecamatan'
                           ,url:'${pageContext.request.contextPath}/kecamatan/listkecamatan'" />  
                    <span><b>Nama Kelurahan:</b></span>
                    <input id="namakelurahan" >
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
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Informasi Kelurahan</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>Kode Kelurahan:</label>
            <input name="kodekelurahan" id="lanjut" maxlength="5" required="true" >
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="cc2" class="easyui-combobox" name="kodekecamatan"  
                   data-options="valueField:'kodekecamatan',textField:'namakecamatan'
                   ,url:'${pageContext.request.contextPath}/kecamatan/listkecamatan'" />  
        </div>
        <div class="fitem">
            <label>Nama Kelurahan:</label>
            <input name="namakelurahan" class="easyui-validatebox" maxlength="20" required="true">
        </div>
    </form>
</div>

<div id="dlgedit" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi Kelurahan</div>
    <form id="fms" method="post" novalidate>
        <div class="fitem">
            <label>Kode Kelurahan:</label>
            <input name="kodekelurahan" maxlength="5" required="true" readonly>
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="cc2" class="easyui-combobox" name="kodekecamatan"  
                   data-options="valueField:'kodekecamatan',textField:'namakecamatan'
                   ,url:'${pageContext.request.contextPath}/kecamatan/listkecamatan'" />  <span style="color: red;"> << Pilih Sekali Lagi</span>
        </div>
        <div class="fitem">
            <label>Nama Kelurahan:</label>
            <input name="namakelurahan" class="easyui-validatebox" maxlength="20" required="true">
        </div>
    </form>
</div>