<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_size" value="1"/>
<c:set var="current_page" value="${param.page != null ? param.page : 1 }"/>
<input type="hidden" name="page" value="${current_page}"/>
<nav class="pagination">
    <select name="max" onchange="form.page.value = 1; form.submit();">
         <option value="1" ${param.max == 1 ? 'selected' : ''}>1</option>
         <option value="20" ${param.max == 20 ? 'selected' : ''}>20</option>
         <option value="30" ${param.max == 30 ? 'selected' : ''}>30</option>
         <option value="40" ${param.max == 40 ? 'selected' : ''}>40</option>
    </select>
    <button type="button" class="button" onclick="form.page.value = ${current_page - 1}; form.submit()" ${current_page == 1 ? 'disabled' : ''}>Previous</button>
    <c:if test="${current_page > 2}">
        <button type="button" class="button" onclick="form.page.value = 1; form.submit()">1</button>
        <span class="button static">...</span>
    </c:if>
    <c:forEach begin="${current_page > 2 ? current_page - 1 : 0}" end="${current_page - 1}" varStatus="p">
        <button type="button" class="button ${ (p.index + 1) == current_page ? 'active' : ''}" onclick="form.page.value = ${p.index + 1}; form.submit()">${p.index + 1}</button>
    </c:forEach>
    <button type="button" class="button" onclick="form.page.value = ${current_page + 1}; form.submit();" ${models.size() < (param.max != null ? param.max : page_size) ? 'disabled' : ''}>Next</button>
</nav>