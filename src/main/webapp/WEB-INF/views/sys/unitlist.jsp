<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <title>部门管理</title>
    <meta charset="utf-8">
    <script src="<%=basePath%>/static/js/jquery-2.2.0.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/css/trip.css">
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css">
    <%--表格样式--%>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/bootstrap-table/bootstrap-table.css">
    <link rel="stylesheet" href="<%=basePath%>/static/font-awesome/css/font-awesome.min.css">
    <%--<script src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>--%>
    <%--表格JS--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-table/bootstrap-table.js"></script>
    <%--表格导出--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
    <script src="<%=basePath%>/static/js/tableExport.js"></script>
    <%--语言包--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <%--提示框--%>
    <script src="<%=basePath%>/static/js/jquery.noty.packaged.min.js"></script>
    <script src="<%=basePath%>/static/js/showinfo.js"></script>
    <%--树--%>
    <script type="text/javascript" src="<%=basePath%>/static/js/ztree3d5/js/jquery.ztree.all.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/js/ztree3d5/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>/static/js/ztree3d5/css/demo.css" type="text/css" />
    <%--选人--%>
    <link rel="stylesheet" href="<%=basePath%>/static/css/demo.css" type="text/css">
    <link href="<%=basePath%>/static/css/selectuser.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>/static/js/selectbox.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/font-awesome/css/font-awesome.min.css">
    <!--校验-->
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/validate/css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="<%=basePath%>/static/bootstrap/validate/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/bootstrap/validate/js/language/zh_CN.js"></script>
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
    function initTable(id) {
        //先销毁表格
        $('#cusTable').bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            contentType: "application/x-www-form-urlencoded",
            url: "<%=basePath%>/unit/getUnitList.do", //获取数据的Servlet地址
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
            search:false,
            idField:"fid",
            //showFooter:true,
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "limit",

            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    limit: params.limit,
                    offset: params.offset,
                    unId:id,
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
        //初始化部门树
        $.fn.zTree.init($("#treeDemo"), setting);
        //调用函数，初始化表格
        initTable();
        initValidate();
        $("button[title='刷新']").hide();
        $("#unitForm").submit(function(ev){ev.preventDefault();});//AJAX提交必修使用
        $('#submitBtn').click(function() {
            var bootstrapValidator = $("#unitForm").data('bootstrapValidator');//必须
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()) {
                submit();
            }else{
                alert("请按照要求填写");
                return;
            }
        });
        $(".close").click(function (){
            $('#unitForm').data('bootstrapValidator').resetForm(true);
        })
    });
    //校验
    function initValidate(){
        $('#unitForm').bootstrapValidator({
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
                            message: '部门名称不能为空!'
                        },
                        regexp: {
                            regexp: /[\u4e00-\u9fa5]/,
                            message: '部门名称只能由中文字符组成'
                        }
                    }
                },
                nickname:{
                    validators:{
                        notEmpty: {
                        message: '部门简称不能为空!'
                        },
                        regexp: {
                        regexp: /[\u4e00-\u9fa5]/,
                            message: '部门简称只能由中文字符组成'
                        }
                    }
                },
                fcode:{
                    validators:{
                        notEmpty: {
                            message: '部门代码不能为空!'
                        },
                        regexp: {
                            regexp: /^[0-9]*$/,
                            message: '部门代码只能由字母，数字，点和下划线组成'
                        }
                    }
                },
                unittype:{
                    validators:{
                        notEmpty: {
                            message: '部门类型不能为空!'
                        }
                    }
                },
            }
        });
    }
//添加和编辑提交按钮
    function submit(){
        var UnitLabel=$("#myUnitLabel").text();
        if(UnitLabel.indexOf("添加") !=-1){
            saveUnit();//添加提交
        }else{
            updateUnit();//修改提交
        }
    }
//添加窗口
    function newUnit(){
        $("#fid").val("");
        $("#fname").val("");
        $("#nickname").val("");
        $("#fcode").val("");
        $("#unittype").val("");
        $("#parent_unitid").val("");
        $("#myUnitLabel").html("添加部门");
        $('#newUnit').modal('show');
    }
//添加部门提交保存
    function saveUnit(){
        var fname=$("#fname").val();
        var nickname=$("#nickname").val();
        var fcode=$("#fcode").val();
        var unittype=$("#unittype").val();
        var parent_unitid=$("#unitId").val();
        $.ajax({
           type:"POST",
            url:"<%=basePath%>/unit/addUnit.do",
            async:false,
            data:{
                fname:fname,
                nickname:nickname,
                fcode:fcode,
                unittype:unittype,
                parent_unitid:parent_unitid
            },
            success:function(data){
                if(data!=="failed"){
                    successInfo("添加成功!");
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                    $('#unitForm').data('bootstrapValidator').resetForm(true);
                    location.reload();
                }else{
                    errorInfo("添加记录失败");
                }
            }
        });
        $('#newUnit').modal('hide');
    }
