<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/games/">Games</a></h1>
      <a class="button blue" href="/games/new">New</a>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${games != null}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Slug</th>
                        <th>Name</th>
                        <th>Last Update</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td>${game.id}</td>
                        <td>${game.slug}</td>
                        <td>${game.name}</td>
                        <td>${game.updated == null ? game.created : game.updated}</td>
                        <td><a href="/games/${game.id}" class="button">Edit</a> <form class="inline" action="/games/${game.id}" method="POST"><input type="hidden" name="_method" value="DELETE" /><button type="submit" class="button red">Remove</button></form></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </jsp:body>

</t:page>
