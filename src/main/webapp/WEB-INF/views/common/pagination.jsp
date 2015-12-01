<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_size" value="20"/>
<c:set var="current_page" value="${param.page != null ? param.page : 1 }"/>
<input type="hidden" name="max" value="${param.max != null ? param.max : page_size}" />
<input type="hidden" name="page" value="${current_page}"/>
<nav class="pagination">
<c:choose>
    <c:when test="${current_page > 1}">
        <button type="button" class="button" onclick="form.page.value = ${current_page - 1}; form.submit()">Previous</button>
    </c:when>
    <c:otherwise>
        <button type="button" class="button" disabled>Previous</button>
    </c:otherwise>
</c:choose>
<c:forEach begin="0" end="${current_page - 1}" varStatus="p">
    <c:choose>
        <c:when test="${ (p.index + 1) == current_page}">
            <button type="button" class="button active">${p.index + 1}</button>
        </c:when>
        <c:otherwise>
            <button type="button" class="button" onclick="form.page.value = ${p.index + 1}; form.submit()">${p.index + 1}</button>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:choose>
    <c:when test="${models.size() == (param.max != null ? param.max : page_size)}">
        <button type="button" class="button" onclick="form.page.value = ${current_page + 1}; form.submit();">Next</button>
    </c:when>
    <c:otherwise>
        <button type="button" class="button" disabled>Next</button>
    </c:otherwise>
</c:choose>
</nav>