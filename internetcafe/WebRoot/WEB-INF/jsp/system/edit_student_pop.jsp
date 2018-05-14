<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<form action="${ctx}/save_student.do" method="post"
	onsubmit="return validate(this)">
	<input type="hidden" name="id" value="${studentInfo.id}">
	<div class="clearfix">
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>姓名 </label>
			<div class="col_right_li">
				<c:choose>
					<c:when test="${studentInfo.id!=null}">
						<input type="text" class="inp" name="studentName" readonly
							value="${studentInfo.studentName}" />
					</c:when>
					<c:otherwise>
						<input type="text" class="inp required" name="studentName" value="${studentInfo.studentName }"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>年龄 </label>
			<div class="col_right_li">
				<input type="text" class="inp required" name="age" value="${studentInfo.age }"/>
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span></span>性别 </label>
			<div class="col_right_li">
				<select id="sex" name="sex">
					<option value="">请选择</option>
					<option value="0" <c:if test="${0==studentInfo.sex}">selected</c:if>>男</option>
					<option value="1" <c:if test="${1==studentInfo.sex}">selected</c:if>>女</option>
				</select>
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span></span>家庭住址 </label>
			<div class="col_right_li">
				<input type="text" class="inp" name="address" value="${studentInfo.address }" />
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>所属年级 </label>
			<div class="col_right_li">
				<select id="gradeId" name="gradeId">
				<option value="">请选择</option>
					<c:forEach var="row" items="${gradeList}">
						<option value="${row.id}"
							<c:if test="${row.id==studentInfo.grade.id}">selected</c:if>>${row.gradeName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>所属班级 </label>
			<div class="col_right_li">
				<select id="classsId" name="classsId">
				<option value="">请选择</option>
					<c:forEach var="row" items="${classsList}">
						<option value="${row.id}"
							<c:if test="${row.id==studentInfo.classsId.id}">selected</c:if>>${row.classsName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	
	</div>
	<div class="ui_t_foot">
		<button class="btn" type="button" onclick="win.close();">取消</button>
		<button class="btn btn-primary" type="submit">确定</button>
	</div>
</form>