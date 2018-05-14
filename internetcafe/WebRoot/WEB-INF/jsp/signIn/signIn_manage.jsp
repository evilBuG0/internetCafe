<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script>
	function viewRepeat(id){
	    $panel = $("#viewRepeatId");
	    $panel.empty();
	    $panel.load('sign_in_detail_pop.do', {'id': id}, function() { });
	    $("#viewRepeatId").dialog({
	        closeOnEscape: false,
	        resizable: true,
	        draggable: true,
	        height: 400,
	        width: 800,
	        modal: true,
	        buttons: {
	            "关闭": function () {
	                $(this).dialog("close");
	            }
	        }
	    });
	}
</script>
</head>
<body>
<div id="viewRepeatId" title="考勤详情" style="display: none;"></div>
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="${ctxRoot}">首页</a>
			</li>
			<li>考勤管理</li>
			<li class="active">考勤记录</li>
		</ul>
	</div>
	<div class="page-content">
		<form action="${ctx}/user_manage.do" method="post"
			onsubmit="return search(this);">
			<div class="row_height_s">

				<div class="form-group_li">
					<div class="col_right_li">
						<select name="Q_EQ_type">
							<option value="">---请选择实训室---</option>
						</select>
					</div>
				</div>
				<div class="form-group_li">
					<div class="col_right_li">
						<select name="Q_EQ_type">
							<option value="">---请选择年级---</option>
						</select>
					</div>
				</div>
				<div class="form-group_li">
					<div class="col_right_li">
						<select name="Q_EQ_type">
							<option value="">---请选择班级---</option>
						</select>
					</div>
				</div>
				<div class="form-group_li">
					<div class="col_right_li">
						<select name="Q_EQ_type">
							<option value="">---请选择课次---</option>
						</select>
					</div>
				</div>
				<div class="form-group_li">
					<div class="col_right_li">
						<select name="Q_EQ_type">
							<option value="">---请选择出勤状态---</option>
						</select>
					</div>
				</div>
				<button class="btn btn-primary btn-sm" type="submit">
					搜索 <i class="icon-search icon-on-right bigger-110"></i>
				</button>

			</div>
		</form>

		<div class="table-responsive">
			<table
				class="table table-striped table-bordered table-hover dataTable">
				<thead>
					<tr>
						<th class="center"><label> <input type="checkbox"
								class="ace" onclick="checkAll(this);"> <span class="lbl"></span>
						</label>
						</th>
						<th>实训室</th>
						<th>年级</th>
						<th>班级</th>
						<th>课次</th>
						<th>出勤状态</th>
						<th>登记日期</th>
						<th>登记人</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${page.content}" var="item">
						<tr>
							<td class="center"><label> <input type="checkbox"
									class="ace" name="id" value="${item.id}" /> <span class="lbl"></span>
							</label>
							</td>
							<td>${item.computerRoom.computerRoomName}</td>
							<td>${item.classsId.grade.gradeName}</td>
							<td>${item.classsId.classsName}</td>
							<td>${item.lessonsId.lessonsName}</td>
							<td>${item.signInState}</td>
							<td><fmt:formatDate value="${item.createDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${item.createUser.username}</td>
							<td>${item.remark }</td>
							<td><input title="查看考勤详情" style="width:22px;length:22px;"
								type='image' src="${ctxRoot}/static/images/search.png"
								onclick="viewRepeat(${item.id});" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row row_height_x message-footer">
				<tags:page page="${page}" />
			</div>
		</div>
		<!-- /.table-responsive -->
	</div>
</body>
</html>
