<%@ page import="edu.bionic.domain.Color" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop Админка | Редактирование продукта</title>
</head>
<body>
    <h1>${isNew ? "Новый товар" : "Редактирование товара №".concat(product.id)}</h1>
    <form:form modelAttribute="product">
        <form:hidden path="id"/>
        <p>
            <label for="name">Наименование</label>
            <form:input path="name"/>
            <form:errors path="name" cssStyle="color: red"/>
        </p>
        <p>
            <label for="price">Цена</label>
            <form:input path="price"/>
            <form:errors path="price" cssStyle="color: red"/>
        </p>
        <p>
            <label for="color">Цвет</label><br>
            <form:radiobuttons path="color" items="<%= Color.values()%>"/>
            <form:errors path="color" cssStyle="color: red"/>

        </p>
        <p>
            <label for="capacity">Память, GB</label>
            <form:input path="capacity"/>
            <form:errors path="capacity" cssStyle="color: red"/>
        </p>
        <p>
            <label for="display">Экран</label>
            <form:input path="display"/>
            <form:errors path="display" cssStyle="color: red"/>
        </p>
        <p>
            <label for="description">Описание</label><br>
            <form:textarea path="description" rows="10" cols="30"/>
        </p>
        <a href="<c:url value="/admin/products"/>"><button type="button">Вернуться</button></a>
        <button type="submit">Сохранить</button>
    </form:form>
    <c:if test="${updateIsSuccessful}">
        <span style="color: green;">Изменения сохранены</span>
    </c:if>
</body>
</html>
