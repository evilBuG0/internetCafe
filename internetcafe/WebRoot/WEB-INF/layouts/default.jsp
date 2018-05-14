<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<% request.setAttribute("path", request.getContextPath()); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/layouts/meta.jsp" %>
    <sitemesh:head/>
    <title>XXX学校实验室机房反馈系统</title>
</head><%--
about:blank
--%><body>
<iframe id='callbackframe' name='callbackframe'  src='about:blank' style='display:none'></iframe>
<div id='background' style="display:block;" class='background'></div>
<div id='progressBar' style="display:block;" class='progressBar'>数据加载中，请稍等...</div>
<!--<![endif]-->
<%@ include file="/WEB-INF/layouts/header.jsp" %>

<div class="main-container" id="main-container">
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <jsp:include page="menu.jsp"/>
        <div class="main-content" id="mainId">
            <sitemesh:body/>
        </div>

    </div>
</div>
<div id="_winId" style="display:none">
    <div style="top:0;left:0;position:fixed;z-index:1000;" class="bg"></div>
    <div class="beian_winBG">
        <span class="ui-dialog-title">
            <div class="widget-header widget-header_height">
                <h4 class="smaller font_y"><i class="icon-plus-sign green"></i><span id="_winTitle"></span></h4>
            </div>
        </span>
        <div id="_winContentId">

        </div>
    </div>
</div>
</body>
</html>