<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="game" scope="request" value="${current}"/>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/games/">Games</a> / ${game.slug}</h1>
    </jsp:attribute>

    <jsp:body>
        <jsp:include page="form.jsp">
            <jsp:param name="action" value="/games/${game.id}"/>
            <jsp:param name="method_override" value="PUT"/>
            <jsp:param name="button_name" value="Save"/>
        </jsp:include>
    </jsp:body>

</t:page>