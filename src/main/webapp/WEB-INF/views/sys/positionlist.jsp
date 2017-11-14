<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <title>岗位管理</title>
    <meta charset="utf-8">
    <script src="<%=basePath%>/js/jquery-2.2.0.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/trip.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <%--表格样式--%>
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-table/bootstrap-table.css">
    <link rel="stylesheet" href="<%=basePath%>/font-awesome/css/font-awesome.min.css">
    <%--表格JS--%>
    <script src="<%=basePath%>/bootstrap/bootstrap-table/bootstrap-table.js"></script>
    <%--表格导出--%>
    <script src="<%=basePath%>/bootstrap/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
    <script src="<%=basePath%>/js/tableExport.js"></script>
    <%--语言包--%>
    <script src="<%=basePath%>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <%--提示框--%>
    <script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
    <script src="<%=basePath%>/js/showinfo.js"></script>
    <%--时间--%>
    <script type="text/javascript" src="/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="/bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.fr.js"></script>
    <!--校验-->
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/validate/css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="<%=basePath%>/bootstrap/validate/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>/bootstrap/validate/js/language/zh_CN.js"></script>
    <%--自建公共js文件--%>
    <script type="text/javascript" src="<%=basePath%>/js/common-creat.js"></script>
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
        #toolbar>button.btn-info{
            background-color:#58ABD1;
        }
        #toolbar>button.btn-info:hover{
            background-color: #3092B8;
        }
    </style>
</head>
<script >

    //初始化表格
    function initTable() {
        //先销毁表格
        $('#cusTable').bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            contentType: "application/x-www-form-urlencoded",
            url: "<%=basePath%>/position/getPositionList.do", //获取数据的Servlet地址
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            toolbar:"#toolbar",
            showRefresh: true,
            //showExport:true,
           // height:400,
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            exportDataType : "all",
            clickToSelect:true,
            //search:true,
            idField:"fid",
            //showFooter:true,
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "limit",

            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    limit: params.limit,
                    offset: params.offset
                };
                return param;
            },
            onLoadSuccess: function(){  //加载成功时执行

            },
            onLoadError: function(){  //加载失败时执行

            }
        });
    }

    /**
     * 初始化
     */
    $(document).ready(function () {
        //调用函数，初始化表格
        initTable();
        initValidate();
        $("button[title='刷新']").hide();
        $("#positionForm").submit(function(ev){ev.preventDefault();});//AJAX提交必修使用
        $('#submitBtn').click(function() {
            var bootstrapValidator = $("#positionForm").data('bootstrapValidator');//必须
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()) {
                submit();
            }else{
                alert("请按照要求填写");
                return;
            }
        });
        $(".close").click(function (){
            $('#positionForm').data('bootstrapValidator').resetForm(true);
        })
    });
    function initValidate(){
        $('#positionForm').bootstrapValidator({
            message: '值不能为空',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                fname:{
                    validators:{
                        notEmpty: {
                            message: '职位名称不能为空!'
                        },
                        regexp: {
                            regexp: /[\u4e00-\u9fa5]/,
                            message: '职位名称只能由中文字符组成'
                        }
                    }
                },
                fcode:{
                    validators:{
                        notEmpty: {
                            message: '职位代码不能为空!'
                        },
                        regexp: {
                            regexp: /^[0-9]*$/,
                            message: '职位代码只能由字母，数字，点和下划线组成'
                        }
                    }
                },
                finfo:{
                    validators:{
                        notEmpty: {
                            message: '职位描述不能为空!'
                        },
                        regexp: {
                            regexp: /[\u4e00-\u9fa5]/,
                            message: '职位描述只能由中文字符组成'
                        }
                    }
                },
            }
        });
    }
//添加和编辑提交按钮
    function submit(){
        var PositionLabel=$("#myPositionLabel").text();
        if(PositionLabel.indexOf("添加") !=-1){
            savePosition();//添加提交
        }else{
            updatePosition();//修改提交
        }
    }
//添加窗口
    function newPosition(){
        $("#fid").val("");
        $("#fname").val("");
        $("#fcode").val("");
        $("#finfo").val("");
        $("#myPositionLabel").html("添加职位");
        $('#newPosition').modal('show');
    }
//添加职位提交保存
    function savePosition(){
        var fname=$("#fname").val();
        var fcode=$("#fcode").val();
        var finfo=$("#finfo").val();
        $.ajax({
           type:"POST",
            url:"<%=basePath%>/position/addPosition.do",
            data:{
                fname:fname,
                fcode:fcode,
                finfo:finfo,
            },
            success:function(data){
                if(data!=="failed"){
                    successInfo("添加成功!");
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                    $('#positionForm').data('bootstrapValidator').resetForm(true);
                }else{
                    errorInfo("添加记录失败");
                }
            }
        });
        $('#newPosition').modal('hide');
    }
