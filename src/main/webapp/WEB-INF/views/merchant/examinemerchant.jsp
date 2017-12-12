<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <title>商户审核管理</title>
    <meta charset="utf-8">
    <script src="<%=basePath%>/static/js/jquery-2.2.0.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/css/trip.css">
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <%--表格样式--%>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/bootstrap-table/bootstrap-table.css">
    <link rel="stylesheet" href="<%=basePath%>/static/font-awesome/css/font-awesome.min.css">
    <%--表格JS--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-table/bootstrap-table.js"></script>
    <%--语言包--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <%--提示框--%>
    <script src="<%=basePath%>/static/js/jquery.noty.packaged.min.js"></script>
    <script src="<%=basePath%>/static/js/showinfo.js"></script>
    <%--自建公共js文件--%>
    <script type="text/javascript" src="<%=basePath%>/static/js/common-creat.js"></script>
    <style>
        .modal-title{
            font-size: 18px;
            color: #337AB7;
            font-weight: 700;
            height:20px;
            line-height: 20px;
        }
        .modal-body{
            padding:15px 40px;
        }
        .form-group label{
            font-weight: normal;
        }
        ul.ztree{
            max-height: 152px;
            max-width: 150px;
        }
        #toolbar>button.btn-info{
            background-color:#58ABD1;
        }
        #toolbar>button.btn-info:hover{
            background-color: #3092B8;
        }
    </style>
</head>
<script >
    /**
     * 初始化数据
     */
    $(document).ready(function () {
        //调用函数，初始化数据
        var merchId=$("#merchId").val();
        $.ajax({
            url:"<%=basePath%>/merchant/getExamineMerchantList.do",
            type:"POST",
            data:{
                merchId:merchId
            },
            success:function(data){
                if(data!=="failed"){
                    var msg=eval("("+data+")");
                    $("#merchname").text(msg[0].merchname);
                    $("#username").text(msg[0].username);
                    $("#userIcCode").text(msg[0].userIcCode);
                    $("#telephone").text(msg[0].telephone);
                    $("#bondvalue").text(msg[0].bondvalue);
                    $("#builddatetime").text(msg[0].builddatetime);
                    $("#merchscroe").text(msg[0].merchscroe);
                }else{
                    errorInfo("获取数据失败");
                }
            }
        });
    });
</script>
<body id="loading" class="style_body">
<img src="<%=basePath%>/static/img/bac1.png" style="position: fixed;top: -20px;left:-20px;width:10%;opacity: 0.3;">
<img src="<%=basePath%>/static/img/bac2.png" style="position: fixed;bottom: -20px;left:0;width:10%;opacity: 0.5;">
<img src="<%=basePath%>/static/img/bac3.png" style="position: fixed;top: 0;right:0;width:15%;opacity: 0.3;">
<img src="<%=basePath%>/static/img/bac4.png" style="position: fixed;bottom: 0;right:0;width:12%;opacity: 0.3;">
<div class="container" >
    <div class="style_border">
        <input type="hidden" id="merchId" value="<%= request.getAttribute("id")%>">
        <div class=" font14_row">
            <div>
                <h4 class="font14 control-label" >会员信息</h4>
                <div>
                    <div class="col-md-2" style="width:20%">
                        <div class="control-group">
                            <label>会员姓名:</label>
                            <label class="control-label" id="username" >
                            </label>
                        </div>
                    </div>
                    <div class="col-md-2" style="width:30%">
                        <div class="control-group">
                            <label>会员电话:</label>
                            <label class="control-label" id="telephone"></label>
                        </div>
                    </div>
                    <div class="col-md-2" style="width:30%">
                        <div class="control-group">
                            <label>身份证:</label>
                            <label class="control-label" id="userIcCode"></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="font14_row">
            <h4 class="font14">账户信息</h4>
            <div id="parentDiv" style="overflow:auto;">
                    <div class="col-md-2">
                        <div class="control-group">
                            <label>银行开户名:</label>
                            <label class="control-label" id="itemname"></label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="control-group">
                            <label>银行账号:</label>
                            <label class="control-label" id="itemname1"></label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="control-group">
                            <label>银行名称:</label>
                            <label class="control-label" id="itemname2"></label>
                        </div>
                    </div>
            </div>
        </div>
        <div class=" font14_row" style="height:85px;line-height:25px;margin-bottom: 20px;padding:25px 20px">
            <h4 class="font14">店铺信息</h4>
            <div class="col-md-2" style="width:50%">
                <div class="control-group">
                    <label>店铺名称:</label>
                    <label class="control-label" id="merchname" >
                    </label>
                </div>
            </div>
            <div class="col-md-2" style="width:21%">
                <div class="control-group">
                    <label>押金:</label>
                    <label class="control-label" id="bondvalue"></label>
                </div>
            </div>
            <div class="col-md-2" style="width:50%">
                <div class="control-group">
                    <label>申请时间:</label>
                    <label class="control-label" id="builddatetime"></label>
                </div>
            </div>
            <div class="col-md-2" style="width:20%">
                <div class="control-group">
                    <label>等级:</label>
                    <label class="control-label" id="merchscroe"></label>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>
<%--模态弹窗引用jquery-ui设置可拖动--%>
<script src="<%=basePath%>/static/js/jquery-ui.min.js"></script>
<script>
    $(document).ready(function(){
        $(".modal-content").draggable({ cursor: "move" });//为模态对话框添加拖拽
    })
</script>
</body>