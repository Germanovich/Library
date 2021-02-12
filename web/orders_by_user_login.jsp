<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="title.orders" bundle="${rb}"/></title>
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
        <c:choose>
            <c:when test="${not empty search_text}">
                <h2>"${search_text}" <fmt:message key="label.search_results" bundle="${rb}"/></h2>
            </c:when>
            <c:otherwise>
                <h2><fmt:message key="label.book.list" bundle="${rb}"/></h2>
            </c:otherwise>
        </c:choose>
        <div class="row col-md-10">
            <ctg:table>
                <thead class="table-dark">
                <tr>
                    <td><fmt:message key="label.order.id" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.id" bundle="${rb}"/></td>
                    <td><fmt:message key="label.user.login" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.title" bundle="${rb}"/></td>
                    <td><fmt:message key="label.book.author" bundle="${rb}"/></td>
                    <td><fmt:message key="label.order.status" bundle="${rb}"/></td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.book.id}</td>
                        <td>${order.user.login}</td>
                        <td>${order.book.title}</td>
                        <td>${order.book.authorName}</td>
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
                            <c:choose>
                                <c:when test="${'NEW' eq order.orderStatus}">
                                    <form action="library" method="POST">
                                        <input type="hidden" name="command" value="CANCEL_ORDER"/>
                                        <input type="hidden" value="${order.id}" name="orderId">
                                        <button type="submit" class="btn btn-danger">
                                            <fmt:message key="action.cancel" bundle="${rb}"/>
                                        </button>
                                    </form>
                                </c:when>
                                <c:when test="${'IN_PROGRESS' eq order.orderStatus}">
                                    <form action="library" method="POST">
                                        <input type="hidden" name="command" value="CLOSE_ORDER"/>
                                        <input type="hidden" value="${order.id}" name="orderId">
                                        <button type="submit" class="btn btn-warning">
                                            <fmt:message key="action.close" bundle="${rb}"/>
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
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
                           href="library?command=ORDERS_BY_USER_LOGIN&search_text=${search_text}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">
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
                                   href="library?command=ORDERS_BY_USER_LOGIN&search_text=${search_text}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="library?command=ORDERS_BY_USER_LOGIN&search_text=${search_text}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">
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
