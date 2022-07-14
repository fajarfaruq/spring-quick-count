<div id="breadcumb">
    <h4>Perolehan Suara > TPS</h4>
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
    var url;
    var baris;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', 'Tambah Suara TPS');
        $('#fm').form('clear');
        url = '${pageContext.request.contextPath}/perolehansuara/tps/tambah';
    }
    function editUser() {
        baris = $('#dg').datagrid('getSelected');
        if (baris) {
            $('#dlgedit').dialog('open').dialog('setTitle', 'Edit Suara TPS');
            $('#fms').form('load', baris);
            $('#dg1').edatagrid({
                url: '${pageContext.request.contextPath}/perolehansuara/tps/tampildetail/' + baris.kodetps,
                updateUrl: '${pageContext.request.contextPath}/perolehansuara/tps/updatesuarasah',
            });
            url = '${pageContext.request.contextPath}/perolehansuara/tps/updatesuaratidaksah';
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
            $.messager.confirm('Konfirmasi', 'Apakah anda yakin akan menghapus suara tps tersebut?', function(r) {
                if (r) {
                    $.post('${pageContext.request.contextPath}/perolehansuara/tps/hapus', {id: row.kodetps}, function(result) {
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
                    url: '${pageContext.request.contextPath}/perolehansuara/tps/tampildetail/' + row.kodetps,
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
    $(function() {
        $('#pasangancaloncombo').combogrid({
            panelWidth: 450,
            value: '006',
            fitColumns: 'true',
            idField: 'kodepasangancalon',
            textField: 'kodepasangancalon',
            url: '${pageContext.request.contextPath}/pasangancalon/listpasangancalon',
            columns: [[
                    {field: 'kodepasangancalon', title: 'Kode Pasangan', width: 100},
                    {field: 'gubernur', title: 'Gubernur', width: 100},
                    {field: 'wakilgubernur', title: 'Wakil Gubernur', width: 100}
                ]]
        });
    });
</script>
<div class="manajemendata">
    <table id="dg" class="easyui-datagrid" 
           url="${pageContext.request.contextPath}/perolehansuara/tps/tampil" toolbar="#tb"
           singleSelect="true" pagination="true" fitColumns="true" fit="true">
        <thead>
            <tr>
                <th field="namakecamatan" width="60">Kecamatan</th>
                <th field="namakelurahan" width="60">Kelurahan</th>
                <th field="nomortps" width="60">Nomor TPS</th>
                <th field="jmldpt" width="60">Jumlah DPT</th>
                <th field="suarasah" width="60">Suara Sah</th>
                <th field="suaratidaksah" width="60" >Suara Tidak Sah</th>         
            </tr>
        </thead>
    </table>
    <div id="resize" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px"
         closed="true" buttons="#dlg-buttons">
    </div>
    <div id="tb" style="padding:3px">
        <div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Tambah</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Hapus</a>
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
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Simpan</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Batal</a>
    </div>   

    <div id="dlg-buttons1">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateUser()">Simpan</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgedit').dialog('close')">Batal</a>
    </div>                           
</div>

<div id="dlg" class="easyui-dialog" style="width:700px;height:420px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Informasi Suara TPS</div>
    <form id="fm" method="post" novalidate>
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
            <input id="ccinsert2" class="easyui-combobox" name="kodekelurahan" data-options="  
                   valueField: 'kodekelurahan',  
                   textField: 'namakelurahan',  
                   onSelect: function(rec){  
                   var url = '${pageContext.request.contextPath}/tps/listtps/'+rec.kodekelurahan;  
                   $('#ccinsert3').combobox('clear');
                   $('#ccinsert3').combobox('reload', url);  
                   }" />  
        </div>  
        <div class="fitem">
            <label>Nomor TPS:</label>
            <input id="ccinsert3" class="easyui-combobox" name="kodetps" data-options="valueField:'kodetps',textField:'nomortps'" required="true" /> 
        </div>  
        <div class="fitem">
            <label>Suara Tidak Sah:</label>
            <input name="suaratidaksah" class="easyui-numberspinner" value="0" data-options="min:0,max:5000" style="width:80px;" required="true"></input>
        </div> 
        <fieldset>
            <legend>Perolehan Pasangan Calon</legend>
            <div class="fitem">
                <label>Kode Pasangan Calon</label>
                <select id="pasangancaloncombo" name="kodepasangancalon" style="width:250px;" required="true"></select>
            </div>    
            <div class="fitem">
                <label>Perolehan Suara</label>
                <input name="suarasah" class="easyui-numberspinner" value="0" data-options="min:0,max:5000" style="width:80px;" required="true"></input>
            </div>             
        </fieldset>
    </form>
</div>

<div id="dlgedit" class="easyui-dialog" style="width:600px;height:500px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <div class="ftitle">Informasi Suara TPS</div>
    <form id="fms" method="post" novalidate>
        <div class="fitem">
            <label>Kode TPS</label>
            <input name="kodetps" class="easyui-numberbox" maxlength="5" readonly="true" >
        </div>    
        <div class="fitem">
            <label>Suara Tidak Sah:</label>
            <input name="suaratidaksah" class="easyui-numberspinner" value="0" data-options="min:0,max:5000" style="width:80px;" required="true"></input>
        </div> 
        <table id="dg1" title="Pasangan Calon" style="width:500px;height:200px"
               toolbar="#toolbar1" idField="kodesuaratps" fitColumns="true"  singleSelect="true">
            <thead>
                <tr>
                    <th field="kodepasangancalon" width="50" >Kode Calon</th>
                    <th field="gubernur" width="50" >Gubnernur</th>
                    <th field="wakilgubernur" width="50" >Wakil Gubernur</th>
                    <th field="suarasah" width="50" editor="{type:'validatebox',options:{required:true}}">Suara</th>
                </tr>
            </thead>
        </table>
        <div id="toolbar1">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg1').edatagrid('saveRow');
        doClearSearch()">Simpan</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg1').edatagrid('cancelRow')">Batal</a>
        </div>
        <br />
        <div class="fitem">
            <label>Tervalidasi :</label>
            <select class="easyui-combobox" name="tervalidasi" style="width:200px;" data-options="required:true">
                <option value="Sudah">Sudah</option>
                <option value="Belum">Belum</option>
            </select>
        </div>
    </form>
</div>                           