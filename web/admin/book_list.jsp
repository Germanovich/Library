<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.books" bundle="${rb}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">
</head>
<body link=#4483e2 vlink=#09607c alink=#f20e56>

<%@ include file="/header.jsp" %>

<main class="main">
    <div class="container" align="center">
        <h2><fmt:message key="label.book.list" bundle="${rb}"/></h2>
        <form action="library?command=EDIT_BOOK_PAGE" method="POST">
            <div class="button">
                <button type="submit" class="btn btn-secondary">
                    <fmt:message key="action.book.add" bundle="${rb}"/>
                </button>
            </div>
        </form>
        <div class="row">
            <ctg:table>
                <thead class="table-dark">
                <tr>
                    <td><fmt:message key="label.book.id" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.title" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.author" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.year" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.quantity" bundle="${rb}"/></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.authorName}</td>
                        <td>${book.dateOfCreation}</td>
                        <td>${book.quantity}</td>
                        <td>
                            <form action="library?command=DELETE_BOOK&bookId=${book.id}" method="POST">
                                <div class="button">
                                    <button type="submit" class="btn btn-danger">
                                        <fmt:message key="action.delete" bundle="${rb}"/>
                                    </button>
                                </div>
                            </form>
                        </td>
                        <td>
                            <form action="library?command=EDIT_BOOK_PAGE&bookId=${book.id}" method="POST">
                                <div class="button">
                                    <button type="submit" class="btn btn-warning">
                                        <fmt:message key="action.edit" bundle="${rb}"/>
                                    </button>
                                </div>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </ctg:table>
        </div>
        <nav>
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=BOOK_LIST&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                            <fmt:message key="label.item.previous" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active">
                                <a class="page-link">
                                        ${i}
                                    <span class="sr-only">
                                        (<fmt:message key="label.item.current" bundle="${rb}"/>)
                                    </span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="library?command=BOOK_LIST&recordsPerPage=${recordsPerPage}&currentPage=${i}">
                                        ${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=BOOK_LIST&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
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
