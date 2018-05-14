<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<form rel="${param.rel}" action="${ctx}/save_parameter.do" method="post" onsubmit="return validate(this)">    <div class="clearfix">
    <input type="hidden" name="parentId" value="${param.parentId}" />
    <input type="hidden" name="id" value="${parameterInfo.id}" />
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>编号 </label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${parameterInfo.id != null}">
                        <input type="text" class="inp" name="code" readonly value="${parameterInfo.code}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="inp required" name="code"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>名称</label>
            <div class="col_right_li">
                <input type="text" class="inp required" name="name" value="${parameterInfo.name}"/>
            </div>
        </div>
        <c:if test="${(not empty param.parentId) && 0 != param.parentId}">
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>类型</label>
            <div class="col_right_li">
                <input type="text" class="inp required" name="type" value="${parameterInfo.type}"/>
            </div>
        </div>
        </c:if>
        <div class="form_g_li form_g_li1">
            <label class="col_left_li"> 描述 </label>
            <div class="col_right_li">
                <textarea name="remark" cols="" rows="" class="inp_l">${parameterInfo.remark}</textarea>
            </div>
        </div>
    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="submit">确定</button>
    </div>
</form>

