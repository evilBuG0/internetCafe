<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色管理</title>
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
            角色管理
        </li>
    </ul>
</div>
<div class="page-content">
    <form action="${ctx}/role_manage.do" method="post" onsubmit="return search(this);">
        <div class="row_height_s">
            <div class="form-group_li">
                <input type="text"  placeholder="角色名称" class="col_d" value="${param.Q_LIKE_roleName}" name="Q_LIKE_roleName"/>
            </div>
            <div class="form-group_li">
                <div class="col_right_li">
                    <select name="Q_EQ_type.id">
                        <option value="">---请选择角色类型---</option>
                        <c:forEach var="item" items="${parameterList}">
                            <option value="${item.id}" <c:if test="${item.id==param['Q_EQ_type.id']}">selected</c:if>>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="btn btn-primary btn-sm" type="submit">搜索
                <i class="icon-search icon-on-right bigger-110"></i>
            </button>
        </div>
    </form>
    <div class="row row_height_y">
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-primary"
                        onClick="win.init({title:'添加角色',rel:''}).show('${ctx}/edit_role_pop.do',{})">新增
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-info"
                        onClick="win.init({title:'修改角色'}).show('${ctx}/edit_role_pop.do?id={id}',{})">修改
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-danger"
                        onClick="win.init({title:'确定要删除吗？'}).confirm('${ctx}/delete_role.do?id={id}')">删除
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-success"
                        onClick="win.init({title:'授权'}).show('${ctx}/authority_role_pop.do?id={id}',{})">授权
                </button>
            </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover dataTable">
            <thead>
            <tr>
                <th width="1%" class="center">
                    <label>
                        <input type="checkbox" class="ace" onclick="checkAll(this);">
                        <span class="lbl"></span>
                    </label>
                </th>
                <th width="12%">角色名称</th>
                <th width="11%">角色类型</th>
                <th width="11%">描述</th>
                <th width="11%">创建人</th>
                <th width="11%">创建时间</th>
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
                    <td>${item.roleName}</td>
                    <td>${item.type.name}</td>
                    <td>${item.description}</td>
                    <td>${item.createUser.username}</td>
                    <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