//编辑
    function editUnit(){
        $("#myUnitLabel").html("修改部门");
        var arr = $('#cusTable').bootstrapTable('getSelections');
        var ids = getCheckFid();
        if(arr.length>0) {
            var fid = getIdSelections();
            if (fid.length > 1) {
                warningInfo("请选择一条记录");
            } else {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>/unit/getUnitById.do",
                    data: {
                        ids: ids
                    },
                    success: function (data) {
                        var msg = eval("(" + data + ")");
                        $("#fid").val(ids.replace(",", ""));
                        $("#fname").val(msg[0].fname);
                        $("#nickname").val(msg[0].nickname);
                        $("#fcode").val(msg[0].fcode);
                        $("#unittype").val(msg[0].unittype);
                        $("#parent_unitid").val(msg[0].parentUnitid);
                        $("#unitId").val(msg[0].saveUnit);
                    }
                });
                $('#newUnit').modal('show');
            }
        }else{
            warningInfo("请选择一条记录");
        }

    }
    //提交更新
    function updateUnit(){
        var fid=$("#fid").val();
        var fname=$("#fname").val();
        var nickname=$("#nickname").val();
        var fcode=$("#fcode").val();
        var unittype=$("#unittype").val();
        var parent_unitid=$("#unitId").val();
        $.ajax({
            type:"POST",
            url:"<%=basePath%>/unit/updateUnit.do",
            data:{
                fid:fid,
                fname:fname,
                nickname:nickname,
                fcode:fcode,
                unittype:unittype,
                parent_unitid:parent_unitid
            },
            success:function(data){
                if(data!=="failed"){
                    successInfo("修改成功!");
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                    $('#unitForm').data('bootstrapValidator').resetForm(true);
                    location.reload();
                }else{
                    errorInfo("修改记录失败");
                }
            }
        });
        $('#newUnit').modal('hide');
    }

function delRow(){
    var arr = $('#cusTable').bootstrapTable('getSelections');
    if(arr.length>0) {
        var fid = getIdSelections();
        var str = getCheckFid().split(",");
        if (str.indexOf("0")==0) {
            warningInfo("根部门不能删除");
        } else {
            (confirmInfo("确认删除当前记录?")).then(function (status) {
                if (status == true) {
                        deleteUnit();
                }
            });
        }
    }else{
        warningInfo("请选择要删除的记录");
    }
}
    //删除部门
function deleteUnit(){
    console.info("deleteUnit");
    var ids = getIdSelections();
     $.ajax({
        type: "POST",
        url: "<%=basePath%>/unit/delUnitIds.do",
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
                location.reload();
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

    //部门树
    var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "level",//单选
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        async: {
            enable: true,
            url:"<%=basePath%>/unit/getUnitTree.do"
        },
        callback: {
            onClick: onClick
        }
    };
//回调函数
    function onClick(e, treeId, treeNode) {
        $("#unitId").val("");
        $("#unitId").val(treeNode.id);
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getSelectedNodes(),
                v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        $("#parent_unitid").val(v);
    }
    //显示下拉树
    function showMenu() {
        var parentunit=$("#parent_unitid").val();
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = zTree.getNodes();
        var	nodes_array = zTree.transformToArray (nodes);
        for(var i=0;i<nodes_array.length;i++){
            if(nodes_array[i].name == parentunit){
                zTree.checkNode(nodes_array[i], true);
                zTree.selectNode(nodes_array[i]);
                zTree.expandNode(nodes_array[i].getParentNode(), true, true, true);
            }
        }
        var cityObj = $("#parent_unitid");
        var cityOffset = $("#parent_unitid").offset();
        $("#menuContent").css({left:380 + "px", top:60 + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }
    //清空校验
    function resetForm(){
        $('#unitForm').data('bootstrapValidator').resetForm(true);
    }
</script>
<body id="loading" class="style_body">
<div class=" style_border">
    <div>
        <div id="toolbar" class="btn-group-sm">
            <button id="add" class="btn btn-info" onclick="newUnit()">
                <i class="glyphicon glyphicon-expand"></i> 增加
            </button>
            <button id="edit" class="btn btn-info" onclick="editUnit()">
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
                <th data-field="fname" data-editable="false"  align="center">部门名称</th>
                <th data-field="nickname"  data-editable="false" align="center">部门简称</th>
                <%--<th data-field="fcode"  data-editable="false" align="center">部门代码</th>
                <th data-field="unittype"  data-editable="false" align="center">部门类型</th>--%>
                <th data-field="parentUnitid"  data-editable="false" align="center">上级部门</th>
            </tr>
            </thead>
        </table>
    </div>

</div>

    <div class="modal fade" id="newUnit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myUnitLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                            <div id="unitForm">
                            <div class="col-md-6">
                                <input class="form-control" id="fid" placeholder="ID" type="hidden">
                                <input class="form-control" id="unitId" placeholder="ID" type="hidden">
                                <input class="form-control" id="savemasterUserid" placeholder="ID" type="hidden">
                                <input class="form-control" id="saveviceUserid" placeholder="ID" type="hidden">
                                <input class="form-control" id="savemasterUser" placeholder="ID" type="hidden">
                                <input class="form-control" id="saveviceUser" placeholder="ID" type="hidden">
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>部门名称</label>
                                    <input class="form-control" id="fname" name="fname" placeholder="部门名称" type="text">
                                </div>
                                <div class="form-group" >
                                    <label>部门简称</label>
                                    <input class="form-control" id="nickname" name="nickname" placeholder="部门简称" type="text">
                                </div>
                                </div>
                                <div class="col-md-6">
                                <div style="margin-top:20px;z-index: 999" class="form-group form-inline" >
                                    <label>上级部门</label>
                                    <input id="parent_unitid" name="parent_unitid" type="text" readonly class="form-control" style="width:120px;"/>
                                    &nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
                                </div>
                                   <%-- <div class="form-group" >
                                        <label>部门类型</label>
                                        <input class="form-control" id="unittype" name="unittype" placeholder="部门类型" type="text">
                                    </div>--%>
                                <!-- /.form-group -->
                            </div>
                            </div>
                            <!-- /.col -->
                        </div>
                <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
                    <ul id="treeDemo" class="ztree" style="width:160px;height:150px;background: white;border: 1px solid lightgrey"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="resetForm()">关闭</button>
                <button type="button" class="btn btn-primary" id="submitBtn">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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