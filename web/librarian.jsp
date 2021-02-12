<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="title.librarian" bundle="${rb}"/></title>
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
        <h2><fmt:message key="label.orders.new" bundle="${rb}"/></h2>
        <div class="row col-md-10">
            <ctg:table>
                <thead class="table-dark">
                <tr>
                    <td><fmt:message key="label.order.id" bundle="${rb}"/></td>
                    <td><fmt:message key="label.user.id" bundle="${rb}"/></td>
                    <td><fmt:message key="label.user.name" bundle="${rb}"/></td>
                    <td><fmt:message key="label.user.login" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.title" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.author" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.year" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.quantity" bundle="${rb}"/></td>
                    <td><fmt:message key="label.order.type" bundle="${rb}"/></td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.user.id}</td>
                        <td>${order.user.name} ${order.user.surname}</td>
                        <td>${order.user.login}</td>
                        <td>${order.book.title}</td>
                        <td>${order.book.authorName}</td>
                        <td>${order.book.dateOfCreation}</td>
                        <c:choose>
                            <c:when test="${order.book.quantity > 0}">
                                <form action="library" method="POST">
                                    <input type="hidden" name="command" value="ACCEPT_ORDER"/>
                                    <td>
                                        <input type="hidden" value="${order.id}" name="orderId">
                                            ${order.book.quantity}
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select name="orderType" class="form-select">
                                                <option value="READING_ROOM">
                                                    <fmt:message key="title.order.type.reading_room" bundle="${rb}"/>
                                                </option>
                                                <option value="SUBSCRIPTION">
                                                    <fmt:message key="title.order.type.subscription" bundle="${rb}"/>
                                                </option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-success">
                                            <fmt:message key="action.order.accept" bundle="${rb}"/>
                                        </button>
                                    </td>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <td><fmt:message key="label.no_books" bundle="${rb}"/></td>
                                <td><select disabled></select></td>
                                <td>
                                    <button type="submit" class="btn btn-success" disabled>
                                        <fmt:message key="action.order.accept" bundle="${rb}"/>
                                    </button>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </ctg:table>
        </div>
        <nav>
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=NEW_ORDER_LIST&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                            <fmt:message key="label.item.previous" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
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
                                   href="library?command=NEW_ORDER_LIST&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=NEW_ORDER_LIST&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
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
