<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../public/header.jsp" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>

<style>
	canvas#captcha{
		cursor: pointer;
		display:inline-block;
		float:right;
	}
	input.captcha-input{
		width:120px;
		display:inline-block;
	}
	
	div.login-window{
		margin-top:100px;
		background: rgba(242,242,242,0.8);
		border:1px solid gray;
	}
	div.login-window div.layui-card-header{
		padding-top:10px;
		padding-bottom:10px;
	}
	
	div.footer-div{
		position:fixed;
		bottom:0;
		padding:10px 0;
		width:100%;
		text-align:center;
		font-size:20px;
		
		background-color:#F2F2F2;
	}
	
	body{
		background: url(../image/admin/login_back.gif) no-repeat; 
		background-size:100% 100%; 
		background-attachment:fixed;
	}
	
	div.layui-container{
		width:100%;
	}
</style>

<body>
	<blockquote class="layui-elem-quote">
		<h2>后台管理</h2>
	</blockquote>
	
	<div class="layui-container">		
		<div class="layui-row">
			<div class="layui-col-md-offset7 layui-col-md4">
				<div class="layui-card login-window">
				  <div class="layui-card-header"><h2>管理员登录</h2></div>
				  <div class="layui-card-body">
				  	<form class="layui-form" action="index">
				  	
					  <div class="layui-form-item">
					    <label class="layui-form-label">用户名</label>
					    <div class="layui-input-block">
					      <input type="text" name="name" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">密码</label>
					    <div class="layui-input-block">
					      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">验证码</label>
					    <div class="layui-input-block">
					      <input type="text" name="captcha" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input captcha-input">
					      <canvas  id="captcha" width="150" height="40"></canvas>
					    </div>
					  </div>
					  
					  <hr class="layui-bg-black">
					  
					  <div class="layui-form-item">
					  	 <div class="layui-row">
					  	 	<div class="layui-col-md5">
						  		<button class="layui-btn-lg layui-btn login-btn layui-btn-fluid" lay-submit lay-filter="login">登录</button>
					      	</div>
					      	<div class="layui-col-md-offset2 layui-col-md5">
					      		<button type="reset" class="layui-btn-lg layui-btn layui-btn-primary layui-btn-fluid">重置</button>
							</div>
						</div>
					  </div>
					  
					</form>
				  </div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer-div">
		©  北墘村   All   Rights Reserved
	</div>
	
<script>
layui.use('form', function(){
	var form = layui.form;
	var $ = layui.jquery;
	
	var show_num = [];
	draw(show_num);
		  
  //登录监听
  form.on('submit(login)', function(data){
	var captcha = data.field.captcha.toLowerCase();
	var num = show_num.join("");
	
	if(captcha==num){ //验证码正确
		var login_success = false;
		$.ajax({
			type:"post",
		   	url: 'loginValidate',
		  	async:false,
		   	data:{name:data.field.name,password:data.field.password},
		   	success: function(result){
		   		if(result['code']=='0'){
		   			login_success = true;
		   		}
		   	}
		});
		
		//用户或密码错误
		if(login_success==false){
			layer.msg('用户名或密码错误', {icon: 2}, 1000);
			return false;
		}
	}
	else{ //验证码错误
		layer.msg('验证码错误', {icon: 2}, 1000);
		return false;
	}
  });
  
  //验证码点击监听
  $("#captcha").on('click',function(){
      draw(show_num);
  })
  
    //生成验证码
	function draw(show_num) {
	    var canvas_width=$('#captcha').width();
	    var canvas_height=$('#captcha').height();
	    var captcha = document.getElementById("captcha");//获取到canvas的对象，演员
	    var context = captcha.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
	    captcha.width = canvas_width;
	    captcha.height = canvas_height;
	    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
	    var aCode = sCode.split(",");
	    var aLength = aCode.length;//获取到数组的长度
	    
	    for (var i = 0; i <= 3; i++) {
	        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
	        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
	        var txt = aCode[j];//得到随机的一个内容
	        show_num[i] = txt.toLowerCase();
	        var x = 10 + i * 20;//文字在canvas上的x坐标
	        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
	        context.font = "bold 23px 微软雅黑";
	
	        context.translate(x, y);
	        context.rotate(deg);
	
	        context.fillStyle = randomColor();
	        context.fillText(txt, 0, 0);
	
	        context.rotate(-deg);
	        context.translate(-x, -y);
	    }
	    for (var i = 0; i <= 5; i++) { //验证码上显示线条
	        context.strokeStyle = randomColor();
	        context.beginPath();
	        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
	        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
	        context.stroke();
	    }
	    for (var i = 0; i <= 30; i++) { //验证码上显示小点
	        context.strokeStyle = randomColor();
	        context.beginPath();
	        var x = Math.random() * canvas_width;
	        var y = Math.random() * canvas_height;
	        context.moveTo(x, y);
	        context.lineTo(x + 1, y + 1);
	        context.stroke();
	    }
	}
	function randomColor() {//得到随机的颜色值
	    var r = Math.floor(Math.random() * 256);
	    var g = Math.floor(Math.random() * 256);
	    var b = Math.floor(Math.random() * 256);
	    return "rgb(" + r + "," + g + "," + b + ")";
	}
});
    	 
</script>
</body>
</html>
