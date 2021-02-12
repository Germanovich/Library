<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.book.edit.or.add" bundle="${rb}"/></title>
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
            <input type="hidden" name="command" value="EDIT_BOOK"/>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationId" class="form-label">
                        <fmt:message key="label.book.id" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty book}">
                            <input readonly type="number" class="form-control" id="validationId" name="id">
                        </c:when>
                        <c:otherwise>
                            <input readonly type="number" class="form-control" id="validationId" name="id"
                                   value=${book.id}>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationTitle" class="form-label">
                        <fmt:message key="label.book.title" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty book}">
                            <input type="text" class="form-control" id="validationTitle" name="title" required>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="validationTitle" name="title"
                                   value="${book.title}"
                                   required>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationAuthorName" class="form-label">
                        <fmt:message key="label.book.author" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty book}">
                            <input type="text" class="form-control" id="validationAuthorName" name="authorName"
                                   required>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="validationAuthorName" name="authorName"
                                   value=${book.authorName} required>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationDateOfCreation" class="form-label">
                        <fmt:message key="label.user.date_of_birth" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty book}">
                            <input type="date" class="form-control" id="validationDateOfCreation" name="dateOfCreation"
                                   required>
                        </c:when>
                        <c:otherwise>
                            <input type="date" class="form-control" id="validationDateOfCreation" name="dateOfCreation"
                                   value="${book.dateOfCreation}" required>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-2">
                    <label for="validationQuantity" class="form-label">
                        <fmt:message key="label.book.quantity" bundle="${rb}"/>
                    </label>
                    <c:choose>
                        <c:when test="${empty book}">
                            <input type="number" class="form-control" id="validationQuantity"
                                   min="0" name="quantity" required>
                        </c:when>
                        <c:otherwise>
                            <input type="number" class="form-control" id="validationQuantity" name="quantity"
                                   min="0" value="${book.quantity}" required>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row" align="center">
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">
                        <c:choose>
                            <c:when test="${empty book}">
                                <fmt:message key="action.book.add" bundle="${rb}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="action.book.edit" bundle="${rb}"/>
                            </c:otherwise>
                        </c:choose>
                    </button>
                    <a class="btn btn-primary" href="library?command=BOOK_LIST" role="button">
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
