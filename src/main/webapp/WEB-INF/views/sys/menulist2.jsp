<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <title>菜单管理</title>
    <meta charset="utf-8">
    <%--<script src="<%=basePath%>/js/jquery-2.2.0.min.js"></script>--%>
    <link rel="stylesheet" href="<%=basePath%>/css/trip.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap/css/bootstrap.min.css">
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
            url: "/sys/getModuleList.do", //获取数据的Servlet地址
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            toolbar:"#toolbar",
            showRefresh: true,
            //showExport:true,
           // height:400,
            pageSize: 50,  //每页显示的记录数
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
    var $table = $('#cusTable');

    /**
     * 初始化
     */
    $(document).ready(function () {

        //调用函数，初始化表格
        initTable();
        $("button[title='刷新']").hide();

    });


//显示新建窗口
    function newModule(){
        $('#newModal').modal('show');
    }
//
    //初始化新建菜单表单
    function initNewModule(){

    }
//提交保存
    function saveModule(){

    }
//编辑
    function editModule(){

    }
    //更新
    function updateModule(){

    }

function delRow(){
    var arr = $table.bootstrapTable('getSelections');
    if(arr.length>0){
        (confirmInfo("确认删除当前记录?")).then(function (status) {
            if (status==true) {
                deleteModule()
            }
        });
    } else{
        warningInfo("请选择要删除的记录");
    }
}
    //删除菜单
function deleteModule(){
    console.info("deleteModule");
    var ids = getIdSelections();
     $.ajax({
        type: "POST",
        url: "/sys/delModule4Ids.do",
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
                $table.bootstrapTable('remove', {
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
    return $.map($table.bootstrapTable('getAllSelections'), function (row) {
        return row.fid;
    });
}
    //获取FID用于后台操作
function getCheckFid(){
    var fids="";
    $table.find('input[name="btSelectItem"]:checked').each(function(){
        fids += $(this).val()+',';
    });
    return fids;
}

</script>
<body id="loading" class="style_body">
<div class="style_border">
    <div id="toolbar" class="btn-group-sm">
        <button id="add" class="btn btn-info" onclick="newModule()">
            <i class="glyphicon glyphicon-expand"></i> 增加
        </button>
        <button id="remove" class="btn btn-info" onclick="delRow()">
            <i class="glyphicon glyphicon-remove"></i> 删除
        </button>
        <button id="edit" class="btn btn-info" onclick="editModule()">
            <i class="glyphicon glyphicon-edit"></i> 修改
        </button>
        <button id="refresh" class="btn btn-info" name="refresh" >
            <i class="glyphicon glyphicon-refresh"></i> 刷新
        </button>
    </div>
    <table id="cusTable" class="table" >
        <thead>
        <tr>
            <th data-field="fid" data-checkbox="true" align="center"></th>
            <th data-field="mdName" data-editable="false"  align="center" >菜单名称</th>
            <th data-field="mdCode"  data-editable="false" align="center">模块代码</th>
            <th data-field="mdImg"  data-editable="false" align="center">图标</th>
            <th data-field="mdAddress"  data-editable="false" align="center">模块地址</th>
            <th data-field="mdMethod"  data-editable="false" align="center">打开方法</th>
            <th data-field="mdIschild"  data-editable="false" align="center">是否为子节点</th>
            <th data-field="pmId" data-editable="false" align="center">父菜单</th>
            <th data-field="mdOrdernum" data-editable="false" align="center">显示顺序</th>
        </tr>
        </thead>
    </table>
</div>

    <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新建</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>菜单名称</label>
                                    <input class="form-control" id="mdName" placeholder="菜单名称" type="text">
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>模块代码</label>
                                    <input class="form-control" id="mdCode" placeholder="模块代码" type="text">
                                </div>
                                <div class="form-group">
                                    <label>父菜单</label>
                                    <select class="form-control" id="pmId" >
                                        <option>option 1</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>显示顺序</label>
                                    <input class="form-control" id="mdOrdernum" placeholder="显示顺序" type="text">
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>模块地址</label>
                                    <input class="form-control" id="mdAddress" placeholder="模块地址" type="text">
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>图标</label>
                                    <input class="form-control" id="mdImg" placeholder="图标样式" type="text">
                                </div>

                                <div class="form-group">
                                    <label>打开方式</label>
                                    <select class="form-control" id="mdMethod" >
                                        <option>默认</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>
                                        <input type="checkbox" class="minimal" id="mdIschild"> 包含子菜单</label>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                        </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交</button>
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