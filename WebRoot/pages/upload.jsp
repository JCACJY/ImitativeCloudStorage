<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>Upload.html</title>
	<link rel="stylesheet" type="text/css" href="/CloudStorage/css/webuploader.css">
	<script type="text/javascript" src="/CloudStorage/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="/CloudStorage/js/webuploader.min.js"></script>
	<script type="text/javascript" src="/CloudStorage/js/uploadControl.js"></script>
  </head>
  
  <body>
  	<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="attach"></div>
        <input type="button" value="移除" id="remove"/>
        <input type="button" value="上传" id="upload"/>
    </div>
	</div>
  	
  </body>
</html>

