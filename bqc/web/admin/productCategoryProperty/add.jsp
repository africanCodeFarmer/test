<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="../../include/layui/css/layui.css">
<script src="../../include/layui/layui.js"></script>

</head>
<body>
<div style="padding:15px;">新增 ${father_pojo.name} 属性</div>

<form class="layui-form" action="" style="padding:15px;">
	<input type="hidden" name="pcid" value="${father_pojo.id}">

	<div class="layui-form-item">
	    <label class="layui-form-label">名字</label>
	    <div class="layui-input-block">
	    	<input type="text" name="name" class="layui-input" required lay-verify="required">
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
            url: "../addProductCategoryProperty",
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