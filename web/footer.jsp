<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<footer class="footer mt-auto py-3 bg-dark">
    <div class="container" align="center">
        <span class="text-muted">&copy; <fmt:message key="footer.copyright" bundle="${ rb }"/></span>
    </div>
</footer>
