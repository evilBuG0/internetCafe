<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty page && page.totalElements!=0}">
    <%
        int current = page.getNumber() + 1;
        int paginationSize = page.getSize();
        int begin = Math.max(1, current - paginationSize / 2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
        int totalPages = page.getTotalPages();
        Long totalSizes = page.getTotalElements();
        request.setAttribute("current", current);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalSizes", totalSizes);
    %>

    <div class="pull-right">
        <ul class="pagination middle pull-left">
            <% if (page.hasPreviousPage()) {%>
            <li><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=1&sortType=${sortType}&type=${type}&${searchParams}"><span> <i class="icon-step-backward middle"></i></span></a></li>
            <li><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${current-1}&sortType=${sortType}&type=${type}&${searchParams}"><span><i class="icon-caret-left bigger-140 middle"></i></span></a></li>
            <%} else {%>
            <li class="disabled"><span> <i class="icon-step-backward middle"></i></span></li>
            <li class="disabled"><span><i class="icon-caret-left bigger-140 middle"></i></span></li>
            <%} %>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i == current}">
                        <li class="active"><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <% if (page.hasNextPage()) {%>
            <li><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${current+1}&sortType=${sortType}&type=${type}&${searchParams}"><i class="icon-caret-right bigger-140 middle"></i></a></li>
            <li><a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${page.totalPages}&sortType=${sortType}&type=${type}&${searchParams}"><i class="icon-step-forward middle"></i></a></li>
            <%} else {%>
            <li class="disabled"><span><i class="icon-caret-right bigger-140 middle"></i></span></li>
            <li class="disabled"><span><i class="icon-step-forward middle"></i></span></li>
            <%} %>
            <span class="pagination middle">&nbsp;&nbsp;&nbsp;${current} / ${totalPages}　共 ${totalSizes} 条记录</span>
        </ul>
    </div>
</c:if>