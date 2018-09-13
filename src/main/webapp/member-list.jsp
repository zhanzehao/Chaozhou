<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="lib/html5shiv.js"></script>
	<script type="text/javascript" src="lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray">
		<span class="l">
			<a href="javascript:;" class="btn btn-danger radius" id="deleteSome"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
		</span>
		<div class="pull-right">
			<input type="text" name="" id="fuzzy-search" placeholder=" 模糊查询" style="width:250px" class="input-text">
			<div class="btn-group">
				<button name="" id="btn-simple-search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
				<button type="button" class="btn btn-success" title="高级查询" id="toggle-advanced-search"><i class="Hui-iconfont">&#xe674;</i></button>
			</div>
		</div>
	</div>
	<div class="row-fluid" style="display:none;" id="div-advanced-search">
		<form class="form-inline well">
			<span>用户名:</span>
			<input type="text" class="input-text radius" style="width:100px" placeholder="用户名" id="name-search">&nbsp;
			<span>手机号码:</span>
			<input type="text" class="input-text radius" style="width:150px" placeholder="手机号码" id="phone-search">&nbsp;
			<span>性别:</span>
			<input type="text" class="input-text radius" style="width:100px" placeholder="性别" id="sex-search">&nbsp;
			<span>注册时间:</span>
			<!--<input type="text" class="input-text radius" style="width:150px" placeholder="注册时间" id="jointime-search">-->
			<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">&nbsp;
			<span>用户状态:</span>
			<span class="select-box inline" style="border-radius:5px;">
				<select class="select" id="status-search">
					<option value="">全部</option>
					<option value="1">已停用</option>
					<option value="0">已启用</option>
				</select>
			</span>
			<span class="select-box inline" style="border-radius:5px;">
				<select class="select" id="role-search">
					<option value="">全部</option>
					<option value="1">商家</option>
					<option value="0">用户</option>
				</select>
			</span>&nbsp;
			<button type="button" class="btn" id="btn-advanced-search"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</form>
	</div>
	<div class="mt-20">
		<table id = "UserId" class="table table-border table-bordered table-hover table-bg table-sort mytable">
			<thead>
			<tr class="text-c">
				<th width="30"><input type="checkbox" name="cb-check-all" value=""></th>
				<th>ID</th>
				<th>用户名</th>
				<th>性别</th>
				<th>手机</th>
				<th>头像</th>
				<th>生日</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="userbody"></tbody>
		</table>
	</div>
	<br><br><br><br>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/spin-2.1.0/jquery.spin.merge.js"></script>
<script type= "text/javascript" src="static/js/constant.js"></script>
<script type= "text/javascript" src="static/js/userlist.js"></script>
<script type="text/javascript"></script>
</body>
</html>