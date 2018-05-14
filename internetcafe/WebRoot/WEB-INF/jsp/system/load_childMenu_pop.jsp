<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>

<form action="" method="post" >
    <div class="clearfix">
        <c:forEach items="${childMenus}" var="item">
            <div class="form_g_li">
                <label class="col_left_li"> </label>
                <div class="col_right_li">
                        ${item.menuName}
                </div>
            </div>
        </c:forEach>

    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">关闭</button>

    </div>
</form>