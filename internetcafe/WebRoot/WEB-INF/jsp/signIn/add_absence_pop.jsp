<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<form id="absenceForm" method="post"
	onsubmit="return validate(this)">
	<input type="hidden" name="serialNum" value="${serialNum}">
	<div class="clearfix">
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>学生姓名 </label>
			<div class="col_right_li">
				<select class="inp required" name="stuId" id="stuId">
					<option value="">---请选择缺勤学生---</option>
					<c:forEach var="item" items="${studentList }">
						<option value="${item.id }">${item.studentName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form_g_li">
			<label class="col_left_li"> <span>*</span>缺勤原因 </label>
			<div class="col_right_li">
				<select class="inp required" name="signState" id="signState">
					<option value="">---请选择缺勤原因---</option>
					<option value="迟到">迟到</option>
					<option value="早退">早退</option>
					<option value="旷课">旷课</option>
					<option value="请假">请假</option>
				</select>
			</div>
		</div>
		<div class="form-group_li">
			<label class="col_left_li"> 说明： </label>
			<div class="col_right_li">
				<textarea cols="78" rows="4" id="explain"
					name="explain" style="resize: none;"></textarea>
			</div>
		</div>
		
	</div>
	<div class="ui_t_foot">
		<button class="btn" type="button" onclick="win.close();">取消</button>
		<button class="btn btn-primary" type="submit" onclick="saveSingInTemp()">确定</button>
	</div>
</form>