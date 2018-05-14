<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>菜单管理</title>
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
            菜单管理
        </li>
    </ul>
</div>
<div class="page-content">
    <form action="${ctx}/menu_manage.do" method="post" onsubmit="return search(this);">
        <div class="row_height_s">
            <div class="form-group_li">
                <input type="text"  placeholder="菜单名称" class="col_d" value="${param.Q_LIKE_title}" name="Q_LIKE_title"/>
            </div>
            <div class="form-group_li">
                <div class="col_right_li">
                    <select name="Q_EQ_type">
                        <option value="">---请选择类型---</option>
                        <option value="1" <c:if test="${1==param['Q_EQ_type']}">selected</c:if>>菜单</option>
                        <option value="2" <c:if test="${2==param['Q_EQ_type']}">selected</c:if>>按钮</option>
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
                        onClick="win.init({title:'添加菜单',rel:''}).show('${ctx}/edit_menu_pop.do',{})">新增
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-info"
                        onClick="win.init({title:'修改菜单'}).show('${ctx}/edit_menu_pop.do?id={id}',{})">修改
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-danger"
                        onClick="win.init({title:'确定要删除吗？'}).confirm('${ctx}/delete_menu.do?id={id}')">删除
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-info"
                        onClick="win.init({title:'查看子菜单'}).show('${ctx}/load_childMenu_pop.do?id={id}',{})">查看子菜单
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
                <th width="12%">菜单名称</th>
                <th width="12%">上级菜单</th>
                <th width="11%">类型</th>
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
                    <td>${item.menuName}</td>
                    <td>${item.parentMenu.menuName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.type == 1}">
                                菜单
                            </c:when>
                            <c:otherwise>
                                按钮
                            </c:otherwise>
                        </c:choose>
                    </td>
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