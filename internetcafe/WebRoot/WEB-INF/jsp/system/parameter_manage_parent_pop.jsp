<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="unit">
    <div class="widget-header header-color-blue2">
        <h5 class="lighter smaller">类别管理</h5>
    </div>
    <div class="row row_height_y">
        <div class="pull-left">
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-primary"
                        onClick="win.init({title:'添加类别',rel:'parentId'}).show('${ctx}/edit_parameter_pop.do?rel=parentFormId&parentId=${param['Q_EQ_parent.id']}')">新增
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-info"
                        onClick="win.init({title:'编辑类别',rel:'parentId'}).show('${ctx}/edit_parameter_pop.do?rel=parentFormId&parentId=${param['Q_EQ_parent.id']}&id={id}')">修改
                </button>
            </div>
            <div class="ui-pg-div icon_left">
                <button class="btn btn-minier btn-danger"
                        onClick="win.init({title:'确定要删除吗？',rel:'parentId'}).confirm('${ctx}/delete_parameter_pop.do?id={id}')">删除</button>
            </div>
        </div>
        <div class="pull-right1">
            <form id="parentFormId" rel="parentId" action="${ctx}/parameter_manage_parent_pop.do" onsubmit="return search(this)">
                <input hidden="hidden" name="Q_EQ_parent.id" value="${param['Q_EQ_parent.id']}" />
                <input type="text" placeholder="名称" name="Q_LIKE_name" value="${param.Q_LIKE_name}"/>
                <span class="input-group-btn">
                    <button class="btn btn-info btn-minier" type="submit">
                        <i class="icon-search icon-on-right bigger-110"></i>
                    </button>
                </span>
            </form>
        </div>
    </div>
    <div class="table-responsive td_o table-responsive1" id="parentId">
        <table class="table table-striped table-bordered table-hover dataTable">
            <thead>
            <tr>
                <th width="1%" class="center">
                    <label>
                        <input type="checkbox" class="ace" id="checkAllId" onclick="checkAll(this, 'parentId');">
                        <span class="lbl"></span>
                    </label>
                </th>
                <th width="15%">编号</th>
                <th width="19%">名称</th>
                <c:if test="${not empty param['Q_EQ_parent.id'] && 0 != param['Q_EQ_parent.id']}">
                    <th width="18%">类型</th>
                </c:if>
                <th width="15%">描述</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page_parent.content}" var="item">
            <tr onclick="loadUrl('childrenId', '${ctx}/parameter_manage_child_pop.do?Q_EQ_parent.id=${item.id}')">
                <td class="center">
                    <label>
                        <input type="checkbox" class="ace" name="id" value="${item.id}"/>
                        <span class="lbl"></span>
                    </label>
                </td>
                <td>${item.code}</td>
                <td>${item.name}</td>
                <c:if test="${not empty param['Q_EQ_parent.id'] && 0 != param['Q_EQ_parent.id']}">
                    <td>${item.type}</td>
                </c:if>
                <td>${item.remark}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- /.table-responsive -->
    <div class="row row_height_x message-footer">
        <tags:page page="${page_parent}"/>
    </div>
</div>