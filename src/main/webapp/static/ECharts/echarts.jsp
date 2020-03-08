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
    <title>ECharts三方技术集成</title>
    <%-- jquery插件 --%>
    <script src="${app}/ECharts/EChartsjs/jquery-3.3.1.min.js"></script>
    <script src="${app}/ECharts/EChartsjs/echarts.min.js"></script>
    <%-- map地图js --%>

</head>
<body>
<div id="pic_echarts" style="height: 500px;width: 600px;margin: 0 auto;"></div>
<script type="application/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('pic_echarts'));

    $.ajax({
        url: "",
        type: "POST",
        datatype: "json",
        data: "id=" + 2,
        success: function (resource) {
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例',

                },
                tooltip: {},
                legend: {
                    data: ['销量']
                },
                xAxis: {
                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });





</script>

</body>
</html>