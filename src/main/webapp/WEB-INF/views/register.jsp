<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Регистрация</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
    <h1>Регистрация нового пользователя</h1>
    <form:form modelAttribute="newUser">
        <p>
            <label>Email</label>
            <form:input path="email"/>
            <form:errors path="email" cssStyle="color: red"/>
        </p>
        <p>
            <label>Пароль</label>
            <form:input path="password"/>
            <form:errors path="password" cssStyle="color: red"/>
        </p>
        <p>
            Информация о себе:
        </p>
        <p>
            <label>Имя</label>
            <form:input path="name"/>
            <form:errors path="name" cssStyle="color: red"/>
        </p>
        <button>Зарегистрироваться</button>
    </form:form>

    <a href="<с:url value="/"/> ">
        <button>На главную</button>
    </a>
</body>
</html>
