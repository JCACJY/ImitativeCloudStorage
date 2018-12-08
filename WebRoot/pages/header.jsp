<%--
  Created by IntelliJ IDEA.
  User: KelipuTe
  Date: 2017/9/18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="hd">
    <div class="module-header">
        <div node-type="module-header-wrapper" class="module-header-wrapper" style="height: 62px;">
            <dl class="xtJbHcb">
                <dt class="EHazOI"><a onclick="showFileByName('${user_online.homepath}')" target="_self" title="百度网盘"></a></dt>
                <dd class="vyQHNyb" node-type="header-link">
                    <span class="cMEMEF wGMtMgb" node-type="disk-home">
                        <a href="/CloudStorage/pages/welcome.jsp" target="_self" title="网盘" node-type="item-title">网盘</a>
                        <span class="gICyHO"></span>
                    </span>
                    <span class="cMEMEF " node-type="mbox-homepage">
                        <a href="/CloudStorage/pages/chat.jsp" onclick="addShareFile()" target="_self" title="分享" node-type="item-title">好友</a>
                        <span class="gICyHO"></span>
                    </span>
                    <span class="cMEMEF " node-type="find-apps">
                        <a href="" target="_self" title="更多" node-type="item-title">更多</a>
                        <span class="gICyHO"></span>
                        <i class="find-light-icon"></i>
                    </span>
                </dd>
                <dd class="CDaavKb" node-type="header-apps">
                   <span class="DIcOFyb" node-type="app-user-info">
                                <span class="user-photo-box">
                                   <a class="user-photo" href="people.jsp"
                                      style="background-image:url(${user_online.avaterurl});"></a>
                                </span>
                                <span class="user-name">${user_online.username}</span>
                                <span class="user-exit">
                                    <a href="/CloudStorage/user/_logout.action">注销</a>
                                </span>
                        <a class="JS-user-level level-0" node-type="app-user-level"
                           href="/buy/center?tag=8&amp;from=hicon" target="_blank"></a>
                        <i node-type="img-ico" class="NxuPcOb icon icon-dropdown-arrow"></i>
                    </span>
                </dd>
                <dd class="gOIbzPb" node-type="header-union">
                    <a href="#" target="_blank" class="BHgeMdb" title="超级会员 超低价">
                        超级会员 超低价
                    </a>
                </dd>
            </dl>
        </div>
    </div>
</div>