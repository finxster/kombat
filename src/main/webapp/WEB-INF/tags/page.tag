<%@tag description="Master Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User Sample</title>
        <link rel="stylesheet" href="/assets/css/main.css" />
        <c:forEach var="style" items="${styles}">
            <link rel="stylesheet" href="${style}" />
        </c:forEach>
    </head>
    <body>
        <c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}"/>
        <header>
            <nav class="menu">
                <ul>
                    <li class="${uri.contains('users') ? 'active' : '' }"><a href="/users">Users</a></li>
                    <li class="${uri.contains('games') ? 'active' : '' }"><a href="/games">Games</a></li>
                </ul>
            </nav>
            <jsp:invoke fragment="header"/>
        </header>
        <section id="content">

            <c:if test="${notice != null}" >
                <p class="notice">${notice}</p>
            </c:if>

            <c:if test="${warning != null}" >
                <p class="warning">${warning}</p>
            </c:if>

            <c:if test="${error != null}" >
                <p class="error">${error}</p>
            </c:if>
            <jsp:doBody/>
        </section>
        <footer>
            <jsp:invoke fragment="footer"/>
            <p id="copyright">Copyright 2015, AvenueCode.</p>
            <script src="https://code.jquery.com/jquery-2.1.4.min.js" type="text/javascript"></script>
            <script src="/assets/js/main.js" type="text/javascript"></script>
            <c:forEach var="script" items="${scripts}">
              <script src="${script}" type="text/javascript"></script>
            </c:forEach>

            <form action="" id="delete-form" method="POST">
                <input type="hidden" name="_method" value="DELETE"/>
            </form>
        </footer>
    </body>
</html>