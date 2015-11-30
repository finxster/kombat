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
            <form action="/characters" method="GET">
            <table>
                <thead>
                    <tr>
                        <th><input type="number" name="id" value="${param.id}" placeholder="ID"/></th>
                        <th><input type="text" name="name" value="${param.name}" placeholder="Name"/></th>
                        <th><input type="text" name="game" value="${param.game}" placeholder="Game"/></th>
                        <th><input type="datetime-local" name="updated" value="${param.updated}" placeholder="Updated"/></th>
                        <th>
                            <input type="hidden" name="page" value="${param.page ? param.page : 1}" />
                            <button type="submit" class="button blue">Filter</button>
                            <button type="reset" class="button">Reset</button>
                        </th>
                    </tr>
                </thead>
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
                        <td><a href="/characters/${character.id}" class="button">Edit</a> <a href="/characters/${character.id}" class="button red ajax-delete">Remove</a></td>
                    </tr>
                </c:forEach>
            </table>
            </form>
        </c:if>
    </jsp:body>

</t:page>
