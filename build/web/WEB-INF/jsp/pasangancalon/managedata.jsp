<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="breadcumb">
    <h4>Pasangan Calon > Manajemen Data</h4>
</div>
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
<script type="text/javascript">
    var url;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah Pasangan Calon');
        $('#fm').form('clear');
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit Pasangan Calon');
            $('#fms').form('load', row);
            url = '${pageContext.request.contextPath}/pasangancalon/managedata/edit';
        }
    }

    //for save
    function saveUser() {
        var imgVal = $('#image').val();
        if (imgVal == '')
        {
            $.messager.show({
                title: 'Error',
                msg: 'Gambar Harus Diisi .. !!'
            });
        } else {
            $('#fm').submit();
            $('#fm').form('clear');
        }
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
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus pasangancalon tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/pasangancalon/managedata/hapus', {id: row.kodepasangancalon}, function(result) {
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

    ${alert}
</script>
    <div class="manajemendata">
        <table id="dg" class="easyui-datagrid" 
               url="${pageContext.request.contextPath}/pasangancalon/tampil"
               toolbar="#tb"
               rownumbers="true" pagination="true" fitColumns="true" fit="true" singleSelect="true" pageSize="20">
            <thead>
                <tr>
                    <th field="kodepasangancalon" width="60">Nomor Pasangan Calon</th>
                    <th field="gubernur" width="60">Gubernur</th>
                    <th field="wakilgubernur" width="60">Wakil Gubernur</th>
                    <th field="gambar" width="60">Foto</th>
                </tr>
            </thead>
        </table>
        <div id="tb" style="padding:3px">
            <div>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus</a>
            </div>
        </div>

        <div id="resize" class="easyui-dialog" style="width:250px;height:300px;padding:10px 20px"
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
<div id="dlg" class="easyui-dialog" style="width:450px;height:310px;padding:10px 10px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Informasi Pasangan Calon</div>
    <form:form modelAttribute="pasangancalonhandle" id="fm" method="post" 
               enctype="multipart/form-data" onSubmit="return Validate();" >

        <div class="fitem">
            <label>Nomor Pasangan Calon</label>
            <form:input path="kodepasangancalon" maxlength="2" class="easyui-numberbox" type="text" required="true" />
        </div>
        <div class="fitem">
            <label>Gubernur</label>
            <form:input path="gubernur" class="easyui-validatebox" maxlength="15" type="text" required="true" />
        </div>
        <div class="fitem">
            <label>Wakil Gubernur</label>
            <form:input path="wakilgubernur" class="easyui-validatebox" maxlength="15" type="text" required="true" />
        </div>
        <div class="fitem">
            <label>Foto Pasangan Calon</label>
            <form:input path="fileData" id="image" class="easyui-validatebox" type="file" required="true" />
        </div>
    </form:form>
</div>

<div id="dlgedit" class="easyui-dialog" style="width:450px;height:310px;padding:10px 10px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi Pasangan Calon</div>
    <form:form modelAttribute="pasangancalonhandle" id="fms" method="post" 
               enctype="multipart/form-data" onSubmit="return Validate();" >

        <div class="fitem">
            <label>Nomor Pasangan Calon</label>
            <form:input path="kodepasangancalon" class="easyui-numberbox" maxlength="2" type="text" required="true" readonly="true" />
        </div>
        <div class="fitem">
            <label>Gubernur</label>
            <form:input path="gubernur" class="easyui-validatebox" maxlength="15" type="text" required="true" />
        </div>
        <div class="fitem">
            <label>Wakil Gubernur</label>
            <form:input path="wakilgubernur" class="easyui-validatebox" maxlength="15" type="text" required="true"/>
        </div>
        <div class="fitem">
            <label>Foto Pasangan Calon</label>
            <form:input path="fileData" id="image" class="easyui-validatebox" type="file" required="true"  />
        </div>
    </form:form>
</div>
