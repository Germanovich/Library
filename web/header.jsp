<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <div class="localization">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="label.language" bundle="${rb}"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li class="localization-item">
                                <a class="dropdown-item" href="library?command=ENGLISH_LANGUAGE">
                                    <fmt:message key="label.language.english" bundle="${rb}"/>
                                </a>
                            </li>
                            <li class="localization-item">
                                <a class="dropdown-item" href="library?command=RUSSIAN_LANGUAGE">
                                    <fmt:message key="label.language.russian" bundle="${rb}"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <c:choose>
                    <c:when test="${ not empty sessionUser }">
                        <li class="nav-item">
                            <a class="nav-link" href="library?command=logout">
                                <fmt:message key="action.logout" bundle="${rb}"/>
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${sessionUser.role eq 'ADMIN' }">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        <fmt:message key="label.commands" bundle="${rb}"/>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-dark"
                                        aria-labelledby="navbarDarkDropdownMenuLink">
                                        <li>
                                            <a class="dropdown-item" href="library?command=ORDER_LIST"
                                               class="localization-link">
                                                <fmt:message key="label.order.list" bundle="${rb}"/>
                                            </a>
                                            <a class="dropdown-item" href="library?command=USER_LIST"
                                               class="localization-link">
                                                <fmt:message key="label.user.list" bundle="${rb}"/>
                                            </a>
                                            <a class="dropdown-item" href="library?command=BOOK_LIST"
                                               class="localization-link">
                                                <fmt:message key="label.book.list" bundle="${rb}"/>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:when test="${sessionUser.role eq 'LIBRARIAN' }">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        <fmt:message key="label.commands" bundle="${rb}"/>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-dark"
                                        aria-labelledby="navbarDarkDropdownMenuLink">
                                        <li>
                                            <a class="dropdown-item" href="library?command=NEW_ORDER_LIST"
                                               class="localization-link">
                                                <fmt:message key="action.orders.new" bundle="${rb}"/>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="library?command=ORDER_LIST"
                                               class="localization-link">
                                                <fmt:message key="action.orders.all" bundle="${rb}"/>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <form class="d-flex" method="post" action="library">
                                        <input type="hidden" name="command" value="ORDERS_BY_USER_LOGIN"/>
                                        <input class="form-control me-2" type="search" name="search_text"
                                               placeholder="<fmt:message key="action.search.users.by.login" bundle="${rb}"/>"/>
                                        <button class="btn btn-outline-success me-2" type="submit">
                                            <fmt:message key="action.search" bundle="${ rb }"/>
                                        </button>
                                    </form>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        <fmt:message key="label.commands" bundle="${rb}"/>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-dark"
                                        aria-labelledby="navbarDarkDropdownMenuLink">
                                        <li><a class="dropdown-item" href="library?command=BOOK_LIST"
                                               class="localization-link">
                                            <fmt:message key="action.go.catalog" bundle="${rb}"/></a></li>
                                        <li><a class="dropdown-item" href="library?command=profile"
                                               class="localization-link">
                                            <fmt:message key="action.go.profile" bundle="${rb}"/></a></li>
                                    </ul>
                                </li>
                                <li>
                                    <form class="d-flex" method="post" action="library">
                                        <input type="hidden" name="command" value="BOOKS_BY_TITLE"/>
                                        <input class="form-control me-2" type="search" name="search_text"
                                               placeholder="<fmt:message key="action.search.books.by.title" bundle="${rb}"/>">
                                        <button class="btn btn-outline-success me-2" type="submit">
                                            <fmt:message key="action.search" bundle="${rb}"/>
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form class="d-flex" method="post" action="library">
                                        <input type="hidden" name="command" value="BOOKS_BY_AUTHOR"/>
                                        <input class="form-control me-2" type="search" name="search_text"
                                               placeholder="<fmt:message key="action.search.books.by.author" bundle="${rb}"/>">
                                        <button class="btn btn-outline-success me-2" type="submit">
                                            <fmt:message key="action.search" bundle="${ rb }"/>
                                        </button>
                                    </form>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="library?command=forward&page=registration">
                                <fmt:message key="action.registration" bundle="${rb}"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="library?command=forward&page=login">
                                <fmt:message key="action.login" bundle="${rb}"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>
