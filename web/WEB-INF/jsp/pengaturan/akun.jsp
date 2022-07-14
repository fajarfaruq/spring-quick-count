<div id="breadcumb">
    <h4>Pengaturan > Profil Akun</h4>
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.6.2.min.js"></script>
<style type="text/css">
    #ff label{
        display:block;
        width:100px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function() {
        $('#ff').form({
            url: '${pageContext.request.contextPath}/user/useradmin/edit',
            onSubmit: function() {
                    return $(this).form('validate');
            },
            success: function(result) {
                var result = eval('(' + result + ')');
                if (result.success) {
                    $.messager.show({
                        title: 'Sukses',
                        msg: result.msg
                    });
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            }
        });
    });
</script>
</head>
<body>

    <div style="width:230px;background:#fafafa;padding:10px;">
        <form id="ff" method="post" novalidate>
            <div>
                <label for="name">Username</label>
                <input class="easyui-validatebox" type="text" name="username" required="true" readonly value="${user}"></input>
            </div>
            <div>
                <label for="email">Password</label>
                <input class="easyui-validatebox" type="password" name="password" id="pass1" required="true" ></input>
            </div>
            <br/>
            <div>
                <input type="submit" value="Submit">
            </div>
        </form>
    </div>