$(function(){
    var id = getQueryString("id");
    var pictureBaseUrl = "https://chouzhou-1256247322.cos-website.ap-guangzhou.myqcloud.com/";
    $.ajax({
        type: 'POST',
        url: 'http://127.0.0.1:8080/Chaozhou/queryUserById',
        data: { 'id' : id },
        dataType: "json",
        success: function(data) {
            $(username).val(data.user.uname);
            $(mobile).val(data.user.phoneNumber);
            if(data.user.sex == "男") {
                $("#sex1").prop("checked","checked");
                $(sex1).iCheck({
                    radioClass: 'iradio-blue'
                });
            } else {
                $("#sex2").prop("checked","checked");
                $(sex2).iCheck({
                    radioClass: 'iradio-blue'
                });
            }
            if(data.user.head !=null && data.user.head != "") {
                $(head).attr("src",pictureBaseUrl+data.user.head);
            }
            $(birthday).val(data.user.birthday);
        },
        error: function(data) {
            console.log(data.msg);
            layer.msg('发生错误!', {icon: 1, time: 1000});
        },
    });

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
                isMobile:true,
            },
            uploadfile:{
                required:false,
            },

        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.getFrameIndex(window.name);
            var param = {};
            param.uid = id;
            param.uname = $(username).val();
            param.phoneNumber = $(mobile).val();
            param.sex = $('input[name="sex"]:checked').val();
            param.birthday = $(birthday).val();
            $.ajax({
                type: 'POST',
                url: 'http://127.0.0.1:8080/Chaozhou/updateUserById',
                data: JSON.stringify(param),
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function(data) {
                    if (data.status == "success") {
                        parent.layer.msg('修改成功！', {icon: 6, time: 400}, function() {
                            parent.location.reload();
                        });
                        //parent.layer.close(index);
                    } else {
                        layer.msg('修改失败!', {icon: 1, time: 1000});
                    }
                },
                error: function(data) {
                    layer.msg('发生错误!', {icon: 1, time: 1000});
                }
            });
        }
    });
});

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}