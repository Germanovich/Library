<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="label.profile" bundle="${rb}"/></title>

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
        <h1><fmt:message key="label.greeting" bundle="${rb}"/>, ${sessionUser.login}!</h1>
        <h4>${sessionUser.name} ${sessionUser.surname}</h4>
        <hr>
        <h4><fmt:message key="label.user.orders" bundle="${rb}"/></h4>
        <div class="row col-md-10">
            <ctg:table>
                <thead class="table-dark">
                <td><fmt:message key="label.book.author" bundle="${rb}"/></td>
                <td><fmt:message key="label.book.title" bundle="${rb}"/></td>
                <td><fmt:message key="label.book.year" bundle="${rb}"/></td>
                <td><fmt:message key="label.order.status" bundle="${rb}"/></td>
                <td><fmt:message key="label.order.type" bundle="${rb}"/></td>
                <td></td>
                </thead>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.book.authorName}</td>
                        <td>${order.book.title}</td>
                        <td>${order.book.dateOfCreation}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.orderStatus eq 'NEW'}">
                                    <fmt:message key="title.order.status.new" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${order.orderStatus eq 'IN_PROGRESS'}">
                                    <fmt:message key="title.order.status.in_progress" bundle="${rb}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="title.order.status.closed" bundle="${rb}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${!empty order.orderType}">
                                <c:choose>
                                    <c:when test="${order.orderType eq 'READING_ROOM'}">
                                        <fmt:message key="title.order.type.reading_room" bundle="${rb}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="title.order.type.subscription" bundle="${rb}"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </td>
                        <td>
                            <form action="library" method="POST">
                                <input type="hidden" name="command" value="CANCEL_ORDER"/>
                                <input type="hidden" value="${order.id}" name="orderId">
                                <div class="button">
                                    <c:choose><c:when test="${order.orderStatus eq 'NEW'}">
                                        <button type="submit" class="btn btn-danger">
                                            <fmt:message key="action.cancel" bundle="${rb}"/>
                                        </button>
                                    </c:when><c:otherwise>
                                        <button type="submit" class="btn btn-danger" disabled>
                                            <fmt:message key="action.cancel" bundle="${rb}"/>
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
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=profile&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
                            Previous
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="library?command=profile&recordsPerPage=${recordsPerPage}&currentPage=${i}">
                                        ${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=profile&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
                            Next
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
