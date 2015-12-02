<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="game_path" scope="request" value="/games/${game.id}" />
<c:set var="base_path" scope="request" value="${game_path}/characters" />
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/games">Games</a> / <a href="${game_path}">${game.name}</a> / <a href="${base_path}/">Characters</a></h1>
      <a class="button blue" href="${base_path}/new">New</a>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${models != null}">
            <form action="${base_path}" method="GET">
            <table class="table">
                <thead>
                    <tr>
                        <th class="picture-col"></th>
                        <th><input type="number" name="id" value="${param.id}" placeholder="ID"/></th>
                        <th><input type="text" name="name" value="${param.name}" placeholder="Name"/></th>
                        <th><input type="text" name="game" value="${param.game}" placeholder="Game"/></th>
                        <th><input type="datetime-local" name="updated" value="${param.updated}" placeholder="Updated"/></th>
                        <th>
                            <button type="submit" class="button blue">Filter</button>
                            <button type="reset" class="button">Reset</button>
                        </th>
                    </tr>
                    <tr>
                        <th></th>
                        <th class="text-left">ID</th>
                        <th>Name</th>
                        <th>Game</th>
                        <th>Last Update</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="character" items="${models}">
                    <tr>
                        <td><c:if test="${character.picture != null}"><img src="/uploads${character.picture}"/></c:if></td>
                        <td class="text-left">${character.id}</td>
                        <td>${character.name}</td>
                        <td>${character.game.name}</td>
                        <td><fmt:formatDate value="${(character.updated == null ? character.created : character.updated)}" pattern="MM/dd/yyyy hh:mm aaa"/></td>
                        <td><a href="${base_path}/${character.id}" class="button">Edit</a> <a href="${base_path}/${character.id}" class="button red form-delete">Remove</a></td>
                    </tr>
                </c:forEach>
                <tfoot>
                    <tr>
                        <td colspan="10">
                            <jsp:include page="../../common/pagination.jsp" />
                        </td>
                    </tr>
                </tfoot>
            </table>
            </form>
        </c:if>
    </jsp:body>

</t:page>