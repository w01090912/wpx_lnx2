<%@page pageEncoding="UTF-8" contentType="text/html; UTF_8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%-- ECharts插件 --%>
    <script type="text/javascript" src="${app}/static/ECharts/EChartsjs/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${app}/static/ECharts/EChartsjs/echarts.js"></script>
</head>
<body>
<div id="pic_echarts" style="height: 500px;width: 600px;"></div>
<script type="application/javascript">

    /*cacher();*/
    //加载页面获取数据


    var dom = document.getElementById("pic_echarts");
    var myChart = echarts.init(dom);

    var  option = {
        title: {
            text: '部门男女比例统计',

        },
        tooltip: {},
        legend: {
            data: ['女','男']
        },
        xAxis: {
            data: ["科研部", "人事部", "财务部"]
        },
        yAxis: {},
        series: []

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.post("${app}/emp/echartsEmp", function (data) {
        console.log(data);
        myChart.setOption({
            series: [{
                name: '男',
                type: 'bar',
                data: data.men
            },
                {
                    name: '女',
                    type: 'bar',
                    data: data.woMen
                }]
        })
    }, "json");





</script>

</body>
</html>