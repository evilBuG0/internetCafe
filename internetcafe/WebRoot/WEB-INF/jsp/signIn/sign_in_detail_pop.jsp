<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<style>
.col_right_li_detail {
	float: left;
	width: 220px;
	text-align: left;
	line-height: 30px;
	font-size: 1.2em;
}
</style>

</head>
<body style="background-color:#f8f8f8;">
	
	<div class="table-responsive">
		<table
			class="table table-striped table-bordered table-hover dataTable">
			<thead>
				<tr>
					<th>缺勤学生</th>
					<th>缺勤原因</th>
					<th>说明</th>
					<th>登记人</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${signInDetailList}" var="item">
					<tr>
						<td>${item.studentId.studentName}</td>
						<td>${item.signState}</td>
						<td>${item.explainInfo }</td>
						<td>${item.createUser.username }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>


