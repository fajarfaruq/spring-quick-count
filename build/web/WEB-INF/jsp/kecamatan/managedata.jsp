<div id="breadcumb">
    <h4>Kecamatan > Manajemen Data</h4>
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
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah Kecamatan');
        $('#fm').form('clear');
        getidakhir();
        url = '${pageContext.request.contextPath}/kecamatan/managedata/tambah';
    }
    function getidakhir(){
                $.getJSON('${pageContext.request.contextPath}/kecamatan/managedata/idselanjutnya', function(data) {
            var idsel = data.idselanjutnya;
           document.getElementById('lanjut').value = idsel;
        });

    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit Kecamatan');
            $('#fms').form('load', row);
            url = '${pageContext.request.contextPath}/kecamatan/managedata/edit';
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
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus kecamatan tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/kecamatan/managedata/hapus', {id: row.kodekecamatan}, function(result) {
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
        getidakhir();
    }
    function doSearch() {
        $('#dg').datagrid('load', {
            kodekecamatan: $('#kodekecamatan').val(),
            namakecamatan: $('#namakecamatan').val()
        });
        $('#cariform').form('clear');
    }
    function doClearSearch() {
        $('#cariform').form('clear');
        doSearch();
    }
</script>

    <div class="manajemendata">
        <table id="dg" class="easyui-datagrid" 
               url="${pageContext.request.contextPath}/kecamatan/tampil"
               toolbar="#tb"
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="kodekecamatan" width="60">Kode Kecamatan</th>
                    <th field="namakecamatan" width="60">Nama Kecamatan</th>
                </tr>
            </thead>
        </table>

        <div id="tb" style="padding:3px">
            <div>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah Kecamatan</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit Kecamatan</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus Kecamatan</a>

            </div>
            <div style="margin-left: 5px;">
                <form id="cariform">
                    <span><b>Kode Kecamatan:</b></span>
                    <input id="kodekecamatan"  maxlength="5">
                    <span><b>Nama Kecamatan:</b></span>
                    <input id="namakecamatan" >
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">Cari</a>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="doClearSearch()">Refresh</a>
                </form>
            </div>
        </div>

        <div id="resize" class="easyui-dialog" style="width:300px;height:300px;padding:10px 20px"
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
    <div class="ftitle">Informasi Kecamatan</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>Kode Kecamatan:</label>
            <input name="kodekecamatan" id="lanjut" maxlength="5" required="true" readonly>
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input name="namakecamatan" class="easyui-validatebox" maxlength="20" required="true">
        </div>
    </form>
</div>

<div id="dlgedit" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi Kecamatan</div>
    <form id="fms" method="post" novalidate>
        <div class="fitem">
            <label>Kode Kecamatan:</label>
            <input name="kodekecamatan" class="easyui-validatebox" maxlength="5" required="true" readonly>
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input name="namakecamatan" class="easyui-validatebox" maxlength="20" required="true">
        </div>
    </form>
</div>