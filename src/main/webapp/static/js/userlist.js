$(function (){
    var $wrapper = $('#div-table-container');
    var $table = $('#UserId');

    var _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax : function(data, callback, settings) { //ajax配置为function,手动调用异步查询
            //手动控制遮罩
            $wrapper.spinModal();
            //封装请求参数
            var param = userManage.getQueryCondition(data);
            $.ajax({
                type: "POST",
                url: "http://127.0.0.1:8080/Chaozhou/displayAllUser",
                cache : false,	//禁用缓存
                data: JSON.stringify(param),	//传入已封装的参数
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function(result) {
                    //异常判断与处理
                    if (result.errorCode) {
                        alert("查询失败");
                        return;
                    }
                    //封装返回数据，这里仅演示了修改属性名
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.pageData;
                    //关闭遮罩
                    $wrapper.spinModal(false);
                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);

                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("查询失败");
                    $wrapper.spinModal(false);
                }
            });
        },
        columns: [
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {
                data: "uid",
                width: "70px",
                className: "uid"
            },
            {
                //className : "text-overflow",	//文字过长时用省略号显示，CSS实现
                data: "uname"
                //render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
            },
            {
                data: "sex",
                //固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
                //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                width : "60px"
            },
            {
                data : "phoneNumber",
                width : "160px"
            },
            {
                data : "head",
                className : "text-overflow",	//文字过长时用省略号显示，CSS实现
                render : CONSTANT.DATA_TABLES.RENDER.HEAD,
                defaultContent:"",
                orderable : false,
                width : "150px"
            },
            {
                data : "birthday",
                defaultContent:"",
                width : "100px"
            },
            {
                className : "td-manage",
                data: null,
                defaultContent:"",
                orderable : false,
                width : "80px"
            }
        ],
        "createdRow": function ( row, data, index ) {
            //行渲染回调,在这里可以对该行dom元素进行任何操作
            //给当前行加样式
            if (data.uid) {
                $(row).addClass("text-c");
            }
            //给当前行某列加样式
            //$('td', row).eq(3).addClass(data.status?"text-success":"text-error");
            //不使用render，改用jquery文档操作呈现单元格
            var $btnEdit = $('<a title=\'编辑\' href=\'javascript:;\' onclick=member_edit(\'编辑\',\'member-edit.html\','+data.uid+',\'\',\'510\') class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6df;</i></a>');
            var $btnDel = $('<a title=\'删除\' href=\'javascript:;\'  onclick=member_del(this,'+data.uid+') class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6e2;</i></a>');
            $('td', row).eq(7).append($btnEdit).append($btnDel);

        },
        "drawCallback": function( settings ) {
            //渲染完毕后的回调
            //清空全选状态
            $(":checkbox[name='cb-check-all']",$wrapper).prop('checked', false);
            //默认选中第一行
            //$("tbody tr",$table).eq(0).click();
        }
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    $("#btn-simple-search").click(function(){
        userManage.fuzzySearch = true;

        //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
        //_table.ajax.reload();
        //_table.draw(false);
        _table.draw();
    });

    $("#btn-advanced-search").click(function(){
        userManage.fuzzySearch = false;
        _table.draw();
    });

    //行点击事件
    $("tbody",$table).on("click","tr",function(event) {
        $(this).addClass("active").siblings().removeClass("active");
        //获取该行对应的数据
        // var item = _table.row($(this).closest('tr')).data();
        //$(this).closest('tr').addClass("active").siblings().removeClass("active");
    });

    $table.on("change",":checkbox",function() {
        if ($(this).is("[name='cb-check-all']")) {
            //全选
            $(":checkbox",$table).prop("checked",$(this).prop("checked"));
        }else{
            //一般复选
            var checkbox = $("tbody :checkbox",$table);
            $(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    }).on("click",".td-checkbox",function(event) {
        //点击单元格即点击复选框
        !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    });

    $("#toggle-advanced-search").click(function(){
        $("i",this).toggleClass("Hui-iconfont Hui-iconfont");
        $("#div-advanced-search").slideToggle("fast");
    });

    $("#deleteSome").click(function() {
        var idArray = new Array();
        var check = $("input[name='ids']:checked");//选中的复选框
        check.each(function(){
            var row = $(this).parent("td").parent("tr");
            var item = _table.row(row).data();
            var uid = item.uid;
            idArray.push(uid);
        });
        if(idArray.length<=0){
            layer.msg('请选择要删除的用户!', {icon: 1, time: 1000});
        }else{
            member_del(this,idArray);
        }
    });

    //返回页面顶部
    //$.Huitotop();
});

var userManage = {
    //currentItem : null,
    fuzzySearch : true,
    getQueryCondition : function(data) {
        var param = {};
        //组装排序参数
        if (data.order&&data.order.length&&data.order[0]) {
            switch (data.order[0].column) {
                case 1:
                    param.orderColumn = "uid";
                    break;
                case 2:
                    param.orderColumn = "uname";
                    break;
                case 3:
                    param.orderColumn = "sex";
                    break;
                case 4:
                    param.orderColumn = "phoneNumber";
                    break;
                case 6:
                    param.orderColumn = "birthday";
                    break;
                default:
                    param.orderColumn = "uid";
                    break;
            }
            param.orderDir = data.order[0].dir;
        }
        //组装查询参数
        param.fuzzySearch = userManage.fuzzySearch;
        if (userManage.fuzzySearch) {
            param.fuzzy = $("#fuzzy-search").val();
        }else{
            param.uname = $("#name-search").val();
            param.phoneNumber = $("#phone-search").val();
            param.sex = $("#sex-search").val();
            //param.status = $("#status-search").val();
            //param.role = $("#role-search").val();
        }

        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;

        param.draw = data.draw;

        return param;
    }
};

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
    var urlwith = url+"?id="+id;
    layer_show(title,urlwith,w,h);
}

/*用户-删除*/
function member_del(obj,ids) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: 'http://127.0.0.1:8080/Chaozhou/deleteUserById',
            data: { "id" : ids },
            dataType: "json",
            traditional: true,
            success: function (data) {
                if (data.status == "success") {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 400}, function() {
                        location.reload();
                    });
                } else {
                    layer.msg('删除失败!', {icon: 1, time: 1000});
                }
            },
            error: function (data) {
                console.log(data.msg);
                layer.msg('删除失败!', {icon: 1, time: 1000});
            },
        });
    })
}