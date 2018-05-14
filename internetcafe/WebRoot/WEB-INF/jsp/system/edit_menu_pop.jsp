<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<script type="text/javascript">
    function selectMenu(){
        var type = $("#typeId option:selected").val();
        $("#parentMenuId").empty();
        if(type==1){
            $("#parentMenuId").append("<option value=''>-------请选择菜单-------</option>")
        }
        $.post("${ctx}/select_menu.do","type="+type,function(data){
            for(var i = 0 ; i < data.length ; i++){
                $("#parentMenuId").append("<option value='"+data[i]["id"]+"'>"+data[i]["menuName"]+"</option>");
            }
        },"json");
    }
</script>
<form action="${ctx}/save_menu.do" method="post" onsubmit="return validate(this)">
    <input type="hidden" name="id" value="${menuInfo.id}">
    <div class="clearfix">
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>菜单名称</label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${menuInfo.id != null}">
                        <input type="text" class="inp" name="menuName"  value="${menuInfo.menuName}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="inp required" name="menuName"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> 菜单URL </label>
            <div class="col_right_li">
                <input type="text" class="inp " name="url" value="${menuInfo.url}"/>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> <span>*</span>类型</label>
            <div class="col_right_li">
                <select name="type" id="typeId" onchange="selectMenu()">
                    <option value="1" <c:if test="${1==menuInfo.type}">selected</c:if>>菜单</option>
                    <option value="2" <c:if test="${2==menuInfo.type}">selected</c:if>>按钮</option>
                </select>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> 父菜单</label>
            <div class="col_right_li">
                <c:choose>
                    <c:when test="${menuInfo.id != null}">
                        <c:choose>
                            <c:when test="${menuInfo.type==1}">
                                <select name="parentId" id="parentMenuId">
                                    <c:forEach var="item" items="${parentMenus}">
                                        <option value="${item.id}" <c:if test="${item.id==menuInfo.parentMenu.id}">selected</c:if>>${item.menuName}</option>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="parentId" id="parentMenuId">
                                    <c:forEach var="item" items="${selectMenus}">
                                        <option value="${item.id}" <c:if test="${item.id==menuInfo.parentMenu.id}">selected</c:if>>${item.menuName}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                    <c:otherwise>
                        <select name="parentId" id="parentMenuId">
                            <option value="">-------请选择菜单-------</option>
                            <c:forEach var="item" items="${parentMenus}">
                                <option value="${item.id}" <c:if test="${item.id==menuInfo.parentMenu.id}">selected</c:if>>${item.menuName}</option>
                            </c:forEach>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="form_g_li">
            <label class="col_left_li"> 菜单样式 </label>
            <div class="col_right_li">
                <input type="text" class="inp " name="cssClass" value="${menuInfo.cssClass}"/>
            </div>
        </div>
        <%--<div class="form_g_li">
            <label class="col_left_li"> CODE </label>
            <div class="col_right_li">
                <input type="text" class="inp " name="code" value="${menuInfo.code}"/>
            </div>
        </div>--%>
    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="submit">确定</button>
    </div>
</form>