<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户管理</title>
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
            用户管理
        </li>
    </ul>
</div>
<div class="page-content">
    <form action="${ctx}/user_manage.do" method="post" onsubmit="return search(this);">
        <div class="row_height_s">
            <div class="form-group_li">
                <input type="text"  placeholder="用户名" name="Q_LIKE_username" class="col_d" value="${param.Q_LIKE_username}"/>
            </div>
            <button class="btn btn-primary btn-sm" type="submit">搜索
                <i class="icon-search icon-on-right bigger-110"></i>
            </button>

        </div>
    </form>
    <div class="row row_height_y">
        <div class="ui-pg-div icon_left">
            <button class="btn btn-minier btn-primary"
                    onClick="win.init({title:'添加公司',rel:''}).show('${ctx}/edit_user_pop.do',{})">新增
            </button>
        </div>
        <div class="ui-pg-div icon_left">
            <button class="btn btn-minier btn-info"
                    onClick="win.init({title:'修改公司'}).show('${ctx}/edit_user_pop.do?id={id}',{})">修改
            </button>
        </div>
        <div class="ui-pg-div icon_left">
            <button class="btn btn-minier btn-danger"
                    onClick="win.init({title:'确定要删除吗？'}).confirm('${ctx}/delete_user.do?id={id}')">删除
            </button>
        </div>
        <div class="ui-pg-div icon_left">
            <button class="btn btn-minier btn-info"
                    onclick="win.init({title:'重置密码'}).show('${ctx}/reset_password_pop.do?id={id}')">重置密码
            </button>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover dataTable">
            <thead>
            <tr>
                <th class="center">
                    <label>
                        <input type="checkbox" class="ace" onclick="checkAll(this);">
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>公司名称</th>
                <th>公司地址</th>
                <th>创建日期</th>
                <th>创建人</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page.content}" var="item">
            <tr>
                <td class="center">
                    <label>
                        <input type="checkbox" class="ace" name="id" value="${item.id}"/>
                        <span class="lbl"></span>
                    </label>
                </td>
                <td>${item.name}</td>
                <td>${item.address}</td>
                <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.createUser.username}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="row row_height_x message-footer">
            <tags:page page="${page}"/>
        </div>
    </div><!-- /.table-responsive -->
</div>
</body>
</html>
