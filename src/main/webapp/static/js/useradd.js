$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-member-add").validate({
        rules:{
            username:{
                required:true,
                minlength:2,
                maxlength:16
            },
            sex:{
                required:true,
            },
            mobile:{
                required:true,
                isMobile:true
            },
            password:{
                required:true,
                minlength: 5
            },
            Repassword:{
                required:true,
                equalTo:"#password"
            }
        },
        messages: {
            username:{
                required: "请输入你的用户名",
                minlength: "用户名长度不得少于2个字符",
                maxlength: "用户名长度不得多于16个字符"
            },
            mobile:{
                required: "请输入你的手机号码"
            },
            password:{
                required: "请输入密码",
                minlength: "密码长度不得少于5个字符"
            },
            Repassword:{
                required: "请确定你的密码",
                equalTo: "两次输入的密码不同"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            //var index = parent.layer.getFrameIndex(window.name);
            //拼接后台需要的参数
            var formData = new FormData();
            var head = document.getElementById("fileinput");
            formData.append("file", head.files[0]);
            formData.append("uname",$("#username").val());
            formData.append("phoneNumber",$("#mobile").val());
            formData.append("sex",$('input[name="sex"]:checked').val());
            formData.append("password",$("#password").val());
            formData.append("birthday",$("#birthday").val());

            $.ajax({
                type: 'POST',
                url: 'http://127.0.0.1:8080/Chaozhou/insertUser',
                data: formData,
                //dataType: "json",
                cache: false,
                processData:false,
                contentType:false,
                xhr:function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
                    myXhr = $.ajaxSettings.xhr();
                    if(myXhr.upload){ //检查upload属性是否存在
                        //绑定progress事件的回调函数
                        myXhr.upload.addEventListener('progress',progressHandlingFunction, false);
                    }
                    return myXhr; //xhr对象返回给jQuery使用
                },
                success: function(data) {
                    if (data.status == "success") {
                        parent.layer.msg('增加成功', {icon: 6, time: 400}, function() {
                            parent.location.reload();
                        });
                        //parent.layer.close(index);
                    } else {
                        layer.msg('增加失败!', {icon: 1, time: 1000});
                    }
                },
                error: function(data) {
                    layer.msg('发生错误!', {icon: 1, time: 1000});
                }
            });
        }
    });
});

//图片预览功能
function setImagePreview(avalue) {
    var docObj = document.getElementById("fileinput");
    var imgObjPreview = document.getElementById("head");
    if(docObj.files && docObj.files[0])
    {
        //火狐下，直接设img属性
        imgObjPreview.style.display = 'block';
        imgObjPreview.style.width = '110px';
        imgObjPreview.style.height = '100px';
        //imgObjPreview.src = docObj.files[0].getAsDataURL();
        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
    }
    else
    {
        //IE下，使用滤镜
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag"); //必须设置初始大小
        localImagId.style.width = "110px";
        localImagId.style.height = "100px"; //图片异常的捕捉，防止用户修改后缀来伪造图片
        try {
            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        } catch(e) {
            alert("您上传的图片格式不正确，请重新选择!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
    }
    return true;
}

//上传进度回调函数：
function progressHandlingFunction(e) {
    if (e.lengthComputable) {
        //显示进度条
        var progress = document.getElementById("progress");
        progress.style.display = "block";

        $('#percent').attr({value : e.loaded, max : e.total}); //更新数据到进度条
        var percent = e.loaded/e.total*100;
        $('#percent').css('width', percent.toFixed(2) + "%");
    }
}