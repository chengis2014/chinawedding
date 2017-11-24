<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="<%=basePath%>/static/js/jquery-2.2.0.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css">
    <link href="<%=basePath%>/static/bootstrap/flatlab-master/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/static/font-awesome/css/font-awesome.min.css">

    <link href="<%=basePath%>/static/css/selectuser.css" rel="stylesheet">
    <!--引入CSS-->
    <%--提示框--%>
    <script src="<%=basePath%>/static/js/jquery.noty.packaged.min.js"></script>

    <script src="<%=basePath%>/static/js/showinfo.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="<%=basePath%>/static/js/jquery.simplemodal.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/static/js/selectbox.js"></script>
    <script src="<%=basePath%>/static/js/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function() {
        });
    </script>
    <style>
        .bac_bac{
            width: 100%;
            height:100%;
            background: url(<%=basePath%>/static/img/shbj3.jpg );
            background-repeat:no-repeat;
            background-position:center;
            position: absolute;
            border:6px solid #ffffff;
        }
        /*欢迎字体*/
        .bac_text {
            position: absolute;
            top: 50%;
            margin-top: -120px;
            left: 50%;
            margin-left: -270px;
            text-align: center;
            font-size: 42px;
            color:#474b4c;
            z-index: 3;
            padding: 20px;
            font-weight: 700;
            text-shadow: 0 0 2px #f2f7fd;
            /*box-shadow: 0 0 10px #18242D;*/
            /*border: 2px solid #ffffff;*/
        }

    </style>
    <title>首页</title>
</head>
<body>

<div class="bac_bac"></div>
<div class="bac_text">欢迎登录<br>管理系统</div>

</div>
</body>
</html>
