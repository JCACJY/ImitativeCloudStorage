<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta charset="UTF-8">
    <title>chat</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
   
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"  type="text/javascript"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/CloudStorage/js/vue.js" ></script>
    <link href="/CloudStorage/css/style.css" rel="stylesheet" type="text/css">
    <link type="text/css" rel="stylesheet" href="/CloudStorage/css/leftSlippingBox.css">
    <link type="text/css" rel="stylesheet" href="/CloudStorage/css/amazeui.min.css">
    <script src="/CloudStorage/js/welcome.js" type="text/javascript"></script>
    
    <!-- UM相关资源 -->
<link href="/CloudStorage/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="/CloudStorage/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/CloudStorage/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="/CloudStorage/umeditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  
  <body>
 <%@include file="../pages/header.jsp"%>

<div class="container">
    <div class="row">
        <!--好友列表部分-->
        <div class="col-md-3 col-md-offset-1 col-sm-4">
            <div class="panel panel-default panel-primary" style="height: 560px;overflow:auto">
                <div class="panel panel-heading">好友列表</div>
                <div class="panel panel-body" id="friend-lists">
                    <!--<dl class="list_dl" >
                        &lt;!&ndash;一组下拉列表&ndash;&gt;
                        <dt class="list_dt">
                            <span class="_after"></span>
                            <span>a</span>
                            <span class="list_dt_icon"></span>
                        </dt>
                        <dd class="list_dd">
                            <ul class="list_ul">
                                <li class="list_li">a</li>
                                <li class="list_li">aa</li>
                                <li class="list_li">aaa</li>
                            </ul>
                        </dd>
                        &lt;!&ndash;一组下拉列表&ndash;&gt;
                    </dl>-->
                    <friend-list></friend-list>
                </div>
            </div>
        </div>
        <!--聊天窗口部分-->
        <div class="col-md-7 col-sm-8">
            <div class="panel panel-default panel-primary" style="height: 560px;">
                <div class="panel panel-heading">
                    <p id="fri_name">联系人</p>
                </div>
                <div id="chat-text" class="panel panel-body" style="height: 300px;overflow:auto">
                    
						<ul id="chatContent" class="am-comments-list am-comments-list-flip">
							<li id="msgtmp" class="am-comment" style="display: none;">
							<input id= "img_in" type="text" value="${user_online.avaterurl}" hidden>
									<img id="msgimg" class="am-comment-avatar"
										src="${user_online.avaterurl}" style="height: 50px; width: 50px" />
								<div class="am-comment-main">
									<div ff="nickname"></div>
									<div ff="content" class="am-comment-bd" style="width: auto;">此处是消息内容</div>
								</div>
							</li>
						</ul>
                    
                    
                    <!--分享文件-->
                    <!--<div class="media">
                        <div class="media-left">
                            <img src="images/default_avatar.jpg" class="img-circle" style="width: 50px;height: 50px">
                        </div>
                        <div class="media-body">
                            <div class="panel panel-default">
                                <div class="panel panel-heading">username分享文件给你</div>
                                <div class="panel panel-body">图标<a>文件</a></div>
                            </div>
                        </div>
                    </div>-->
                    <!--进度条-->
                    <!--<div class="progress">
                        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
                            <span class="sr-only">45% Complete</span>
                        </div>
                    </div>-->
                    <!--警告框-->
                    <!-- <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Warning!</strong> Better check yourself, you're not looking too good.
                    </div> -->
                </div>
                <div>
                    <!--输入框部分-->
                        <div class="form-group">
                            <div id="EditBox" class="am-g am-g-fixed" style="margin-top: -20px;">
							<!--style给定宽度可以影响编辑器的最终宽度-->
							<script type="text/plain" id="myEditor"
								style="width: 650px; height: 140px;"></script>
							
							</div>
                        </div>
                    <div style="text-align: center">
                        <!-- 分享文件按钮 -->
                        <button class="btn btn-default btn-primary" style="width: 150px" data-toggle="modal" data-target="#shareModal">
                            	分享文件
                        </button>
                        <!-- 分享文件遮罩窗体 -->
                        <div class="modal fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="shareModalLabel">分享文件</h4>
                                    </div>
                                    <div class="modal-body" id="fileList">
                                       	 
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button id="shareModalButton" type="button" class="btn btn-primary" data-dismiss="modal">分享文件</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--聊天发送按钮-->
                        <button id="send" class="btn btn-default btn-primary" style="width: 150px">发送</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!--id=friend-lists <friend-list>组件-->
<template id="template-friend-list">
    <div>
        <!-- <dl class="list_dl" v-for="item in friends">
            <dt class="list_dt">
                <span class="_after"></span>
                <span>{{$item.title}}</span>
                <span class="list_dt_icon"></span>
            </dt>
            <dd class="list_dd">
                <ul class="list_ul">
                    <li class="list_li" v-for>{{item.body}}</li>
                </ul>
            </dd>
        </dl> -->
    </div>
