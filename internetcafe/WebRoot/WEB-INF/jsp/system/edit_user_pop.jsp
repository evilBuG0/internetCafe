<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<form action="${ctx}/save_user.do" method="post" onsubmit="return validate(this)">
    <input type="hidden" name="id" value="${userInfo.id}">
    <div class="clearfix">
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>用户名 </label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${userInfo.id!=null}">
                        <input type="text" class="inp" name="username" readonly value="${userInfo.username}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="inp required" name="username"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>登录密码 </label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${userInfo.id!=null}">
                        <input type="password" class="inp" name="password" readonly value="${userInfo.password}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="inp required password" minlength="6" name="password"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>角色 </label>
            <div class="col_right_li">
                <select id="roleId" name="roleId">
                    <c:forEach var="row" items="${roleList}">
                        <option value="${row.id}" <c:if test="${row.id==userInfo.role.id}">selected</c:if>>${row.roleName}</option>
                    </c:forEach>
                </select>
            </div>
        </div><%--
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span> 公司 </label>
            <div class="col_right_li">
                <select name="companyId">
                    <c:forEach var="row" items="${companyList}">
                        <option value="${row.id}" <c:if test="${row.id==userInfo.company.id}">selected</c:if>>${row.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    --%></div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="submit">确定</button>
    </div>
</form>