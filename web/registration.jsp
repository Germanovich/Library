<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.registration" bundle="${rb}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">
</head>

<body>
<%@ include file="/header.jsp" %>
<main class="main">
    <div class="container" align="center">
        <form method="post" action="library">
            <input type="hidden" name="command" value="REGISTRATION"/>
            <input type="hidden" name="role" value="USER">
            <div class="col-md-3">
                <label for="validationName" class="form-label">
                    <fmt:message key="label.user.name" bundle="${rb}"/>
                </label>
                <input type="text" class="form-control" id="validationName"
                       pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                       title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                       name="name" required>
            </div>
            <div class="col-md-3">
                <label for="validationSurname" class="form-label">
                    <fmt:message key="label.user.surname" bundle="${rb}"/>
                </label>
                <input type="text" class="form-control" id="validationSurname"
                       pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                       title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                       name="surname" required>
            </div>
            <div class="col-md-3">
                <label for="validationDateOfBirth" class="form-label">
                    <fmt:message key="label.user.date_of_birth" bundle="${rb}"/>
                </label>
                <input type="date" class="form-control" id="validationDateOfBirth" name="dateOfBirth" required>
            </div>
            <div class="col-md-3">
                <label for="validationLogin" class="form-label">
                    <fmt:message key="label.user.login" bundle="${rb}"/>
                </label>
                <input type="text" class="form-control" id="validationLogin"
                       pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                       title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                       name="login" required>
            </div>
            <div class="col-md-3">
                <label for="validationPassword" class="form-label">
                    <fmt:message key="label.user.password" bundle="${rb}"/>
                </label>
                <input type="password" class="form-control" id="validationPassword"
                       pattern="([A-ZА-Яa-zа-я0-9]){6,20}"
                       title="<fmt:message key="label.valid.password" bundle="${rb}"/>"
                       name="password" required>
            </div>
            <c:if test="${not empty errorRegisterMessage}">
                <br/>
                <span style="color: #ff0000;">${errorRegisterMessage}</span>
                <br/>
            </c:if>
            <div class="col-12">
                <button type="submit" class="btn btn-lg btn-primary">
                    <fmt:message key="action.register" bundle="${rb}"/>
                </button>
            </div>
        </form>
    </div>
</main>
<%@ include file="/footer.jsp" %>
</body>
</html>
