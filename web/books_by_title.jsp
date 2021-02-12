<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="label.book.list" bundle="${rb}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>

<%@ include file="/header.jsp" %>

<main class="main">
    <div class="container" align="center">
        <c:choose>
            <c:when test="${not empty title}">
                <h2>"${title}" <fmt:message key="label.search_results" bundle="${rb}"/></h2>
            </c:when>
            <c:otherwise>
                <h2><fmt:message key="label.book.list" bundle="${rb}"/></h2>
            </c:otherwise>
        </c:choose>
        <div class="row col-md-10">
            <ctg:table>
                <thead class="table-dark">
                <tr>
                    <td><fmt:message key="label.book.author" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.title" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.year" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.quantity" bundle="${rb}"/></td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.authorName}</td>
                        <td>${book.title}</td>
                        <td>${book.dateOfCreation}</td>
                        <td>${book.quantity}</td>
                        <td>
                            <form action="library" method="POST">
                                <input type="hidden" name="command" value="CREATE_ORDER"/>
                                <input type="hidden" value="${book.id}" name="bookId">
                                <div class="button">
                                    <c:choose>
                                        <c:when test="${book.quantity > 0}">
                                            <button type="submit" class="btn btn-success" value="Order">
                                                <fmt:message key="action.order" bundle="${rb}"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-success" disabled>
                                                <fmt:message key="action.order" bundle="${rb}"/>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </ctg:table>
        </div>
        <nav>
            <ul class=" pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=BOOKS_BY_TITLE&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                            <fmt:message key="label.item.previous" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active">
                                <a class="page-link">${i}
                                    <span class="sr-only">
                                        (<fmt:message key="label.item.current" bundle="${rb}"/>)
                                    </span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="library?command=BOOKS_BY_TITLE&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=BOOKS_BY_TITLE&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
                            <fmt:message key="label.item.next" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</main>
<%@ include file="/footer.jsp" %>
</body>
</html>
