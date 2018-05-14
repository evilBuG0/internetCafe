<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<form action="${ctx}/reset_password.do" method="post" onsubmit="return validate(this)">
    <input type="hidden" name="id" value="${userInfo.id}">
    <div class="clearfix">
        <div class="form_g_li">
            <label class="col_left_li"> 用户名 </label>
            <div class="col_right_li">
                <input type="text" class="inp" name="login_name" readonly value="${userInfo.username}"/>
            </div>
        </div>

        <div class="form_g_li">
            <label class="col_left_li"> &nbsp; </label>
            <div class="col_right_li">
                &nbsp;
            </div>
        </div>

        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>新密码 </label>
            <div class="col_right_li">
                <input type="password" id="passwordId" class="inp required password" minlength="6" name="password"/>
            </div>
        </div>

        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>确认新密码 </label>
            <div class="col_right_li">
                <input type="password" class="inp required password" minlength="6" confirmPassword="passwordId" name="confirmPassword"/>
            </div>
        </div>

    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="submit">确定</button>
    </div>
</form>
