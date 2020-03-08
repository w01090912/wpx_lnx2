<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>展示用户</title>
    <link rel="stylesheet" href="${app}/static/boot/css/bootstrap.css">
    <link rel="stylesheet" href="${app}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${app}/static/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${app}/static/boot/js/bootstrap.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

    <%-- 文件上传插件 --%>
    <script src="${app}/static/jqgrid/js/ajaxfileupload.js"></script>

    <script type="application/javascript">


        function update(rowid) {
            if (rowid != null)//gr  获取要修改行的id
            //调用修改的方法
                $("#table").jqGrid('editGridRow', rowid, {
                    height: 300,
                    reloadAfterSubmit: true
                });
            else
                alert("请选中一行");
        }

        function add(rowid) {
            if (rowid != null)//gr  获取要修改行的id
            //调用修改的方法
                $("#table").jqGrid('editGridRow', rowid, {
                    height: 300,
                    reloadAfterSubmit: true
                });
            else
                alert("请选中一行");
        }


        function del(rowid) {
            //改行的ID
            //alert(rowid)
            if (rowid != null)
                $("#table").jqGrid('delGridRow', rowid, {
                    reloadAfterSubmit: true
                });
            else
                alert("请选中一行");
        }

        $(function () {
            $("#table").jqGrid(
                {
                    styleUI: "Bootstrap",//开启Bootstrap风格
                    url: "${app}/all/show",
                    datatype: "json",
                    autowidth: true,
                    pager: "#pager",//开启分页的工具栏
                    rowNum: "3",//决定每页展示的条数
                    height: "100%",
                    rowList: ["3", "6", "9", "12"],//下拉框选择每页展示的条数
                    viewrecords: true,//是否展示总记录数
                    editurl: "${app}/all/edit",
                    colNames: ['用户ID', '用户名', '真实姓名', '年龄', '性别'],
                    colModel: [
                        {name: 'id', index: 'id', align: "center", editable: false},
                        {name: 'username', index: 'username', align: "center", editable: true},
                        {name: 'realname', index: 'realname', align: "center", editable: true},
                        {name: 'age', index: 'realname', align: "center", editable: true},
                        {
                            name: 'sex',
                            index: 'sex',
                            align: "center",
                            editable: true,
                            edittype: "select",
                            editoptions: {value: "男:男;女:女"}
                        }
                    ]

                }).jqGrid("navGrid", "#pager"), {edit: true, add: true, del: true}


        })
        ;
    </script>
</head>
<body>

<table id="table"></table>
<div id="pager"></div>


</body>
</html>