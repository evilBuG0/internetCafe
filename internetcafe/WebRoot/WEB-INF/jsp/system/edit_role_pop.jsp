<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<form action="${ctx}/save_role.do" method="post" onsubmit="return validate(this)">
    <input type="hidden" name="id" value="${roleInfo.id}">
    <div class="clearfix">
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>角色名称</label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${roleInfo.id != null}">
                        <input type="text" class="inp" name="role_name" readonly value="${roleInfo.roleName}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="inp required" name="role_name"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>角色类型</label>
            <div class="col_right_li">
                <select name="type">
                    <c:forEach var="item" items="${parameterList}">
                        <option value="${item.id}" <c:if test="${item.id==roleInfo.type.id}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li">描述</label>
            <div class="col_right_li">
                <input type="text" class="inp" name="description" value="${roleInfo.description}"/>
            </div>
        </div>
    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="submit">确定</button>
    </div>
</form>