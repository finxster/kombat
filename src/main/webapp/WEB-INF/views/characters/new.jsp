<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="character" scope="request" value="${model}"/>
<t:page>
     <jsp:attribute name="header">
      <h1><a href="/characters/">Characters</a> / create</h1>
    </jsp:attribute>

    <jsp:body>
        <jsp:include page="form.jsp">
            <jsp:param name="action" value="/characters/"/>
            <jsp:param name="button_name" value="Create"/>
        </jsp:include>
    </jsp:body>

</t:page>