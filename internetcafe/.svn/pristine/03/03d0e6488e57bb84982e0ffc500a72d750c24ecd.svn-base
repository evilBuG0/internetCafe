<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<div class="breadcrumbs">
    <ul class="breadcrumb h_chinese">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="${ctxRoot}">首页</a>
        </li>
        <li>日志管理</li>
        <li class="active">登录日志</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <!-- /.page-header -->
    <form action="${ctx}/query_login_log.do" method="post" onsubmit="return search(this);">
        <div class="row_height_s">
            <div class="form-group_li">
                <input type="text" class="col_d Wdate" data-date-format="yyyy-mm-dd" placeholder="开始时间" name="Q_startTime" value="${param.Q_startTime}" id="Q_startTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'Q_endTime\')}'})" />
            </div>
            <div class="form-group_li">
                <input type="text" class="col_d Wdate" data-date-format="yyyy-mm-dd" placeholder="结束时间" name="Q_endTime" value="${param.Q_endTime}" id="Q_endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'Q_startTime\')}'})" />
            </div>
            <button class="btn btn-primary btn-sm" type="submit">
                搜索
                <i class="icon-search icon-on-right bigger-110"></i>
            </button>
        </div>
    </form>

    <div class="table-responsive td_o" id="listId">
        <table class="table table-striped table-bordered table-hover dataTable">
            <thead>
            <tr>
                <th width="50" class="center">
                    <label>
                        <input type="checkbox" class="ace"  onclick="checkAll(this)" />
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>帐号</th>
                <th>内容</th>
                <th width="120">IP</th>
                <th width="150">产生时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="item">
                <tr>
                    <td class="center">
                        <label>
                            <input type="checkbox" class="ace" name="id" value="${item.id}" />
                            <span class="lbl"></span>
                        </label>
                    </td>
                    <th>${item.createUser}</th>
                    <td>${item.content}</td>
                    <td>${item.createIp}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- /.table-responsive -->
    <div class="row row_height_x message-footer">
        <tags:page page="${page}"/>
    </div>
</div>