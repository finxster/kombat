<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="game" scope="request" value="${model}"/>
<t:page>
     <jsp:attribute name="header">
      <h1><a href="/games/">Games</a> / create</h1>
    </jsp:attribute>

    <jsp:body>
        <jsp:include page="form.jsp">
            <jsp:param name="action" value="/games/"/>
            <jsp:param name="button_name" value="Create"/>
        </jsp:include>
    </jsp:body>

</t:page>