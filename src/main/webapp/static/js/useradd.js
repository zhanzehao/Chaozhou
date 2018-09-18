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
            var param = {};
            param.uname = $(username).val();
            param.phoneNumber = $(mobile).val();
            param.sex = $('input[name="sex"]:checked').val();
            param.password = $(password).val();
            param.birthday = $(birthday).val();

            var head = document.getElementById("fileinput");
            console.log(head.files[0]);

            var formData = new FormData();
            formData.append("tbuser",JSON.stringify(param));
            formData.append("file", head.files[0]);

            $.ajax({
                type: 'POST',
                url: 'http://127.0.0.1:8080/Chaozhou/insertUser',
                data: formData,
                //dataType: "json",
                cache: false,
                processData:false,
                contentType:false,
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