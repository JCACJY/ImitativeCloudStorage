<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
  </head>
  
  <body>
    <s:form action="/user/_adduser.action">
   		<s:textfield name="phone" label="手机号码"></s:textfield>
    	<s:textfield name="username" label="用户名"></s:textfield>
    	<s:password name="password" label="密码"></s:password>
    	<s:submit value="保存"></s:submit>
    	<s:token></s:token>
    	<s:fielderror name="phone"/>
	  	<s:fielderror name="username"/>
	  	<s:fielderror name="password"/>
    </s:form>
  </body>
</html>
