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
            <form id="filter-form" action="/games" method="GET">
                <input type="hidden" name="order" value="${param.order}" />
                <input type="hidden" name="asc" value="${param.asc}" />
                <table class="table">
                    <thead>
                        <tr>
                            <th class="picture-col"></th>
                            <th><input type="number" name="id" value="${param.id}" placeholder="ID"/></th>
                            <th><input type="text" name="slug" value="${param.slug}" placeholder="Slug"/></th>
                            <th><input type="text" name="name" value="${param.name}" placeholder="Name"/></th>
                            <th><input type="datetime-local" name="updated" value="${param.updated}" placeholder="Updated"/></th>
                            <th colspan="2">
                                <button type="submit" class="button blue">Filter</button>
                                <button type="reset" class="button">Reset</button>
                            </th>
                        </tr>
                        <tr>
                            <th></th>
                            <th class="text-left"><span class="order-field ${ param.order == 'id' ? ( param.asc == false ? 'desc' : '' ) : ''}" rel="id">ID</span></th>
                            <th><span class="order-field ${ param.order == 'slug' ? ( param.asc == false ? 'desc' : '' ) : ''}" rel="slug">Slug</span></th>
                            <th><span class="order-field ${ param.order == 'name' ? ( param.asc == false ? 'desc' : '' ) : ''}" rel="name">Name</span></th>
                            <th><span class="order-field ${ param.order == 'updated' ? ( param.asc == false ? 'desc' : '' ) : ''}" rel="updated">Last Update</span></th>
                            <th>Characters</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach var="game" items="${models}">
                        <tr>
                            <td><c:if test="${game.picture != null}"><figure><img src="/uploads${game.picture}"/></figure></c:if></td>
                            <td class="text-left">${game.id}</td>
                            <td>${game.slug}</td>
                            <td>${game.name}</td>
                            <td><fmt:formatDate value="${(game.updated == null ? game.created : game.updated)}" pattern="MM/dd/yyyy hh:mm aaa"/></td>
                            <td><a href="/games/${game.id}/characters" class="button blue">Characters</a></td>
                            <td><a href="/games/${game.id}" class="button">Edit</a> <a href="/games/${game.id}" class="button red form-delete">Remove</a></td>
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
