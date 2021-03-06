<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../../include/layui/css/layui.css">
<script src="../../include/layui/layui.js"></script>

</head>
<body>
<form class="layui-form" action="" style="padding:15px;">
	<div class="layui-form-item">
	    <label class="layui-form-label">名字</label>
	    <div class="layui-input-block">
	    	<input type="text" name="name" class="layui-input" required lay-verify="required">
	    </div>
	</div>
	
	<div class="layui-form-item">
	    <label class="layui-form-label">密码</label>
	    <div class="layui-input-block">
	    	<input type="text" name="password" class="layui-input" required lay-verify="required">
	    </div>
	</div>
	
	<div class="layui-form-item">
        <label class="layui-form-label" style="">类型</label>
        <div class="layui-input-inline">
            <select name="type" lay-verify="" class="layui-select statusSelect">
                <option value ="0">普通用户</option>
                <option value ="1">商家</option>
                <option value ="2">管理员</option>
            </select>
        </div>
    </div>
	
	<div class="layui-form-item">
	    <label class="layui-form-label"></label>
	    <div class="layui-input-block">
	        <input type="submit" value="确认" class="layui-btn" lay-submit lay-filter="submit">
	    </div>
	</div>
</form>
</body>

<script>
layui.use(['form', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    
    //重新渲染动态填充的数据
    form.render();

    form.on('submit(submit)', function(data){
        $.ajax({
            type: "post",
            async:false,
            url: "addUser",
            data: $("form").serialize(),//表单数据
            success: function (result) {
	            var index = parent.layer.getFrameIndex(window.name);
	            //父页面关闭layer
	            parent.layer.close(index);
	            //父页面table重载
	            parent.layui.table.reload('test', {
	                // page: {
	                //     curr: 1 //重新从第 1 页开始
	                // }
	            });
	            parent.layer.msg('添加完毕');
            }
        });

        return false;
    });
})
</script>
</html>