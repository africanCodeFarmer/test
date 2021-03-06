<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../public/header.jsp" %> 

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>layout 后台大布局 - Layui</title>
 
<style>
	.cursorPointer{
		cursor:pointer;
	}
	div.search-div{
	
	}
	div.search-div input{
		display:inline-block;
		height:32px;
	}
	div.search-div div{
		padding-left:5px;
		display:inline-block;
	}
	.redColor{
		color:#FF5722;
	}
</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo cursorPointer">北墘 后台</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href=""><span class="layui-icon layui-icon-home"></span> 北乾主页</a></li>
      <li class="layui-nav-item"><a href=""><span class="layui-icon layui-icon-cart-simple"></span> 北乾商城</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="../image/admin/head_image_sample.jpg" class="layui-nav-img">
          洪兴林
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
      	<li class="layui-nav-item"><a href="manageProductCategory">商品分类管理-分类和属性</a></li>
      	<li class="layui-nav-item layui-nav-itemed"><a href="manageUser">用户管理</a></li>
      	<li class="layui-nav-item"><a href="manageProduct">产品管理</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 工作区-->
    <div style="padding: 15px;">
    	<div class="layui-breadcrumb">
		  <a><cite>用户管理</cite></a>
		</div>
		
	      <div class="search-div" style="padding-top:15px;">
			<input class="layui-input" id="searchNameInput" type="text" placeholder="名字" style="width:100px;">
			<div class="layui-btn-container">

				<button class="layui-btn layui-btn-sm" id="searchBtn"><span class="layui-icon layui-icon-search"></span></button>
			    <button class="layui-btn layui-btn-sm layui-btn-primary" id="resetBtn"><span class="layui-icon layui-icon-close"></span></button>

		    </div>
		  </div>
		
    	<table id="demo" lay-filter="test"></table>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © beiqian.com
  </div>
</div>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add">添加</button>
    <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">批量删除</button>
    <button class="layui-btn layui-btn-sm layui-btn-disabled" lay-event="update">编辑</button>
  </div>
</script>
<script type="text/html" id="barDemo">
<a class="cursorPointer" lay-event="edit">
	<span class="layui-icon layui-icon-edit"></span>
</a>
<span style="padding:0px 10px;"></span>
<a class="cursorPointer" lay-event="del">
	<span class="layui-icon layui-icon-delete redColor"></span>
</a>
</script>

<script>
//JavaScript代码区域
layui.use(['element','table','form'], function(){
  var element = layui.element;
  var table = layui.table;
  var form = layui.form;
  var $ = layui.jquery;
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,url: 'listUser' //数据接口
    ,height: 'full-240'
   	,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
           layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
           ,limit: 5 //数据分页初始大小
           ,limits: [5, 10, 50, 100, 200, 500]
           ,groups: 10 //显示 10 个连续页码
           ,first: true //显示首页
           ,last: true //显示尾页
    }
    ,cols: [[ //表头
      {type:'checkbox'}
      ,{field: 'id', title: 'ID', width:80, sort: true}
      ,{field: 'name', title: '名字', width:200}
      ,{field: 'password', title: '密码', width:200}
      ,{field: 'typeText', title: '类型', width:100}
      ,{title:'操作', toolbar: '#barDemo', width:100,align:'center'}
    ]]
	,toolbar: '#toolbarDemo'
	,done: function(res, curr, count){
	    /* //如果是异步请求数据方式，res即为你接口返回的信息。
	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	    console.log(res);
	    
	    //得到当前页码
	    console.log(curr); 
	    
	    //得到数据总量
	    console.log(count); */
    }
  	,id:'test'
  });
  
  //搜索重置按钮监听
  $("button#searchBtn").click(function(){
	  var search_name = $("input#searchNameInput").val();

		//执行重载
		table.reload('test', {
		    page: {
		        curr: 1 //重新从第 1 页开始
		    }
		    , where: {
		    	search_name: search_name,
		    }
		});
  })
  $("button#resetBtn").click(function(){
	  $("input#searchNameInput").val('');
	  
		//执行重载
		table.reload('test', {
		    page: {
		        curr: 1 //重新从第 1 页开始
		    }
		    , where: {
		    	search_name: null,
		    }
		}); 
  })
  
  //操作栏监听
  table.on('toolbar(test)', function(obj){
	  var checkStatus = table.checkStatus(obj.config.id);
	  var data = checkStatus.data;
	  let ids=[];
	  data.forEach(function (element) {
	    ids.push(element.id);
	  });
	  
	  switch(obj.event){
	    case 'add':
	      //layer.msg('添加');
	      
	      layer.open({
	            type: 2,
	            title: '添加数据',
	            shade: false,
	            maxmin: true,
	            area: ['60%', '60%'],
	            content: "user/add.jsp",
	      });
	    break;
	    case 'delete':
	    	layer.confirm('真的批量删除行么', function(index){
	            layer.close(index);
	            
	            //删除
		        $.ajax({
		            type: "post",
		            url: "deleteUser",
		            traditional: true,
		            data: {
		                id: ids,
		            },
		            success: function (result) {
		            	layui.table.reload('test', {
		            		page: {
			                	curr: 1 //重新从第 1 页开始
			                }
			            });
		            	layer.msg('删除完毕');
		            }
		        });
	          });
	    break;
	    case 'update':
	      //layer.msg('编辑');
	    break;
	  };
  });
  
  //监听工具栏事件
  table.on('tool(test)', function(obj){
    var data = obj.data;
    var id = data.id; //点击行id
    
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        //obj.del();
        layer.close(index);
        
        //删除
        $.ajax({
            type: "post",
            url: "deleteUser",
            traditional: true,
            data: {
                id: [id],
            },
            success: function (result) {
            	layui.table.reload('test', {
            		page: {
	                	curr: 1 //重新从第 1 页开始
	                }
	            });
            	layer.msg('删除完毕');
            }
        });
      });
    } else if(obj.event === 'edit'){
    	layer.open({
            type: 2,
            title: '编辑数据',
            shade: false,
            maxmin: true,
            area: ['60%', '60%'],
            content: "editUser?id="+id,
        });
    }
  });
});
</script>
</body>
</html>