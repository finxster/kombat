<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${param.action}" method="POST">
    <c:if test="${param.method_override != null}">
        <input type="hidden" name="_method" value="${param.method_override}"/>
    </c:if>

    <c:set var="nameValidation" value="${validations != null && validations.get('name') != null}"/>

    <fieldset class="name ${nameValidation ? 'field-error' : '' }">
        <label for="name">Name</label>
        <input type="text" name="name" value="${user != null ? user.name : ''}" maxlength="100"/>
        <c:if test="${nameValidation}">
            <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('name')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>

    <c:set var="emailValidation" value="${validations != null && validations.get('email') != null}"/>

    <fieldset class="email ${emailValidation ? 'field-error' : '' }">
        <label for="email">Email</label>
        <input type="email" name="email" value="${user != null  ? user.email : ''}" maxlength="50"/>
        <c:if test="${emailValidation}">
             <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('email')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>

    <c:set var="passwordValidation" value="${validations != null && validations.get('password') != null}"/>

    <fieldset class="password ${passwordValidation ? 'field-error' : '' }">
        <label for="password">Password</label>
        <input type="password" name="password" value="${user != null ? user.password : ''}" maxlength="20"/>
        <c:if test="${passwordValidation}">
             <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('password')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>


    <button type="submit" class="button blue">${param.button_name}</button>
</form>