<div id="breadcumb">
    <h4>User >> Kecamatan</h4>
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
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah User Kecamatan');
        $('#fm').form('clear');
        url = '${pageContext.request.contextPath}/user/userkecamatan/tambah';
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit User Kecamatan');
            $('#fms').form('load', row);
            url = '${pageContext.request.contextPath}/user/userkecamatan/edit';
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
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus user kecamatan tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/user/userkecamatan/hapus', {id: row.username}, function(result) {
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
            username: $('#username').val(),
            kodekecamatan: $('#cc').combobox('getValue')
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
    $(function() {
        $('#cc2').combobox({
            url: '${pageContext.request.contextPath}/kecamatan/listkecamatan',
            valueField: 'kodekecamatan',
            textField: 'namakecamatan'
        });
    });
</script>
    <div class="manajemendata">
        <table id="dg" class="easyui-datagrid" 
               url="${pageContext.request.contextPath}/user/userkecamatan/tampil"
               toolbar="#tb"
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="username" width="60">Username</th>
                    <th field="kodekecamatan" width="60">Kode Kecamatan</th>         
                </tr>
            </thead>
        </table>

        <div id="tb" style="padding:3px">
            <div>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah User</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus User</a>
            </div>
            <div>
                <form id="cariform">
                    <span><b>Username:</b></span>
                    <input id="username" maxlength="5">
                    <span><b>Nama Kecamatan:</b></span>
                    <input id="cc">  
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
    <div class="ftitle">Informasi User Kecamatan</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>Username:</label>
            <input name="username" class="easyui-validatebox" maxlength="15" required="true" >
        </div>
        <div class="fitem">
            <label>Password:</label>
            <input name="password" class="easyui-validatebox" maxlength="20" type="password" required="true">
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="cc2" class="easyui-combobox" name="kodekecamatan"  
                   data-options="valueField:'kodekecamatan',textField:'namakecamatan'
                   ,url:'${pageContext.request.contextPath}/kecamatan/listkecamatan'" />  
        </div>          
    </form>
</div>

<div id="dlgedit" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi User Kecamatan</div>
    <form id="fms" method="post" novalidate>
        <div class="fitem">
            <label>Username:</label>
            <input name="username" class="easyui-validatebox" maxlength="15" required="true" readonly>
        </div>
        <div class="fitem">
            <label>Password:</label>
            <input name="password" class="easyui-validatebox" maxlength="20" type="password" required="true">
        </div>
        <div class="fitem">
            <label>Nama Kecamatan:</label>
            <input id="cc2" class="easyui-combobox" name="kodekecamatan"  
                   data-options="valueField:'kodekecamatan',textField:'namakecamatan'
                   ,url:'${pageContext.request.contextPath}/kecamatan/listkecamatan'" />  
        </div>          
    </form>
</div>