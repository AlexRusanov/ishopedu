<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="background: ghostwhite">
    <a href="<c:url value="/"/> ">
        Главная
    </a>
    <a href="<c:url value="/products"/> ">
        Товары
    </a>

    <sec:authorize access="isAuthenticated()">
        <form:form servletRelativeAction="/logout" cssStyle="float: right">
            <button>Выйти</button>
        </form:form>
        <sec:authorize url="/admin/products">
            <a href="<c:url value="/admin/products"/>" style="float: right">
                <button>Админпанель</button>
            </a>
        </sec:authorize>
        <b style="float: right">Добро пожаловать, ${loggedUser.name} </b>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a href="<c:url value="/login"/>" style="float: right">
            <button>Войти</button>
        </a>
        <a href="<c:url value="/register"/>" style="float: right">
            <button>Зарегистрироваться</button>
        </a>
    </sec:authorize>

</div>