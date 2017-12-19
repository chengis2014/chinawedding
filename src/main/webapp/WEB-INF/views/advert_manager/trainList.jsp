<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--名师面对面管理
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017-11-29
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

    Date dNow = new Date( );
    SimpleDateFormat ft =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strCurrent=ft.format(dNow);
%>
<head>
    <meta charset="utf-8">
    <title>名师管理</title>
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
    <%--日期样式--%>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <%--日期JS--%>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>/static/bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <%--日期--%>
    <%-- <script src="<%=basePath%>/My97/WdatePicker.js"></script>>--%>
    <script type="text/javascript" src="<%=basePath%>/static/js/selectbox.js"></script>
    <%--  <script type="text/javascript" src="<%=basePath%>/js/jquery.simplemodal.js"></script>--%>
    <link rel="stylesheet" href="<%=basePath%>/static/bootstrap/validate/css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="<%=basePath%>/static/bootstrap/validate/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/bootstrap/validate/js/language/zh_CN.js"></script>
    <%-- <script type="text/javascript"  src="<%=basePath%>/static/js/ztree3d5/js/jquery.ztree.all.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/static/js/ztree3d5/css/zTreeStyle/zTreeStyle.css" type="text/css" />
       <link rel="stylesheet" href="<%=basePath%>/js/ztree3d5/css/demo.css" type="text/css" />--%>
    <link href="<%=basePath%>/static/css/selectuser.css" rel="stylesheet">
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
        .modal-content{
            width:400px;
            margin-left: auto;
            margin-right: auto;
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
            url: "<%=basePath%>/role/getRoleList.do", //获取数据的Servlet地址
            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            toolbar:"#toolbar",
            showRefresh: true,
            //showExport:true,
            // height:400,
            pageSize:10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            exportDataType : "all",
            clickToSelect:true,
            //search:true,
            idField:"uid",
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
        $("#defaultForm").submit(function(ev){ev.preventDefault();});


        $('.form_datetime').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            pickerPosition: "top-left",
            showMeridian: 1
        });

        //$('#Modal').modal('show');
        //newModule();
    });

    //显示新建窗口
    function newModule(){
        /* $('#roleName').val("");
         $('#roleDescription').val("");
         $('#roleCreator').val("");*/
        $("#myModalLabel").html("新建名师信息");
        $('#newModal').modal('show');
    }
    function initValidate(){
        $('#defaultForm').bootstrapValidator({
            message: '值不能为空',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                roleName: {
                    validators: {
                        notEmpty: {
                            message: '角色名不能为空'
                        }
                    }
                },
                roleDescription: {
                    validators: {
                        notEmpty: {
                            message: '角色描述不能为空'
                        }
                    }
                },
                roleCreator: {
                    validators: {
                        notEmpty: {
                            message: '创建者不能为空'
                        }
                    }
                }
            }
        });
    }
    //提交保存
    function saveModule(){
        var opurl=$('#fid').val()==""?"<%=basePath%>/role/addRole.do":"<%=basePath%>/role/updateRole.do";
        $.ajax({
            type:"post",
            url: opurl,
            data:{
                fid:$('#fid').val(),
                roleName:$('#roleName').val(),
                roleDescription: $('#roleDescription').val(),
                roleCreator:  $('#roleCreator').val(),
                roleCtime:$('#roleCtime').val(),
                roleEnabled: $('#roleEnabled').val(),
            },
            success: function(data){
                hideWait();
                if(data!=="failed"){
                    successInfo("保存成功!")
                    $('#defaultForm').data('bootstrapValidator').resetForm(true);
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                }else{
                    errorInfo("保存失败");
                    $('#defaultForm').data('bootstrapValidator').resetForm(true);
                    $('#cusTable').bootstrapTable('refresh');//初始化数据
                }
            }
        });
        $('#newModal').modal('hide');
    }


