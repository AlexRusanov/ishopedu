<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Авторизация</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
    <h1>Вход</h1>
    <c:if test="${param.error != null}">
        <p style="color: red;">
            Неправильное имя или пароль
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style="color: green;">
            Вы успешно вышли
        </p>
    </c:if>
    <form:form>
        <p>
            <label for="username">Email</label>
            <input id="username" name="username"/>
        </p>
        <p>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password"/>
        </p>
        <button>Войти</button>
    </form:form>
</body>
</html>
