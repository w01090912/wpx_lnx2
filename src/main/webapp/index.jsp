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
    <link rel="stylesheet" href="${app}/static/boot/css/bootstrap.css">
    <link rel="stylesheet" href="${app}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${app}/static/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${app}/static/boot/js/bootstrap.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${app}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

    <title>登录</title>
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
        }
        .container{
            display:table;
        }

        .row{
            display: table-cell;
            vertical-align: middle;

        }

        .row-centered {
            text-align:center;
        }
        .col-centered {
            display:inline-block;
            float:none;
            text-align:left;
            margin-right:-4px;
        }
    </style>

</head>
<body>

<div class="container">
    <div class="row row-centered ">
        <div class="well col-md-6 col-centered">
            <h2>欢迎登录</h2>
            <c:if test="${message!=null}">
                <div class="alert alert-danger" role="alert">${message}</div>
            </c:if>
            <form action="${app}/user/login" method="post" role="form">
                <div class="input-group input-group-md">
                    <span class="input-group-addon" ><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                    <input type="text" class="form-control"  name="username"/>
                </div>
                <div class="input-group input-group-md">
                    <span class="input-group-addon" ><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" class="form-control"  name="password"/>
                </div>
                <br/>
                <button type="submit" class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
