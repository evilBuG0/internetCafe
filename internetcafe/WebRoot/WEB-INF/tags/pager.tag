<%@ tag pageEncoding="UTF-8"%>
<%@ tag import="com.ideal.oms.dto.Pager" %>
<%@ attribute name="pager" type="com.ideal.oms.dto.Pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty pager &&not empty pager.rows}">
	<%
		int begin = pager.getBegin();
		int end = pager.getEnd();
		int current = pager.getCurrent();
	%>
    <div class="page">
                       共<span class="number">${pager.totalPage }</span>页
         <c:choose>
         	<c:when  test="${pager.totalPage<=1||pager.current==1}">
         		<a>上一页</a>
         	</c:when>
         	<c:otherwise>
         		<a href="javascript:page(${pager.current-1 });">上一页</a>
         	</c:otherwise>
         </c:choose>
         <%
         	for(int i = begin ; i<=end ; i++){
         		if(current==i){
         		%>
         			<a href="javascript:page(<%=i %>);" style="background:#e3e3e3"><%=i %></a>
         		<%
         		}else{
         		%>
         			<a href="javascript:page(<%=i %>);"><%=i %></a>
         		<%
         		}
         	}
         %>
         <c:choose>
         	<c:when  test="${pager.totalPage<=1||pager.current==pager.totalPage}">
         		<a>下一页</a>
         	</c:when>
         	<c:otherwise>
         		<a href="javascript:page(${pager.current+1 });">下一页</a>
         	</c:otherwise>
         </c:choose>
		  第 <input type="text" id="jump" value="${pager.current }"/> 页
        <a href="javascript:jump(${pager.current},${pager.totalPage});">GO</a>
    </div>
</c:if>