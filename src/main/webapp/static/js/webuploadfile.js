(function ($, window) {
    var applicationPath = window.applicationPath === "" ? "" : window.applicationPath || "../";
    function SuiJiNum() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    function addAccessory(fileId,fileName,fileSize){
        var fileNameStr = "";
        fileNameStr = fileName;
        // if(fileName.length > 15){
        //    fileNameStr = fileName.substring(0,15)+"...";
        // }
        // $('#fileQueue').append("<div id='uploadify"+fileId+"' class='uploadifyQueueItem completed'><DIV class='cancel'><A href=\"javascript:$('#uploadify').uploadifyCancel('"+fileId+"')\"><IMG border=0 src='../images/common/fudelete.gif'></A></DIV><SPAN class='fileName' title='"+fileName+"'>"+fileNameStr+" ("+fileSize+")</SPAN><SPAN class='percentage'></SPAN></DIV>");
        return "<div id='uploadify"+fileId+"' class=''><span class='fileName' title='"+fileName+"'>"+fileNameStr+" ("+fileSize+")</span><span class='cancel'><a href=\"javascript:uploadifyCancel('"+fileId+"')\"><img border=0 src='"+applicationPath+"/static/img/fudelete.gif'></a></span></div>";
    }



    function initWebUpload(item, options) {


        if (!WebUploader.Uploader.support()) {
            var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。>";
            if (window.console) {
                window.console.log(error);
            }
            $(item).text(error);
            return;
        }
        //创建默认参数
        var defaults = {
            auto:false,
            hiddenInputId: "uploadifyHiddenInputId", // input hidden id
            onAllComplete: function (event) { }, // 当所有file都上传后执行的回调函数
            onComplete: function (event) { },// 每上传一个file的回调函数
            innerOptions: {},
            fileNumLimit: undefined,//验证文件总数量, 超出则不允许加入队列
            fileSizeLimit: undefined,//验证文件总大小是否超出限制, 超出则不允许加入队列。
            fileSingleSizeLimit: undefined,//验证单个文件大小是否超出限制, 超出则不允许加入队列
            PostbackHold: false,
            formData:{relateId:''}
        };
        var opts = $.extend(defaults, options);
        var hdFileData = $("#" + opts.hiddenInputId);
        var target = $(item);//容器
        var pickerid = "";
        //if (typeof guidGenerator36 != 'undefined')//给一个唯一ID
        //    pickerid = guidGenerator36();
        // else
        pickerid = SuiJiNum();
        var uploaderStrdiv = '<div class="webuploader">'
        //debugger
        if (opts.auto) {
            uploaderStrdiv =
                '<div id="Uploadthelist" class="uploader-list"></div>' +
                '<div class="btns">' +
                '<div id="' + pickerid + '">选择文件</div>' +
                '</div>'

        } else {
            uploaderStrdiv =
                '<div  class="uploader-list"></div>' +
                '<div class="">' +
                '<div id="' + pickerid + '" >选择文件</div>'
                +
                '<div id="webuploadbtn" class="webuploadbtn">开始上传</div>' +
                '</div>'

        }
        uploaderStrdiv += '<div style="display:none" class="UploadhiddenInput" >\
                         </div>'
        uploaderStrdiv+='</div>';
        target.append(uploaderStrdiv);

        var $list = target.find('.uploader-list'),
            $btn = target.find('#webuploadbtn'),//手动上传按钮备用
            state = 'pending',
            $hiddenInput = target.find('.UploadhiddenInput'),
            uploader;
        var jsonData = {
            fileList: []
        };

        var webuploaderoptions = $.extend({
                // swf文件路径
                swf: applicationPath + '/js/webuploader/Uploader.swf',
                // 文件接收服务端。
                server:  applicationPath+'/upload/uploadFile.do',
                deleteServer:applicationPath+'/upload/removeFile.do',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#' + pickerid,
                //不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: false,
                fileNumLimit: opts.fileNumLimit,
                fileSizeLimit: opts.fileSizeLimit,
                fileSingleSizeLimit: opts.fileSingleSizeLimit,
                formData:opts.formData
            },
            opts.innerOptions);
        var uploader = WebUploader.create(webuploaderoptions);




        if (opts.auto) {
            uploader.on('fileQueued', function (file) {
                debugger;
                $list.append('<div id="' + $(item)[0].id + file.id + '" class="item">' +
                    '<span class="webuploadinfo">' + file.name + '</span>' +
                    '<span class="webuploadstate">正在上传...</span>' +
                    '<span class="webuploadDelbtn"><img border=0 src="'+applicationPath+'/static/img/fudelete.gif"></span><br />' +
                    '</div>');
                uploader.upload();
            });
        } else {
            //uploader.formData({relateId:$("#relateId").val()});

            uploader.on('fileQueued', function (file) {//队列事件
                $list.append('<div id="' + $(item)[0].id + file.id + '" class="item">' +
                    '<span class="webuploadinfo">' + file.name + '</span>' +
                    '<span class="webuploadstate">等待上传...</span>' +
                    '<span class="webuploadDelbtn"><img border=0 src="'+applicationPath+'/static/img/fudelete.gif"></span><br />' +
                    '</div>');
            });
        }


        uploader.on('uploadProgress', function (file, percentage) {//进度条事件
            //var $li = target.find('#' + $(item)[0].id + file.id),
            //    $percent = $li.find('.progress .bar');
            //
            //// 避免重复创建
            //if (!$percent.length) {
            //    $percent = $('<span class="progress">' +
            //        '<span  class="percentage"><span class="text"></span>' +
            //        '<span class="bar" role="progressbar" style="width: 0%">' +
            //        '</span></span>' +
            //        '</span>').appendTo($li).find('.bar');
            //}

            //$li.find('span.webuploadstate').html('上传中');
            //$li.find(".text").text(Math.round(percentage * 100) + '%');
            //$percent.css('width', percentage * 100 + '%');
        });
        uploader.on('uploadSuccess', function (file, response) {//上传成功事件
            debugger;
            if (response.state == "false") {
                target.find('#' + $(item)[0].id + file.id).find('span.webuploadstate').html(response.message);
            } else {
                target.find('#' + $(item)[0].id + file.id).find('span.webuploadstate').html("已上传");
                // $("#"+ $(item)[0].id + file.id).html("");
                //console.dir(response);
                //$("#"+ $(item)[0].id + file.id).html(addAccessory(response.files.fid,response.files.realName,response.files.fileSize));
                //$hiddenInput.append('<input type="text" id="hiddenInput'+$(item)[0].id + file.id + '" class="hiddenInput" value="' + response.message + '" />')
            }
        });
        uploader.on('uploadBeforeSend', function (obj, data, headers) {
            data.relateId=$("#relateId").val();
        });
        uploader.on('uploadError', function (file) {
            target.find('#' + $(item)[0].id + file.id).find('span.webuploadstate').html('上传出错');
        });

        uploader.on('uploadComplete', function (file) {//全部完成事件
            target.find('#' + $(item)[0].id + file.id).find('.progress').fadeOut();

        });

        uploader.on('all', function (type) {
            if (type === 'startUpload') {
                state = 'uploading';
            } else if (type === 'stopUpload') {
                state = 'paused';
            } else if (type === 'uploadFinished') {
                state = 'done';
            }

            if (state === 'uploading') {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        //删除时执行的方法
        uploader.on('fileDequeued', function (file) {
            //debugger
            var fullName = $("#hiddenInput" + $(item)[0].id + file.id).val();
            if (fullName!=null) {
                $.post(webuploaderoptions.deleteServer, { fullName: fullName }, function (data) {
                    alert(data.message);
                })
            }
            $("#"+ $(item)[0].id + file.id).remove();
            $("#hiddenInput" + $(item)[0].id + file.id).remove();

        })

        //多文件点击上传的方法
        $btn.on('click', function () {
            if (state === 'uploading') {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });

        //删除
        $list.on("click", ".webuploadDelbtn", function () {
            //debugger
            var $ele = $(this);
            var id = $ele.parent().attr("id");
            id = id.replace($(item)[0].id, "");

            var file = uploader.getFile(id);
            uploader.removeFile(file);
        });

    }
    $.fn.GetFilesAddress = function (options) {
        var ele = $(this);
        var filesdata = ele.find(".UploadhiddenInput");
        var filesAddress = [];
        filesdata.find(".hiddenInput").each(function () {
            filesAddress.push($(this).val());
        })
        return filesAddress;

    }

    $.fn.powerWebUpload = function (options) {
        var ele = this;
        if (typeof WebUploader == 'undefined') {
            //  var casspath = applicationPath + "/js/webuploader/webuploader.css";
            // $("<link>").attr({ rel: "stylesheet", type: "text/css", href: casspath }).appendTo("head");
            // var jspath = applicationPath + "/js/webuploader/webuploader.min.js";
            //  $.getScript(jspath) .done(function() {
            initWebUpload(ele, options);
            //     })
            //    .fail(function() {
            //         alert("请检查webuploader的路径是否正确!")
            //     });

        }
        else {
            initWebUpload(ele, options);
        }
    }
    function uploadifyCancel(fileId){
        $.ajax({
            type:"POST",
            url:""+applicationPath+"/upload/removeFile.do",
            data:{
                fileId:fileId,
            } ,
            error:function(){
            },
            success:function(data){
                if (data==='failed'){
                    alert("文件删除失败");
                }else{
                    $('#uploadify'+fileId).remove();
                }
            }
        });
    }
})(jQuery, window);


