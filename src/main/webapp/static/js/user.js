/*$(function () {
    /!*展示相关数据到页面*!/
    var urls = "displayAllUser";
    $.ajax({
        type:"POST",
            url:urls,
            dataType:"json",
            scriptCharset:"utf-8",
            success:function (data) {
                display_paging(data);
            },
            error:function () {
                alert("信息获取失败，显示不出来！");
            }
    });
});*/

/*function display_paging(data) {
    var str = "";
    var tbody = window.document.getElementById("userbody");
    var uname;
    var sex;
    var phoneNumber;
    var password;
    var head;
    var birthday;
    for (i in data) {
        if (data[i].uname == null) {
            uname = "";
        }else {
            uname = data[i].uname;
        }
        if (data[i].sex == null) {
            sex = "";
        }else {
            sex = data[i].sex;
        }
        if (data[i].phoneNumber == null) {
            phoneNumber = "";
        }else {
            phoneNumber = data[i].phoneNumber;
        }
        if (data[i].password == null) {
            password = "";
        }else {
            password = data[i].password;
        }
        if (data[i].head == null) {
            head = "";
        }else {
            head = data[i].head;
        }
        if (data[i].birthday == null) {
            birthday = "";
        }else {
            birthday = data[i].birthday;
        }
        str +=  "<tr class='text-c'>" +
                "<td><input type=\"checkbox\" value=\"1\" name=\"\"></td>" +
                "<td>"+data[i].uid+"</td>" +
                "<td><u style='cursor:pointer' class='text-primary' " +
                    "onclick= member_show('"+uname+"','member-show.html','10001','360','400) >"+uname+"</u></td>" +
                "<td>" + sex + "</td>" +
                "<td>" + phoneNumber + "</td>" +
                "<td>" + password + "</td>" +
                "<td>" + head + "</td>" +
                "<td>" + birthday + "</td>" +
                "<td class='td-manage'>" +
                "<a title='编辑' href='javascript:;' onclick=member_edit('编辑','member-add.html','4','','510') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>"+
                "<a title='修改密码' href='javascript:;' onClick=change_password('修改密码','change-password.html','10001','600','270') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe63f;</i></a>"+
                "<a title='删除' href='javascript:;' onclick=member_del(this,'1') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>"+
                "</td>"+ "</tr>";
    }
    tbody.innerHTML = str;
}*/

$(function(){
    var table = $('.table-sort').dataTable({
        "processing":true,
        "bServerSide": true,
        "lengthMenu": [1,10, 20, 50, 100],
        "lengthChange": true,
        "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "bInfo": true, //是否显示页脚信息，DataTables插件左下角显示记录数
        ajax: {
            "url": '/Chaozhou/displayAllUser',
            "dataSrc":function (json) {
                console.log(json);
            }
        },
        aoColums:[
            {
                CHECKBOX: {	//复选框单元格
                    className: "td-checkbox",
                    orderable: false,
                    width: "30px",
                    data: null,
                    render: function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            {"mdata": "uid"},
            {"mdata": "uname"},
            {"mdata": "sex"},
            {"mdata": "phoneNumber","defaultContent":""},
            {"mdata": "password"},
            {"mdata": "head", "defaultContent":""},
            {"mdata": "birthday", "defaultContent":""},
            {
                "class":"td-manage",
                "mdata":null
            }
        ],
        aoColumnDefs:[
            {"orderable":false,"aTargets":[0,5,6,8]},// 制定列不参与排序
        {
            "aTarget":[-1],
            "mRender":function (data,type,row) {
                var html =  "<td class='td-manage'>" +
                    "<a title='编辑' href='javascript:;' onclick=member_edit('编辑','member-add.html','4','','510') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>"+
                    "<a title='修改密码' href='javascript:;' onClick=change_password('修改密码','change-password.html','10001','600','270') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe63f;</i></a>"+
                    "<a title='删除' href='javascript:;' onclick=member_del(this,'1') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                return html;
            }
        }],
        "createRow":function (row,data,dataIndex) {
            $(row).addClass("text-c");
        }
    });
});
/*用户-添加*/
function member_add(title,url,w,h){
    layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
    layer_show(title,url,w,h);
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
    layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
    layer_show(title,url,w,h);
}
/*用户-删除*/
function member_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'POST',
            url: '',
            dataType: 'json',
            success: function(data){
                $(obj).parents("tr").remove();
                layer.msg('已删除!',{icon:1,time:1000});
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });
}