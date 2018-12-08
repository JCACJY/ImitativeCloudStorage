<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2017/9/27
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>百度网盘 享你所享</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"  type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="login_body">
   <div class="login_content" >
       <div class="login_main">
           <div class="left_text">
             <div class="index-body-content">
                 <p><span class="left-quote"></span><span>好友分享</span></p>
                 <p><span>共度幸福时光</span><span class="right-quote"></span></p>
             </div>
         </div>
           <div class="login_middle">
               <div class="login_header">
                   <div class="login_header_title">
                       <h4 class="">帐号密码登录</h4>
                   </div>
                   <s:form cssClass="login_form" action="/user/_login.action" theme="simple">
                   <%-- <form class="login_form" action="${pageContext.request.contextPath}/user/_login.action" method="post"> --%>
                   	   <s:token></s:token>
                       <p id="login_form_error" class="login_form_error">
                           <span id="error_span" class="error_span">
                           	 
                           </span>
                       </p>
                       <p id="userNameWrapper" class="userNameWrapper">
                           <label for="userName_id" id="userNameLabel" class="userNameLabel">手机/邮箱/用户名</label>
                           <input id="userName_id" type="text" name="phone" class="userName_class" autocomplete="off" value="" placeholder="手机/邮箱/用户名">
                       </p>
                       <p id="passwordWrapper" class="passwordWrapper">
                           <label for="password_id" id="passwordLabel" class="passwordLabel">密码</label>
                           <input id="password_id" type="password" name="password" class="password_class" value="" placeholder="密码">
                       </p>
                       <p id="submitWrapper" class="submitWrapper">
                           <input id="submit_id" type="submit" value="登录" class="submit_class">
                           <a class="login_register" href="/CloudStorage/pages/register.jsp">立即注册</a>
                           <a class="login_problem" href="#" target="_blank">登录遇到问题</a>
                       </p> 
                       <s:fielderror theme="simple">
                       		<s:param>phone</s:param>
                       		<s:param>password</s:param>
                       </s:fielderror>
                      <s:if test="hasActionMessages()"><s:actionmessage theme="simple"/></s:if>
                  <!--  </form> -->
                   </s:form>
               </div>

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
