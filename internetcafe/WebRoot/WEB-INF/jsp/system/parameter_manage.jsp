<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>参数管理</title>
</head>
<body>
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="${ctxRoot}">首页</a>
        </li>
        <li>
            系统管理
        </li>
        <li class="active">
            参数管理
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="lieb_lis">
        <div class="lieb_li" id="parentId">
            <jsp:include page="parameter_manage_parent_pop.jsp">
                <jsp:param name="name" value="1" />
            </jsp:include>
        </div>
        <div class="lieb_li" id="childrenId">
            <jsp:include page="parameter_manage_child_pop.jsp">
                <jsp:param name="name" value="1" />
            </jsp:include>
        </div>
    </div>
</body>
</html>
