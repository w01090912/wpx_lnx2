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


    <style type="text/css">
        .diva {
            padding-bottom: 20px;
        }
        .divb {
            float: left;
            padding-bottom: 20px;
            padding-right: 20px;
        }
        .divc {

            height: 225px;
            line-height: 225px;
            width: 225px;
            float: left;
            margin-left: 40px;
        }
        .divc button{
            vertical-align: middle;
            margin-left:40px;
            margin-right: 40px;
        }
    </style>
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

            $("#table1").jqGrid(
                {
                    styleUI: "Bootstrap",//开启Bootstrap风格
                    url: "${app}/section/show",

                    colNames: ['部门ID', '部门'],
                    colModel: [
                        {name: 'section_id', index: 'section_id', align: "center", editable: true},
                        {name: 'section_name', index: 'section_name', align: "center", editable: true},
                    ],
                    datatype: "json",
                    height: 150,
                    width: 500,
                    rowNum: 100,
                    rownumbers: true,
                    rownumWidth: 40,
                    caption: "部门信息"
                }).jqGrid("navGrid"), {edit: false, add: false, del: false}

            $("#table2").jqGrid({

                url: "${app}/emp/show",
                datatype: "json",
                colNames: ['姓名', '年龄', '性别', '照片', '职务', '部门'],
                colModel: [
                    {name: 'empName', index: 'empName', align: "center", editable: true},
                    {name: 'empAge', index: 'empAge', align: "center", editable: true},
                    {
                        name: 'empSex',
                        index: 'empSex',
                        align: "center",
                        editable: true,
                        edittype: "select",
                        editoptions: {value: "男:男;女:女"}
                    },
                    {
                        name: 'empImage',
                        editable: true,
                        align: 'center',
                        edittype: 'file',
                        formatter: function (cellvalue, options, rowObject) {
                            return "<a href='${app}/static/img/" + cellvalue + "' download ='" + cellvalue + "'>" +
                                "<img src='${app}/static/img/" + cellvalue + "' width='40%'/>" +
                                "</a>";
                        }
                    },
                    {
                        name: 'empPost',
                        index: 'empPost',
                        align: "center",
                        editable: true,
                        edittype: "select",
                        editoptions: {value: "员工:员工;部长:部长;副部长:副部长"}
                    },
                    {
                        name: 'section.section_name',
                        index: 'section',
                        align: "center",
                        editable: true,
                        edittype: "select",
                        editoptions: {value: "1:科研部;2:人事部;3:财务部"}
                    }
                        /*formatter: function (cellvalue, options, rowObject) {
                            return "";
                        }*/

                ],
                styleUI: "Bootstrap",//开启Bootstrap风格
                height: "100%",
                autowidth: true,
                pager: "#pager",//开启分页的工具
                rowNum: 3,//决定每页展示的条数
                rowList: [3, 6, 9, 12],//下拉框选择每页展示的条数
                viewrecords: true,//是否展示总记录数
                caption: "员工信息",
                editurl: "${app}/emp/edit"
            }).navGrid("#pager", {edit: true, add: true, del: true},
                {
                    //修改的额外控制
                    closeAfterEdit: true,   //修改完成关闭修改框
                    afterSubmit: function (response) {   //上传修改的图片
                        var status = response.responseJSON.status;
                        var id = response.responseJSON.id;
                        if (status == "ok") {
                            console.log("文件上传状态：" + status)
                            $.ajaxFileUpload({
                                url: '${app}/emp/upload',   //上传的方法
                                fileElementId: 'empImage',   //上传文件的属性名
                                data: {id: id},  //要修改的banner的id
                                type: 'post',
                                success: function () {
                                    console.log("文件上传成功")
                                    $('#table2').trigger('reloadGrid');   //刷新表格
                                }
                            });
                        }
                        return "12312";   //这里返回任意数字字符串
                    }
                }, {
                    //添加的额外控制
                    closeAfterAdd: true,    //关闭添加框
                    afterSubmit: function (response) {   //上传添加的图片
                        var status = response.responseJSON.status;
                        var id = response.responseJSON.id;
                        if (status == "ok") {
                            console.log("文件上传状态：" + status)
                            $.ajaxFileUpload({
                                url: '${app}/emp/upload',
                                fileElementId: 'empImage',
                                data: {id: id},
                                type: 'post',
                                success: function () {
                                    $("#table2").trigger("reloadGrid");  //刷新表格
                                }
                            });
                        }
                        return "12312";
                    }

                })
        })
        $('#myButton').on('click', function () {
            var $btn = $(this).button('loading')
            // business logic...
            $btn.button('reset')
        })
    </script>
</head>
<body>
<div class="diva">
    <table id="table2"></table>
    <div id="pager"></div>
</div>
<div class="divb">
    <table id="table1"></table>
</div>
<div class="divc">
    <a href="${app}/emp/download">
        <button type="button" id="myButton" data-loading-text="正在下载..." class="btn btn-primary" autocomplete="off">
            导出在职员工信息
        </button>
    </a>
</div>

</body>
</html>