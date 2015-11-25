<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>

    <jsp:attribute name="header">
      <h1><a href="/users/">Users</a></h1>
      <a class="button blue" href="/users/new">New</a>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${users != null}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Slug</th>
                        <th>Email</th>
                        <th>Nome</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.slug}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td><a href="/users/${user.id}" class="button">Edit</a> <form class="inline" action="/users/${user.id}" method="POST"><input type="hidden" name="_method" value="DELETE" /><button type="submit" class="button red">Remove</button></form></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </jsp:body>

</t:page>