//编辑
    function editPosition(){
        $("#myPositionLabel").html("修改职位");
        var arr = $('#cusTable').bootstrapTable('getSelections');
        var ids = getCheckFid();
        if(arr.length>0) {
            var fid = getIdSelections();
            if (fid.length > 1) {
                warningInfo("请选择一条记录");
            } else {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>/position/getPositionById.do",
                    data: {
                        ids: ids
                    },
                    success: function (data) {
                        var msg = eval("(" + data + ")");
                        $("#fid").val(ids.replace(",", ""));
                        $("#fname").val(msg.fname);
                        $("#fcode").val(msg.fcode);
                        $("#finfo").val(msg.finfo);
                    }
                });
                $('#newPosition').modal('show');
            }
        }else{
            warningInfo("请选择一条记录");
        }

    }
    //提交更新
    function updatePosition(){
        var fid=$("#fid").val();
        var fname=$("#fname").val();
        var fcode=$("#fcode").val();
        var finfo=$("#finfo").val();
        $.ajax({
            type:"POST",
            url:"<%=basePath%>/position/updatePosition.do",
            data:{
                fid:fid,
                fname:fname,
                fcode:fcode,
                finfo:finfo
            },
            success:function(data){
                if(data!=="failed"){
                    successInfo("修改成功!");
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                    $('#positionForm').data('bootstrapValidator').resetForm(true);
                }else{
                    errorInfo("修改记录失败");
                }
            }
        });
        $('#newPosition').modal('hide');
    }

function delRow(){
    var arr = $('#cusTable').bootstrapTable('getSelections');
    if(arr.length>0) {
        var fid = getIdSelections();
        if (fid.length > 1) {
            warningInfo("请选择一条记录");
        } else {
            (confirmInfo("确认删除当前记录?")).then(function (status) {
                if (status == true) {
                    deletePosition();
                }
            });
        }
    }else{
        warningInfo("请选择要删除的记录");
    }
}
    //删除职位
function deletePosition(){
    console.info("deleteUser");
    var ids = getIdSelections();
     $.ajax({
        type: "POST",
        url: "<%=basePath%>/position/delPositionIds.do",
        data: {
            ids:getCheckFid()
        },
         beforeSend : function() {
             submitWait();
         },
         error : function() {
             hideWait();
             errorInfo("删除记录失败");
         },
        success: function(data){
            hideWait();
            if(data!=="failed"){
                $('#cusTable').bootstrapTable('remove', {
                    field: 'fid',
                    values: ids
                });
                successInfo("删除成功!")
            }else{
                errorInfo("删除记录失败");
            }
        }
    });

}

    //取表格行数用于表格行的移除
function getIdSelections() {
    return $.map($('#cusTable').bootstrapTable('getAllSelections'), function (row) {
        return row.fid;
    });
}
    //获取FID用于后台操作
function getCheckFid(){
    var fids="";
    $('#cusTable').find('input[name="btSelectItem"]:checked').each(function(){
        fids += $(this).val()+',';
    });
    return fids;
}
    //清空校验
    function resetForm(){
        $('#positionForm').data('bootstrapValidator').resetForm(true);
    }
</script>
<body id="loading" class="style_body">
<div class="style_border">
    <div>
        <div id="toolbar" class="btn-group-sm">
            <button id="add" class="btn btn-info" onclick="newPosition()">
                <i class="glyphicon glyphicon-expand"></i> 增加
            </button>
            <button id="edit" class="btn btn-info" onclick="editPosition()">
                <i class="glyphicon glyphicon-edit"></i> 修改
            </button>
            <button id="remove" class="btn btn-info" onclick="delRow()">
                <i class="glyphicon glyphicon-remove"></i> 删除
            </button>
            <button id="refresh" class="btn btn-info" name="refresh" >
                <i class="glyphicon glyphicon-refresh"></i> 刷新
            </button>
        </div>
        <table id="cusTable" class="table" >
            <thead>
            <tr>
                <th data-field="fid" data-checkbox="true" align="center"></th>
                <th data-field="fname" data-editable="false"  align="center">职位名称</th>
                <th data-field="fcode"  data-editable="false" align="center">职位代码</th>
                <th data-field="finfo"  data-editable="false" align="center">职位描述</th>
            </tr>
            </thead>
        </table>
    </div>

</div>

    <div class="modal fade" id="newPosition" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width:400px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myPositionLabel"></h4>
            </div>
            <div class="modal-body">
                <div>
                            <div id="positionForm">
                                <input class="form-control" id="fid" placeholder="ID" type="hidden">
                                <div class="form-group">
                                    <label>职位名称</label>
                                    <input class="form-control" id="fname" name="fname" placeholder="职位名称" type="text">
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group" >
                                    <label>职位代码</label>
                                    <input class="form-control" id="fcode" name="fcode" placeholder="职位代码" type="text">
                                </div>
                                <div class="form-group">
                                    <label>职位描述</label>
                                    <input class="form-control" id="finfo" name="finfo" placeholder="职位描述" type="text">
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                        </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="resetForm()" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submitBtn">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>
<%--模态弹窗引用jquery-ui设置可拖动--%>
<script src="<%=basePath%>/js/jquery-ui.min.js"></script>
<script>
    $(document).ready(function(){
        $(".modal-content").draggable({ cursor: "move" });//为模态对话框添加拖拽
    })
</script>
</body>