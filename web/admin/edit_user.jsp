<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.user.edit.or.add" bundle="${rb}"/></title>
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
    <div class="container">
        <form method="post" action="library">
            <input type="hidden" name="command" value="EDIT_USER"/>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationId" class="form-label">
                        <fmt:message key="label.user.id" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty user}">
                            <input readonly type="number" class="form-control" id="validationId" name="id">
                        </c:when>
                        <c:otherwise>
                            <input readonly type="number" class="form-control" id="validationId" name="id"
                                   value=${user.id}>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-3">
                    <label for="validationName" class="form-label">
                        <fmt:message key="label.user.name" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty user}">
                            <input type="text" class="form-control" id="validationName"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="name" required>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="validationName"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="name" value="${user.name}" required>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-3">
                    <label for="validationSurname" class="form-label">
                        <fmt:message key="label.user.surname" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty user}">
                            <input type="text" class="form-control" id="validationSurname"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="surname" required>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="validationSurname"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="surname" value=${user.surname} required>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-3">
                    <label for="validationLogin" class="form-label">
                        <fmt:message key="label.user.login" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty user}">
                            <input type="text" class="form-control" id="validationLogin"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="login" required>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="validationLogin"
                                   pattern="([A-ZА-Яa-zа-я0-9]){3,20}"
                                   title="<fmt:message key="label.valid.text" bundle="${rb}"/>"
                                   name="login" value="${user.login}" required>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-3">
                    <label for="validationPassword" class="form-label">
                        <fmt:message key="label.user.password" bundle="${rb}"/>
                    </label>
                    <input type="text" class="form-control" id="validationPassword"
                           pattern="([A-ZА-Яa-zа-я0-9]){6,20}"
                           title="<fmt:message key="label.valid.password" bundle="${rb}"/>"
                           name="password" required>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty user}">
                </c:when>
                <c:otherwise>
                    <div class="row justify-content-md-center">
                        <div class="col-md-3">
                            <label for="validationDateOfRegistration" class="form-label">
                                <fmt:message key="label.user.date_of_registration" bundle="${rb}"/>
                            </label>
                            <input type="date" class="form-control" id="validationDateOfRegistration"
                                   name="dateOfRegistration"
                                   value="${user.dateOfRegistration}">
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="row justify-content-md-center">
                <div class="col-md-3">
                    <label for="validationRole" class="form-label">
                        <fmt:message key="label.user.role" bundle="${rb}"/>
                    </label>
                    <select class="form-select" id="validationRole" name="role">
                        <c:choose>
                            <c:when test="${empty user}">
                                <option selected disabled value=""></option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="${user.role}">
                                    <fmt:message key="label.user.role.current" bundle="${rb}"/> -
                                    <c:choose>
                                        <c:when test="${user.role eq 'ADMIN'}">
                                            <fmt:message key="label.user.role.admin" bundle="${rb}"/>
                                        </c:when>
                                        <c:when test="${user.role eq 'LIBRARIAN'}">
                                            <fmt:message key="label.user.role.librarian" bundle="${rb}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="label.user.role.user" bundle="${rb}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </option>
                            </c:otherwise>
                        </c:choose>
                        <option value="ADMIN"><fmt:message key="label.user.role.admin" bundle="${rb}"/></option>
                        <option value="LIBRARIAN"><fmt:message key="label.user.role.librarian" bundle="${rb}"/></option>
                        <option value="User"><fmt:message key="label.user.role.user" bundle="${rb}"/></option>
                    </select>
                </div>
            </div>
            <div class="row" align="center">
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">
                        <c:choose>
                            <c:when test="${empty user}">
                                <fmt:message key="action.user.add" bundle="${rb}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="action.user.edit" bundle="${rb}"/>
                            </c:otherwise>
                        </c:choose>
                    </button>
                    <a class="btn btn-primary" href="library?command=USER_LIST" role="button">
                        <fmt:message key="action.cancel" bundle="${rb}"/>
                    </a>
                </div>
            </div>
        </form>
    </div>
</main>
<%@ include file="/footer.jsp" %>
</body>
</html>
