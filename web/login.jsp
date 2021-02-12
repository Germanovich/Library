<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="title.login" bundle="${rb}"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/login.css"/>" rel="stylesheet">
</head>

<body class="text-center">

<%@ include file="/header.jsp" %>

<main class="main">
    <div class="container">
        <form action="library" method="post">
            <input type="hidden" name="command" value="LOGIN"/>
            <h1 class="h3 mb-3 fw-normal"><fmt:message key="label.login.login" bundle="${rb}"/></h1>
            <label for="inputLogin" class="visually-hidden">
                <fmt:message key="label.login.login" bundle="${rb}"/>
            </label>
            <input type="text" id="inputLogin" class="form-control"
                   minlength="3" maxlength="20" name="login"
                   placeholder="<fmt:message key="label.login.login" bundle="${rb}"/>" required autofocus>
            <label for="inputPassword" class="visually-hidden">
                <fmt:message key="label.login.password" bundle="${rb}"/>
            </label>
            <input type="password" id="inputPassword" class="form-control"
                   minlength="6" maxlength="20" name="password"
                   placeholder="<fmt:message key="label.login.password" bundle="${rb}"/>" required>
            <c:if test="${ not empty errorLoginPassMessage }">
                <br/>
                <span style="color: #ff0000;">${errorLoginPassMessage}</span>
                <br/>
            </c:if>
            <button class="w-100 btn btn-lg btn-primary" type="submit">
                <fmt:message key="action.login" bundle="${rb}"/>
            </button>
        </form>
    </div>
</main>
<%@ include file="/footer.jsp" %>
</body>
</html>
