<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.order.edit.or.add" bundle="${rb}"/></title>
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
            <input type="hidden" name="command" value="EDIT_ORDER"/>
            <div class="row justify-content-md-center">
            <div class="col-md-2">
                <label for="validationId" class="form-label">
                    <fmt:message key="label.order.id" bundle="${rb}"/>
                </label>
                <c:choose>
                    <c:when test="${empty order}">
                        <input readonly type="number" class="form-control" id="validationId" name="id">
                    </c:when>
                    <c:otherwise>
                        <input readonly type="number" class="form-control" id="validationId" name="id"
                               value=${order.id}>
                    </c:otherwise>
                </c:choose>
            </div>
            </div>
            <div class="row justify-content-md-center">
            <div class="col-md-2">
                <label for="validationBookId" class="form-label">
                    <fmt:message key="label.book.id" bundle="${rb}"/>
                </label>
                <c:choose>
                    <c:when test="${empty order}">
                        <input type="number" class="form-control" id="validationBookId" name="bookId">
                    </c:when>
                    <c:otherwise>
                        <input type="number" class="form-control" id="validationBookId" name="bookId"
                               value=${order.book.id}>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-2">
                <label for="validationUserId" class="form-label">
                    <fmt:message key="label.user.id" bundle="${rb}"/>
                </label>
                <c:choose>
                    <c:when test="${empty order}">
                        <input type="number" class="form-control" id="validationUserId" name="userId">
                    </c:when>
                    <c:otherwise>
                        <input type="number" class="form-control" id="validationUserId" name="userId"
                               value=${order.user.id}>
                    </c:otherwise>
                </c:choose>
            </div>
            </div>
            <div class="row justify-content-md-center">
            <div class="col-md-2">
                <label for="validationStartDate" class="form-label">
                    <fmt:message key="label.order.start_date" bundle="${rb}"/>
                </label>
                <c:choose>
                    <c:when test="${empty order}">
                        <input type="date" class="form-control" id="validationStartDate" name="startDate"
                               required>
                    </c:when>
                    <c:otherwise>
                        <input type="date" class="form-control" id="validationStartDate" name="startDate"
                               value="${order.startDate}" required>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:choose>
                <c:when test="${empty order}">
                </c:when>
                <c:otherwise>
                    <div class="col-md-2">
                        <label for="validationEndDate" class="form-label">
                            <fmt:message key="label.order.end_date" bundle="${rb}"/>
                        </label>
                        <input type="date" class="form-control" id="validationEndDate" name="endDate"
                               value="${order.endDate}">
                    </div>
                </c:otherwise>
            </c:choose>
            </div>
            <div class="row justify-content-md-center">
            <div class="col-md-2">
                <label for="validationType" class="form-label">
                    <fmt:message key="label.order.type" bundle="${rb}"/>
                </label>
                <select class="form-select" id="validationType" name="orderType" required>
                    <c:choose>
                        <c:when test="${empty order}">
                            <option selected disabled value=""></option>
                        </c:when>
                        <c:otherwise>
                            <option selected value="${order.orderType}">
                                <fmt:message key="label.order.type.current" bundle="${rb}"/> -
                                <c:choose>
                                    <c:when test="${order.orderType eq 'SUBSCRIPTION'}">
                                        <fmt:message key="label.order.type.subscription" bundle="${rb}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="label.order.type.reading_room" bundle="${rb}"/>
                                    </c:otherwise>
                                </c:choose>
                            </option>
                        </c:otherwise>
                    </c:choose>
                    <option value="READING_ROOM"><fmt:message key="label.order.type.reading_room"
                                                              bundle="${rb}"/></option>
                    <option value="SUBSCRIPTION"><fmt:message key="label.order.type.subscription"
                                                              bundle="${rb}"/></option>
                </select>
            </div>
            <div class="col-md-2">
                <label for="validationStatus" class="form-label">
                    <fmt:message key="label.order.status" bundle="${rb}"/>
                </label>
                <select class="form-select" id="validationStatus" name="orderStatus" required>
                    <c:choose>
                        <c:when test="${empty order}">
                            <option selected disabled value=""></option>
                        </c:when>
                        <c:otherwise>
                            <option selected value="${order.orderStatus}">
                                <fmt:message key="label.order.status.current" bundle="${rb}"/> -
                                <c:choose>
                                    <c:when test="${order.orderStatus eq 'NEW'}">
                                        <fmt:message key="label.order.status.new" bundle="${rb}"/>
                                    </c:when>
                                    <c:when test="${order.orderStatus eq 'IN_PROGRESS'}">
                                        <fmt:message key="label.order.status.in_progress" bundle="${rb}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="label.order.status.closed" bundle="${rb}"/>
                                    </c:otherwise>
                                </c:choose>
                            </option>
                        </c:otherwise>
                    </c:choose>
                    <option value="NEW"><fmt:message key="label.order.status.new" bundle="${rb}"/></option>
                    <option value="IN_PROGRESS"><fmt:message key="label.order.status.in_progress"
                                                             bundle="${rb}"/></option>
                    <option value="CLOSED"><fmt:message key="label.order.status.closed" bundle="${rb}"/></option>
                </select>
            </div>
            </div>
            <div class="row" align="center">
            <div class="col-12">
                <button class="btn btn-primary" type="submit">
                    <c:choose>
                        <c:when test="${empty order}">
                            <fmt:message key="action.order.add" bundle="${rb}"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="action.order.edit" bundle="${rb}"/>
                        </c:otherwise>
                    </c:choose>
                </button>
                <a class="btn btn-primary" href="library?command=ORDER_LIST" role="button">
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
