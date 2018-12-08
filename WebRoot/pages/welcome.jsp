<%--
  Created by IntelliJ IDEA.
  User: KelipuTe
  Date: 2017/9/18
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../pages/head.jsp"%>
<script src="/CloudStorage/js/welcome.js" type="text/javascript"></script>
<title>百度网盘_享你所想</title>
</head>
<body>
	<%--网页顶部--%>
	<%@include file="../pages/header.jsp"%>
	<%--网页主体--%>
	<div id="bd" class="global-clearfix">
		<div id="yao-main" node-type="right-box">
			<div class="yao-b">
				<%@include file="../pages/left.jsp"%>
			</div>
			<div class="main-content">
				<div class="content-top">
					<div class="content-top-left">
						<a href="#">
							<button class="file_upload" id="file_upload"
								onclick="fileUpload()">上传</button> </a> <a href="#" id="isa">
							<button class="new_files" id="new_files" onclick="addnewfolder()">新建文件夹</button>
						</a>
					</div>
					<div class="content-top-right" hidden>
						<a href="#">
							<button class="file_function">分享</button> </a> <a href="#">
							<button class="file_function1">下载</button> </a> <a href="#">
							<button class="file_function1">删除</button> </a> <a href="#">
							<button class="file_function1">重命名</button> </a> <a href="#">
							<button class="file_function1">复制到</button> </a> <a href="#">
							<button class="file_function2">移动到</button> </a>
					</div>
				</div>
				<div class="content-body">
					<div class="content-body-top" id="file_title1">
						<span class="file_title1"><a
							ondblclick="showFileByName('我的云盘')">我的云盘</a>
						</span>
					</div>
					<div class="content-body-table">
						<div class="table_title">
							<ul class="table_title_ul">
								<li class="table_title_li1"><span class="text">文件名</span></li>
								<li class="table_title_li2"><span class="text">大小</span></li>
								<li class="table_title_li3"><span class="text">修改时间</span>
								</li>
							</ul>
						</div>
						<div class="table_body" id="table_body"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- 文件右击菜单 -->
	<div class="contextMenu" id="myMenu1">
		<ul class="mymenu1">
		    <li id="open1" data-toggle='modal' data-target='#shareModal'><a>打开</a>
			</li>
			<li id="download1"><a id="download_a">下载</a>
			</li>
			<li id="share1"><a>分享</a>
			</li>
			<li id="copy1"><a>复制</a>
			</li>
			<li id="cut1"><a>剪切</a>
			</li>
			<li id="rename1"><a>重命名</a>
			</li>
			<li id="delete1"><a>删除</a>
			</li>
			<li id="attribute1"><a>属性</a>
			</li>
		</ul>
	</div>
	<!-- 文件夹右击操作 -->
	<div class="contextMenu" id="myMenu2">
		<ul class="mymenu1">
			<li id="open2"><a>打开</a>
			</li>
			<!--  <li id="download2"><a>下载</a></li> -->
			<li id="share2"><a>分享</a>
			</li>
			<li id="copy2"><a>复制</a>
			</li>
			<li id="cut2"><a>剪切</a>
			</li>
			<li id="rename2"><a>重命名</a>
			</li>
			<li id="delete2"><a>删除</a>
			</li>
			<li id="attribute2"><a>属性</a>
			</li>
		</ul>
	</div>
	<div class="contextMenu" id="myMenu3">
		<ul class="mymenu1">
			<li id="onload"><a>上传</a>
			</li>
			<li id="newfolder"><a class="" id="myMenu"
				onclick="addnewfolder()">新建文件夹</a>
			</li>
			<li id="refresh"><a>刷新</a>
			</li>
			<li id="paste"><a>粘贴</a>
			</li>
			<li id="sort"><a>排序方式</a>
			</li>
		</ul>
	</div>
	<input id="currentpath" type="text" value="${user_online.homepath}"
		hidden="true" display="none">
	<input id="usedMemory" type="text" value="${user_online.restspace}"
		hidden="true" display="none">
	<input id="currentpath1" type="text" value="${actionMessages[0]}"
		hidden="true" display="none">

	<div class="panel panel-default" id="upload" hidden
		style="position:absolute;left:30%;top:20%;z-index:1000;width: 600px;border: 3px solid rgba(0,0,0,0.11)">
		<div class="panel panel-heading"
			style="position:relative;float:left;margin:0;padding:0;height:35px;width: 100%;background-color: rgba(0,0,0,0.11)">
			<img src="/CloudStorage/images/cancel.png"
				style="position:relative;margin:0;padding:0;width: 30px;height: 30px;float:right;"
				onclick="cancelUpload()">
			<h4 style="margin-top:7px;color:#1296DB">文件上传</h4>
		</div>
		<div class="panel panel-body" style="width:100%;">
			<div id="uploader" class="wu-example">
				<!--用来存放文件信息-->
				<div id="thelist" class="uploader-list"></div>
				<div class="btns">
					<div id="attach"></div>
					<div style="text-align:center;margin-top:5px;">
						<button id="remove"
							class="btn-lg btn-default btn-primary pull-left"
							style="width:275px">移除</button>
						<button id="upload"
							class="btn-lg btn-default btn-primary pull-right"
							style="width:275px">上传</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 分享文件遮罩窗体 -->
	<div class="modal fade" id="shareModal" tabindex="-1" role="dialog"
		aria-labelledby="shareModalLabel" style="left:-300px;">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="height:700px;width:1000px;}">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="shareModalLabel">在线预览</h4>
				</div>
				<div class="modal-body" id="filePreview"
					style="width:100%;height:100%;text-align:center;">
				</div>

			</div>
		</div>
	</div>
	<div class="panel panel-default" id="actionMessage" hidden
		style="position:absolute;left:38%;top:75%;z-index:1000;width: 400px;border: 3px solid rgba(0,0,0,0.11)">
		<div class="panel panel-heading"
			style="position:relative;float:left;margin:0;padding:0;height:35px;width: 100%;background-color:#D0D0D0">
			<img src="/CloudStorage/images/cancel.png"
				style="position:relative;margin:0;padding:0;width: 30px;height: 30px;float:right;"
				onclick="cancelUpload()">
			<h4 style="margin-top:7px;color:#1296DB">错误信息</h4>
		</div>
		<div class="panel panel-body" style="width:100%;">
			<div id="error" class="wu-example">
				<p><s:actionmessage/></p>
			</div>
		</div>
	</div>
</body>

</html>
