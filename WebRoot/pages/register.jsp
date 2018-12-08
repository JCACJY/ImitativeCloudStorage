<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2017/9/28
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>注册云盘账号</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"  type="text/javascript"></script>
    <link href="/CloudStorage/css/style.css" rel="stylesheet" type="text/css">
    <script src="/CloudStorage/js/register.js" type="text/javascript"></script>
</head>
<body class="register_body">
<div class="register_content">
    <div class="register_table">
        <div class="register_table_title">
            <p>百度云账号注册</p>
        </div>
        <div class="register_table_content">
            <s:form class="register_table_content" action="/user/_adduser.action" theme="simple">
            	<s:token></s:token>
                <table>
                    <tr>
                        <td><label>手机号</label></td>
                        <td><input type="tel" name="phone" id="pnumber" class="input_text" autofocus></td>
                    </tr>
                    <tr>
                        <td><label>密码</label></td>
                        <td><input type="password" name="password" id="pwd" class="input_text"></td>
                    </tr>
                    <tr>
                        <td><label>重复密码</label></td>
                        <td><input type="password" name="password_repeat" id="repwd" class="input_text"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="注册" id="btn_register" class="btn_reg"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p id="error_text"></p></td>
                        <s:fielderror>
                        	<s:param>phone</s:param>
                        	<s:param>phone</s:param>
                        </s:fielderror>
		                <s:if test="hasActionMessages()"><s:actionmessage/></s:if>
                    </tr>
                </table>
            </s:form>
        </div>
        <div class="to_login">
           已有账号？ <a href="/CloudStorage/pages/login.jsp"><span>登录</span></a>
        </div>

    </div>
</div>
<div class="login_buttom">
    ©2017 Baidu |
    <a href="#">服务协议</a> |
    <a href="#">权利声明</a> |
    <a href="#">帮助中心</a> |
</div>
</body>
</html>
