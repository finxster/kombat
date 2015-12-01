<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/games/">Games</a></h1>
      <a class="button blue" href="/games/new">New</a>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${models != null}">
            <form action="/games" method="GET">
            <table>
                <thead>
                    <tr>
                        <th><input type="number" name="id" value="${param.id}" placeholder="ID"/></th>
                        <th><input type="text" name="slug" value="${param.slug}" placeholder="Slug"/></th>
                        <th><input type="text" name="name" value="${param.name}" placeholder="Name"/></th>
                        <th><input type="datetime-local" name="updated" value="${param.updated}" placeholder="Updated"/></th>
                        <th>
                            <button type="submit" class="button blue">Filter</button>
                            <button type="reset" class="button">Reset</button>
                        </th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>Slug</th>
                        <th>Name</th>
                        <th>Last Update</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="game" items="${models}">
                    <tr>
                        <td>${game.id}</td>
                        <td>${game.slug}</td>
                        <td>${game.name}</td>
                        <td><fmt:formatDate value="${(game.updated == null ? game.created : game.updated)}" pattern="MM/dd/yyyy hh:mm aaa"/></td>
                        <td><a href="/games/${game.id}" class="button">Edit</a> <a href="/games/${game.id}" class="button red ajax-delete">Remove</a></td>
                    </tr>
                </c:forEach>
                <tfoot>
                    <tr>
                        <td colspan="10">
                            <jsp:include page="../common/pagination.jsp" />
                        </td>
                    </tr>
                </tfoot>
            </table>
            </form>
        </c:if>
    </jsp:body>

</t:page>