</script>
<body id="loading" class="style_body">
<div class="style_border">
    <div >
        <div id="toolbar" class="btn-group-sm">
            <button id="add" class="btn btn-info" onclick="newModule()">
                <i class="glyphicon glyphicon-expand"></i> 增加
            </button>
            <button id="edit" class="btn btn-info" onclick="editModule()">
                <i class="glyphicon glyphicon-edit"></i> 编辑
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
                <th data-field="uid" data-checkbox="true" align="center"></th>
                <th data-field="trainname" data-editable="false"  align="center" >培训机构</th>
                <th data-field="coursename" data-editable="false"  align="center" >培训课程</th>
                <th data-field="trainclass" data-editable="false"  align="center" >培训课时</th>
                <th data-field="trainmethod" data-editable="false"  align="center" >培训方式</th>
                <th data-field="phone" data-editable="false"  align="center" >联系电话</th>
                <th data-field="traintime" data-editable="false"  align="center" >开始时间</th>
                <th data-field="trainendtime" data-editable="false"  align="center" >结束时间</th>
                <th data-field="legtime" data-editable="false"  align="center" >有效期</th>
                <th data-field="topflag" data-editable="false"  align="center" >是否置顶</th>
                <th data-field="price" data-editable="false"  align="center" >价格</th>
            </tr>
            </thead>
        </table>
    </div>

    <div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content" style="width:700px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body" id="defaultForm" method="post">
                    <div class="row">
                        <input type="hidden" name="fid" id="fid"/>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>培训机构(老师)</label>
                                <input class="form-control" id="trainname"  name="trainname" placeholder="培训机构(老师)" type="text">
                            </div>
                            <div class="form-group">
                                <label>培训课时</label>
                                <input class="form-control" id="trainname"  name="trainname" placeholder="培训课时" type="text">
                            </div>
                            <div class="form-group">
                                <label>培训地址</label>
                                <input class="form-control" id="trainname"  name="trainname" placeholder="培训地址" type="text">
                            </div>
                            <div class="form-group">
                                <label>培训开始时间</label>
                                <!--指定 date标记-->
                                <div class="input-group date form_datetime" data-date="<%=strCurrent%>"
                                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dt_starttime">
                                    <input class="form-control" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <input type="hidden" id="dt_starttime" value="" />
                                <%--<input class="form-control" id="trainname"  name="trainname" placeholder="培训开始时间" type="text">--%>
                            </div>
                            <div class="form-group">
                                <label>LOGL图片</label>
                                <input  id="uplogoimg"  name="uplogoimg" placeholder="logo图片" type="file">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>培训课程</label>
                                <input class="form-control" id="coursename"  name="coursename" placeholder="课程名称" type="text">
                            </div>
                            <div class="form-group">
                                <label>培训方式</label>
                                <input class="form-control" id="coursename"  name="coursename" placeholder="培训方式" type="text">
                            </div>
                            <div class="form-group">
                                <label>联系电话</label>
                                <input class="form-control" id="coursename"  name="coursename" placeholder="联系电话" type="text">
                            </div>
                            <div class="form-group">
                                <label>培训结束时间</label>
                                <!--指定 date标记-->
                                <div class="input-group date form_datetime" data-date="<%=strCurrent%>"
                                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dt_endtime">
                                    <input class="form-control" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <input type="hidden" id="dt_endtime" value="" />
                                <%--<input class="form-control" id="coursename"  name="coursename" placeholder="培训结束时间" type="text">--%>
                            </div>
                            <div class="form-group">
                                <label>置顶顺序</label>
                                <input class="form-control" id="coursename"  name="coursename" placeholder="课程名称" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="row">

                        <div class="col-md-12">
                            <div class="form-group">
                                <label>培训内容</label>
                                <textarea class="form-control" id="traincontent"  name="traincontent" placeholder="培训内容" rows="2" ></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>信息宣传费用</label>
                                <input class="form-control" id="trainname"  name="trainname" placeholder="宣传费用" type="text">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>截止宣传日期</label>
                                <!--指定 date标记-->
                                <div class="input-group date form_datetime" data-date="<%=strCurrent%>"
                                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dt_stoptime">
                                    <input class="form-control" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <input type="hidden" id="dt_stoptime" value="" />
                                <%--<input class="form-control" id="coursename"  name="coursename" placeholder="课程名称" type="text">--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"  onclick="resetForm()">关闭</button>
                    <button type="button" class="btn btn-primary" id="validateBtn">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
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