</template>
<script type="text/javascript">
    Vue.component('friend-list',{
        template:'#template-friend-list',
        data:function () {
            return {
                friends:[]
            }
        },
        created:function () {
            var vm = this;
            $.ajax({
                type:'GET',
                url:'/CloudStorage/friend/_getfriends',
                dataType:'json',
                success:function (data) {
                vm.friends = data;
                var i = 1;
                $.each(data,function(key,value){
                    $('#friend-lists').append("<dl class='list_dl'>"
                                               +"<dt class='list_dt'>"
                                                 +"<span class='_after'></span>"
                                                 +"<span>"+key+"</span>"
                                                 +"<span class='list_dt_icon'></span>"
                                               +"</dt>"
                                               +"<dd class='list_dd' style='maring:0 !important'>"
                                                 +"<ul class='list_ul' id='firendlist"+i+"'></ul>"
                                               +"</dd>"
                                             +"</dl>");
				    $.each(value,function(index,list){
                        $('#firendlist'+i).append("<li class='list_li' data-name='"+list.friendname+"'>"+list.friendname+"</li>")
				    });
				    ++i;
				});
				$(".list_dt").on("click",function () {
                    $('.list_dd').stop();
                    $(this).siblings("dt").removeAttr("id");
                    if($(this).attr("id")=="open"){
                        $(this).removeAttr("id").siblings("dd").slideUp();
                    }else{
                        $(this).attr("id","open").next().slideDown().siblings("dd").slideUp();
                    }
                });
                $(".list_li").on("click",function () {
                    //alert($(this).attr('data-name'));
                    var name = $(this).attr('data-name');
                    $("#fri_name").text(name);
                });
                },
                error:function(jqXHR){
                    console.log("出现错误：" +jqXHR.status);
                }
            });
        }
    });
    /*var vm_friendList = */new Vue({
        el:'#friend-lists'
    });
</script>
<!--id=friend-lists <friend-list>组件-->
<script type="text/javascript">
    $(document).ready(function () {
        /*$.ajax({
         type:'GET',
         url:'' + data,
         data:{},
         dataType:'json',
         success:function (data) {
         console.log(data);
         },
         error:function(jqXHR){
         console.log("出现错误：" +jqXHR.status);
         }
         });*/
        /*侧边栏动作*/
        $(".list_dt").on("click",function () {
            $('.list_dd').stop();
            $(this).siblings("dt").removeAttr("id");
            if($(this).attr("id")=="open"){
                $(this).removeAttr("id").siblings("dd").slideUp();
            }else{
                $(this).attr("id","open").next().slideDown().siblings("dd").slideUp();
            }
        });
        /*分享文件按钮*/
        $('#shareModalButton').on('click',function () {
            $('#chat-text').append(
                '<div class="media">' +
                    '<div class="media-left">' +
                        '<img src="images/default_avatar.jpg" class="img-circle" style="width:50px;height:50px">' +
                    '</div>' +
                    '<div class="media-body">' +
                        '<div class="panel panel-default">' +
                            '<div class="panel panel-heading">username分享文件</div>' +
                            '<div class="panel panel-body">图标<a>文件</a></div>' +
                        '</div>' +
                    '</div>' +
                '</div>'
            )
        });
    });
</script>
<script type="text/javascript">
	
	$(function(){
			//实例化编辑器
			var um = UM.getEditor('myEditor', {
				autoHeightEnabled : false,
				toolbar : [ 'emotion' ]
			});
			
			var hh = "${user_online.phone}";
			//alert(hh);
			var socket = new WebSocket(
					"ws://localhost:8080/CloudStorage/websocket?un="+hh);
			//接收服务器的消息
			socket.onmessage = function(ev) {
				var obj = eval('(' + ev.data + ')');
				addMessage(obj);
			}
			
			$("#send").click(function(){
				
				if (!um.hasContents()) { 
									// 判断消息输入框是否为空
									// 消息输入框获取焦点
									um.focus();
									// 添加抖动效果
									$('.edui-container').addClass(
											'am-animation-shake');
									setTimeout(
											"$('.edui-container').removeClass('am-animation-shake')",
											1000);
								}else {
									var nickname = "${user_online.phone}";
									//获取输入框的内容
									var txt = um.getContent();
									//获取img的src
									var imgsrc = $("#img_in").val(); 
									var friendname = $("#fri_name").text();
									//构建一个标准格式的JSON对象
									var obj = JSON.stringify({
										nickname : nickname,
										content : txt,
										to:friendname,
										imgsrc:imgsrc
									});
									// 发送消息
									socket.send(obj);
									// 清空消息输入框
									um.setContent('');
									// 消息输入框获取焦点
									um.focus();
								}
			});
			
					//人名nickname，时间date，是否自己isSelf，内容content
		function addMessage(msg) {
			$("#msgimg").attr('src',(msg.isSelf ? $("#img_in").val(): msg.imgsrc));
			$("#fri_name").text(msg.nickname);
			var box = $("#msgtmp").clone(); //复制一份模板，取名为box
			
			box.show(); //设置box状态为显示
			box.appendTo("#chatContent"); //把box追加到聊天面板中
			//box.find('[ff="nickname"]').html(msg.to); //在box中设置昵称
			box.find('[ff="content"]').html(msg.content); //在box中设置内容
			box.addClass(msg.isSelf ? 'am-comment-flip' : ''); //右侧显示
			box.addClass(msg.isSelf ? 'am-comment-warning'
					: 'am-comment-success');//颜色
			box.css((msg.isSelf ? 'margin-left' : 'margin-right'), "3%");//外边距
			
			

			$("#chat-text div:eq(0)").scrollTop(999999); //滚动条移动至最底部

		}
	});
</script>
</html>
