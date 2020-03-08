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
    <title>测试案例</title>
    <link rel="stylesheet" href="${app}/static/boot/css/bootstrap.css">
    <link rel="stylesheet" href="${app}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${app}/static/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${app}/static/boot/js/bootstrap.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

    <%-- 文件上传插件 --%>
    <script src="${app}/static/jqgrid/js/ajaxfileupload.js"></script>


</head>
<body>
<div class="row">
    <div style="text-align: center" class="col-lg-2 panel-group" id="accordion" role="tablist"
         aria-multiselectable="true">

        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading4">
                <h4 class="panel-title">
                    <div class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                         href="#collapse5" aria-expanded="false" aria-controls="collapseThree">
                        案例
                    </div>
                </h4>
            </div>
            <div id="collapse5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading4">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <a href="javascript:$('#contentShow').load('${app}/views/showUser.jsp')">案例一</a>
                        </li>
                        <li>
                            <a href="javascript:$('#contentShow').load('${app}/views/showEmp.jsp')">案例二</a>
                        </li>
                        <li>
                            <a href="javascript:$('#contentShow').load('${app}/views/showUser-echar.jsp')">案例三</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-10 ">
    <div class="col-sm-9 col-sm-offset-1" id="contentShow">
        <img src="../static/img/33.jpg"  height="70%" width="50%">
    </div>
    </div>
</div>
</body>
</html>