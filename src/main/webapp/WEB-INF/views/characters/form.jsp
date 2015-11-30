<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${param.action}" method="POST" enctype="multipart/form-data">
    <c:if test="${param.method_override != null}">
        <input type="hidden" name="_method" value="${param.method_override}"/>
    </c:if>

    <c:set var="gameValidation" value="${validations != null && validations.get('game') != null}"/>

    <fieldset class="game ${gameValidation ? 'field-error' : '' }">
        <label for="game">Game</label>
        <select name="game_id">
            <c:forEach var="game" items="${games}">
                <c:choose>
                    <c:when test="${game.id == character.game.id}">
                        <option value="${game.id}" label="${game.name}" selected/>
                    </c:when>
                    <c:otherwise>
                        <option value="${game.id}" label="${game.name}"/>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <c:if test="${gameValidation}">
             <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('game')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>


    <c:set var="pictureValidation" value="${validations != null && validations.get('picture') != null}"/>

    <fieldset class="picture ${pictureValidation ? 'field-error' : '' }">
        <label for="picture">Picture</label>
        <c:if test="${character.picture != null}">
            <img src="/uploads${character.picture}" height="50px"/>
        </c:if>
        <input type="file" name="picture"/>
        <c:if test="${pictureValidation}">
             <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('picture')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>

    <c:set var="nameValidation" value="${validations != null && validations.get('name') != null}"/>

    <fieldset class="name ${nameValidation ? 'field-error' : '' }">
        <label for="name">Name</label>
        <input type="text" name="name" value="${character != null ? character.name : ''}" maxlength="100"/>
        <c:if test="${nameValidation}">
            <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('name')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>


    <button type="submit" class="button blue">${param.button_name}</button>
</form>