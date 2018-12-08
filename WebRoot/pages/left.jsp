<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2017/9/19
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<div node-type="module" class="module-aside DtJtsC" style="height: 100%; background: #eff4f8;">
    <div class="KHbQCub"></div>
    <ul class="duEaNs">
        <li node-type="list-item" data-key="all" class="b-list-item">
            <a class="sprite2 b-no-ln" hidefocus="true" onclick="showFileByName('${user_online.homepath}')">
                    <span class="text1">
                        <span node-type="img-ico" class="icon icon-disk"></span>
                        全部文件
                    </span>
            </a>
        </li>
        <!-- image,application,text,audio,video,zip -->
        <li node-type="list-item" data-key="pic" class="b-list-item">
            <a node-type="pic-item" cat="3" class="b-no-ln" hidefocus="true" onclick="showFileByType('image')">
               <span class="text1">
                    <span class="img-ico aside-mpic"></span>
                    图片
               </span>
            </a>
        </li>
        <li node-type="list-item" data-key="doc" class="b-list-item">
            <a cat="4" class="b-no-ln" hidefocus="true" onclick="showFileByType('text')">
                 <span class="text1">
                    <span class="img-ico aside-mdoc"></span>
                     文档
                 </span>
            </a>
        </li>
        <li node-type="list-item" data-key="video" class="b-list-item">
            <a cat="3" class="b-no-ln" hidefocus="true" onclick="showFileByType('video')">
                <span class="text1">
                    <span class="img-ico aside-mvideo"></span>
                    视频
                </span>
            </a>
        </li>
        <li node-type="list-item" data-key="mbt" class="b-list-item">
            <a cat="7" class="b-no-ln" hidefocus="true" onclick="showFileByType('audio')">
                <span class="text1">
                    <span class="img-ico aside-mbt"></span>
                    音乐
                </span>
            </a>
        </li>
        <li node-type="list-item" data-key="music" class="b-list-item">
            <a cat="1" class="b-no-ln" hidefocus="true" onclick="showFileByType('zip')">
                <span class="text1">
                    <span class="img-ico aside-mmusic"></span>
                压缩包           
                </span>
            </a>
        </li>
        <li node-type="list-item" data-key="other" class="b-list-item">
            <a cat="6" class="b-no-ln" hidefocus="true" onclick="showFileByType('application')">
                <span class="text1">
                    <span class="img-ico aside-moth"></span>
                    其它
                </span>
            </a>
        </li>

        <li node-type="list-item" data-key="share" class="b-list-item bHzsaPb">
            <a class="sprite2 b-no-ln" hidefocus="true" href="#">
                <span class="text1">
                    <span node-type="img-ico" class="icon icon-share"></span>
                    我的分享
                </span>
            </a>
        </li>

        <li node-type="list-item" data-key="recyclebin" class="b-list-item">
            <a class="sprite2 b-no-ln${recycleclass}" hidefocus="true" href="#">
                <span class="text1">
                    <span node-type="img-ico" class="icon icon-delete"></span>
                    回收站
                </span>
            </a>
        </li>
    </ul>
    <div node-type="remaining" class="QGOvsxb" style="visibility: visible; position: absolute; top: auto; bottom: 80px;">
        <ul class="tDuODs">
            <li class="global-clearfix bar">
                <div node-type="quota-full" class="remainingSpaceUi">
                <span node-type="quota-perc" class="remainingSpaceUi_span" id="remainingSpaceUi_span" style="background: rgb(146, 239, 85);">
                </span>
                </div>
                <div class="DIeHPCb">
                    <span node-type="quota-used" class="bold"> <fmt:formatNumber type="number" value="${2048-user_online.restspace}" pattern="0.0" maxFractionDigits="1"/> M</span>/<span node-type="quota-total">2048M</span>
                </div>
                <div class="GELdyA">
                    <a class="remaining-quota-kr" node-type="quota-add-disk" target="_blank" href="https://yun.baidu.com/buy/center?tag=8&from=wzl">
                        扩容</a>
                </div>
            </li>
        </ul>
    </div>
</div>