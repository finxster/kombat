<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="user" scope="request" value="${current}"/>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/users/">Users</a> / ${user.slug}</h1>
    </jsp:attribute>

    <jsp:body>
        <jsp:include page="form.jsp">
            <jsp:param name="action" value="/users/${user.id}"/>
            <jsp:param name="method_override" value="PUT"/>
            <jsp:param name="button_name" value="Save"/>
        </jsp:include>
    </jsp:body>

</t:page>