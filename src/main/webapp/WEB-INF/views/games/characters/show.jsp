<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="game_path" scope="request" value="/games/${game.id}" />
<c:set var="base_path" scope="request" value="${game_path}/characters" />
<c:set var="character" scope="request" value="${current}"/>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/games">Games</a> / <a href="${game_path}">${game.name}</a> / <a href="${base_path}/">Characters</a> / ${character.name}</h1>
    </jsp:attribute>

    <jsp:body>
        <jsp:include page="form.jsp">
            <jsp:param name="action" value="${base_path}/${character.id}"/>
            <jsp:param name="method_override" value="PUT"/>
            <jsp:param name="button_name" value="Save"/>
        </jsp:include>
    </jsp:body>

</t:page>