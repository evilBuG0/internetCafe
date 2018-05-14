<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
<head>
<title>考勤登记</title>
<link rel="stylesheet" href="${ctxRoot}/static/css/jquery-ui.min.css">
<script src="${ctxRoot}/static/js/jquery-ui-1.10.3.min.js"></script>
<script src="${ctxRoot}/static/js/base64.js"></script>
<script src="${ctxRoot}/static/js/scriptLib.js"></script>
<link rel="stylesheet"
	href="${ctxRoot}/static/framework/selectator/fm.selectator.jquery.css"
	type="text/css">
<script
	src="${ctxRoot}/static/framework/selectator/fm.selectator.jquery.js"></script>
<script>
	
</script>
<script type="text/javascript">
	function getClasssByGrade() {
		var gradeId = $("#grade option:selected").val();
		$("#classs").empty();

		$("#classs").append("<option value=''>---请选择班级---</option>");
		if (gradeId != "") {
			$.post("${ctx}/get_classs_by_grade.do", "gradeId=" + gradeId,
					function(data) {
						for ( var i = 0; i < data.length; i++) {
							$("#classs").append(
									"<option value='"+data[i]["id"]+"'>"
											+ data[i]["classsName"]
											+ " </option>");
						}
					}, "json");
		}
	}
	function getarrivalsNumberByClasss() {
		var classsId = $("#classs option:selected").val();

		if (classsId != "") {
			$.post("${ctx}/get_actualNumber_by_classs.do", "classsId="
					+ classsId, function(data) {
				$("#arrivalsNumber").val(data);
			}, "json");
		}
	}
	function checkNumber() {
		//应到人数
		var arrivalsNumber = $("#arrivalsNumber").val();
		//实到人数
		var actualNumber = $("#actualNumber").val();
		if (isNaN(actualNumber)) {
			alert("请输入数字！");
			$("#actualNumber").val("");
			return false;
		}
		if (parseInt(actualNumber) > parseInt(arrivalsNumber)) {
			alert("实到人数不可大于应到人数！");
			$("#actualNumber").val("");
			$("#addBut").attr('disabled',true);  
			return false;
		}
		if (parseInt(actualNumber) < parseInt(arrivalsNumber)) {
			$("#addBut").attr('disabled',false);  
		}
		if (parseInt(actualNumber) == parseInt(arrivalsNumber)) {
			$("#addBut").attr('disabled',true);  
		}
	}

	function showTable() {
		var classsId = $("#classs option:selected").val();
		var serialNum=$("#serialNum").val();
		win.init({
			title : '添加缺勤人员',
			rel : ''
		}).show('${ctx}/add_absence_pop.do?classsId=' + classsId+'&serialNum='+serialNum, {});

	}
	function saveSingInTemp(){
		 var formParam = $("#absenceForm").serialize();//序列化表格内容为字符串  
		    $.ajax({  
		        type:"post",      
		        url:"${ctx}/save_signIn_temp.do",  
		        data:formParam,  
		        cache:false,  
		        dataType:"json",  
		        success:function(data){
		        	var htmlText="<tr><td>"+data.student.studentName+"</td><td>"+data.signState+"</td><td>"+data.explainInfo+"</td></tr>";
		        	$("#signInTempTable").append(htmlText);
		        	win.close();
		        }  
		    });  
	}
	
	function signInAccept(obj){
		$form = $("#acceptFrom");
	    if (!$form.valid($form)) {
	        return false;
	    }
	    
	    $.ajax({
	        type: "POST",
	        url:"${ctx}/save_signIn.do",
	        data:$form.serializeArray(),
	        dataType:"json",
	        cache: false,
	        success: function(data){
	        	$("#acceptFrom")[0].reset();
	        	$("#addBut").attr('disabled',true);
	        	$("#signInTempTbody").remove();
	        	alert("考勤登记成功！");
	        },
	        error: function(){
	        	alert("受理失败，请联系管理员！");
	        }
	    });
	    return false;
	}
</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="${ctxRoot}">首页</a>
			</li>
			<li>考勤管理</li>
			<li class="active">考勤登记</li>
		</ul>
	</div>
	<div class="page-content">

		<form method="post" id="acceptFrom" onsubmit="return signInAccept(this)">
			<div class="row_height_s"
				style="height:99%;border:0px;background: white;">
				<div class="form-group_li">
					<label class="col_left_li"> <span>*</span>实训室： </label>
					<div class="col_right_li">
						<select class="inp required" name="computerRoom" id="computerRoom">
							<option value="">---请选择实训室---</option>
							<c:forEach var="item" items="${cprList }">
								<option value="${item.id }">${item.computerRoomName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				</br> </br> </br>
				<div class="form-group_li">
					<label class="col_left_li"> <span>*</span>年级： </label>
					<div class="col_right_li">
						<select class="inp required" name="grade" id="grade"
							onchange="getClasssByGrade()">
							<option value="">---请选择年级---</option>
							<c:forEach var="item" items="${gradeList }">
								<option value="${item.id }">${item.gradeName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				</br> </br> </br>
				<div class="form-group_li">
					<label class="col_left_li"> <span>*</span>班级： </label>
					<div class="col_right_li">
						<select class="inp required" name="classs" id="classs"
							onchange="getarrivalsNumberByClasss()">
							<option value="">---请选择班级---</option>

						</select>
					</div>
				</div>
				</br> </br> </br>
				<div class="form-group_li">
					<label class="col_left_li"> <span>*</span>课次： </label>
					<div class="col_right_li">
						<select class="inp required" name="lessons" id="lessons"
							onchange="getarrivalsNumberByClasss()">
							<option value="">---请选择课次---</option>
							<c:forEach var="item" items="${lessonsList }">
								<option value="${item.id }">${item.lessonsName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				</br> </br> </br>
				<div class="form-group_li">
					<label class="col_left_li"> 应到人数： </label>
					<div class="col_right_li">
						<input type="text" class="inp" name="arrivalsNumber"
							id="arrivalsNumber" readonly="readonly" />
					</div>
				</div>

				</br> </br> </br>
				<div class="form-group_li">
					<label class="col_left_li"> <span>*</span>实到人数： </label>
					<div class="col_right_li">
						<input type="text" class="inp required" name="actualNumber"
							id="actualNumber" onblur="checkNumber()" />
							<input type="text" hidden value="${num }" id="serialNum" name="serialNum"/>
					</div>
				</div>
				<br/><br/>
				<div class="form-group_li">
					<label class="col_left_li"> </label>
					<div id="absenceModel" class="col_right_li">
						<button id="addBut" class="btn btn-minier btn-danger" disabled="disabled" onClick="showTable()"
							type="button">添加缺勤人员</button>
					</div>
				</div>
				<br/><br/>
				<div class="form-group_li">
				<label class="col_left_li"> </label>
					<div class="col_right_li">
						<table
							class="table table-striped table-bordered table-hover dataTable" id="signInTempTable" style="width: 600px">
							<thead>
								<tr>
									<th width="100px">学生姓名</th>
									<th width="100px">缺勤原因</th>
									<th width="300px">说明</th>
								</tr>
							</thead>
							<tbody id="signInTempTbody">
								
							</tbody>
						</table>
					</div>
				</div>
				</br> </br> </br> <br/>
				<div class="form-group_li">
					<label class="col_left_li"> 备注： </label>
					<div class="col_right_li">
						<textarea cols="75" rows="4" id="remake"
							name="remake" style="resize: none;"></textarea>
						<br /> <br />
						<button class="btn btn-primary"
							style="margin-right:50px;padding:2px 20px;" type="submit">受理</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
