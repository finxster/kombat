<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/characters/">Characters</a></h1>
      <a class="button blue" href="/characters/new">New</a>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${models != null}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Game</th>
                        <th>Last Update</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="character" items="${models}">
                    <tr>
                        <td>${character.id}</td>
                        <td>${character.name}</td>
                        <td>${character.game.name}</td>
                        <td>${character.updated == null ? character.created : character.updated}</td>
                        <td><a href="/characters/${character.id}" class="button">Edit</a> <form class="inline" action="/characters/${character.id}" method="POST"><input type="hidden" name="_method" value="DELETE" /><button type="submit" class="button red">Remove</button></form></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </jsp:body>

</t:page>
