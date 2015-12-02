<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${param.action}" method="POST" enctype="multipart/form-data">
    <c:if test="${param.method_override != null}">
        <input type="hidden" name="_method" value="${param.method_override}"/>
    </c:if>
    
    <input type="hidden" name="picture" value="${user.picture}" />

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

	<c:set var="pictureValidation" value="${validations != null && validations.get('picture') != null}"/>

    <fieldset class="picture ${pictureValidation ? 'field-error' : '' }">
        <label for="userPicture">Picture</label>
        <c:if test="${user.picture != null}">
            <img src="/uploads${user.picture}" height="50px"/>
        </c:if>
        <input type="file" name="userPicture"/>
        <c:if test="${pictureValidation}">
             <ul class="field-error-messages">
                <c:forEach var="validation" items="${validations.get('picture')}">
                    <li>${validation}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fieldset>
    <button type="submit" class="button blue">${param.button_name}</button>
</form>