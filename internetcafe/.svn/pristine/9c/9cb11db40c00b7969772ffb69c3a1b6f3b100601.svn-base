<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>修改密码</title>
    <script>
        function modifyPwd(){
            var $obj = $("#pwdForm");
            if(check()){
                $.post($obj.attr("action"),$obj.serialize(),function(data){
                    if(data['data'].type=="success"){
                        alert(data['data'].message);
                        window.location.href="${ctxRoot}/logout.do";
                    }else{
                        alert(data['data'].message);
                    }
                });
            }
        }

        function check(){
            var $obj = $("#pwdForm");
            var oldPwd = $obj.find("input[name='oldPwd']").val();
            var newPwd = $obj.find("input[name='newPwd']").val();
            var newSure = $obj.find("input[name='newSure']").val();
            if(oldPwd==""){
                alert("请输入原密码！");
                return false;
            }
            if(newPwd==""){
                alert("请输入新密码！");
                return false;
            }
            if(newSure==""){
                alert("请确认新密码！");
                return false;
            }
            if(newPwd!=newSure){
                alert("两次输入新密码不一致！");
                return false;
            }
            if (!/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/.test(newPwd)) {
                alert("密码需包含数字、字母和特殊字符！不能有中文！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="${ctxRoot}">首页</a>
        </li>
        <li>
            用户菜单
        </li>
        <li class="active">
            修改密码
        </li>
    </ul>
</div>
<div class="page-content">
<form action="${ctx}/modify_pwd.do" id="pwdForm">
    <div class="row_height_s" style="height:99%;border:0px;background: white;">
        <div class="form-group_li">
            <label class="col_left_li"> <span>*</span>用户名 </label>
            <div class="col_right_li">
                <input type="text" class="inp" name="userName" readonly value="${userName}"/>
            </div>
        </div>
        </br></br>
        <div class="form-group_li">
            <label class="col_left_li"> <span>*</span>旧密码 </label>
            <div class="col_right_li">
                <input type="password" class="inp required" name="oldPwd" value=""/>
            </div>
        </div>
        </br></br>
        <div class="form-group_li">
            <label class="col_left_li"> <span>*</span>新密码 </label>
            <div class="col_right_li">
                <input type="password" class="inp required" name="newPwd"/>
            </div>
        </div>
        </br></br>
        <div class="form-group_li">
            <label class="col_left_li"> <span>*</span>确认新密码 </label>
            <div class="col_right_li">
                <input type="password" class="inp required" name="newSure"/>
            </div>
        </div>
    </br></br></br>
        <div style="padding-left:160px;">
            <div>
                <button class="btn btn-primary" style="margin-right:50px;padding:2px 20px;" type="button" onclick="modifyPwd();">确定</button>
            </div>
        </div>
    </div>
</form>
</div>
</body>
</html>