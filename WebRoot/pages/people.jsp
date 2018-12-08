<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/per.css" type="text/css">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>个人中心</title>
<script type="text/javascript">
	function fn_browse() {
		document.form_per.image.click();
	}

	function preview(){
		var img=document.getElementById("imgFile");
		var icon=document.getElementById("userIcon");
		icon.src=window.URL.createObjectURL(img.files[0]);
	}
	$(function(){
		$(".del_btn").click(function(){
			$(".msg_box").hide();
		});
	});
</script>
</head>
<body style="background-color: #eff4f8;">
	<div class="main">
		<aside class="menu">
			<ul class="ul1 ">
				<li class="person"><br><a href="#s1"><span></span></a></li><li class="person"><a href="#"><span>个人中心</span></a></li>
				<hr>
				<li class="person">
					<p>个人资料</p>
					<ul class="nav">
						<li><a href="#s1" data-toggle="tab">个人信息</a></li>
						<li><a href="#s2" data-toggle="tab">安全设置</a></li>
						<li><a href="/CloudStorage/pages/welcome.jsp">返回首页</a></li>
					</ul>
				</li>
				<li>
					<b>容量</b>
					<div class="progress">
						<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
							60%
						</div>
						
					</div>
					<div class="more_cloud"><a href="https://yun.baidu.com/buy/center?tag=8&from=wzl"><p>扩容</p></a></div>
				</li>
			</ul>
		</aside>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="s1">
				<p>个人信息</p>
				<div class="mes">
					<s:form action="/user/_changebaseinfo.action" name="form_per" enctype="multipart/form-data" method="post">
						<table>
							<tr>
								<td><b>头像：</b></td>
								<td>
								    <a href="javascript:fn_browse();">
										<img src="${user_online.avaterurl}" class="img-circle" height="80px" width="80px" id="userIcon">
									</a>
									<input type="file" name="image" style="display: none" id="imgFile" onchange="preview()"/>
								</td>
									<c:if test="${user_online.vip==1 }">
										<div class="vip"><a href="http://yun.baidu.com/buy/center?tag=8&from=wzl"><img src="/CloudStorage/images/vip_on.png"></a></div>
									</c:if>
									<c:if test="${user_online.vip==0 }">
										<div class="vip"><a href="http://yun.baidu.com/buy/center?tag=8&from=wzl"><img src="/CloudStorage/images/vip_off.png"></a></div>
									</c:if>
							</tr>
							<tr>
								<td><b>手机：</b></td>
								<td><p style="margin-top: 10px">${user_online.phone}</p></td>
							</tr>
							<tr>
								<td><b>用户名：</b></td>
								<td><input value="${user_online.username}" type="text" name="username" />
								</td>
							</tr>
							<tr>
								<td><b>邮箱：</b></td>
								<td><input value="${user_online.mailbox}" type="email" name="mailbox" /></td>
							</tr>
						</table>
						<div class="pupdate">
							<input type="submit" value="确定修改" class="up_btn"/>
						</div>
						<s:fielderror theme="simple">
							<s:param>username</s:param>
							<s:param>iamge</s:param>
							<s:param>password</s:param>
						</s:fielderror>
							<s:if test="hasActionMessages()">
							<div class="msg_box">
								<div class="del_btn"><p>×</p></div>
								<div class="msg_boxin">
									<p><s:actionmessage/></p>
								</div>
							</div>
							</s:if>
					</s:form>
				</div>
			</div>
			<div class="tab-pane fade" id="s2">
				<p>安全设置</p>
				<div>
				<s:form action="/user/_changepassword.action" theme="simple">
					<table>
						<tr>
							<td><b>原密码：</b></td>
							<td><input name="password" value="" type="text" id="ypwd"/></td>
						</tr>
						<tr>
							<td><b>新密码：</b></td>
							<td><input name="newpassword" value="" type="password" id="xpwd"/></td>
						</tr>
						<tr>
							<td><b>重复新密码：</b></td>
							<td><input name="repetitivepassword" value="" type="password" id="rxpwd"/></td>
						</tr>
					</table>
					<div class="pupdate">
						<input type="submit" value="确认修改" class="up_btn"/><!-- onclick="update()" -->
					</div>
				</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>