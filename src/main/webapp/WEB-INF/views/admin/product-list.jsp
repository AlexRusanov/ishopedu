<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop Админка  | Список товаров</title>
</head>
<body>
    <h1>Таблица товаров магазина</h1>
    <a href="<c:url value="/admin/products/new"/>">
        <button type="button">Добавить товар</button>
    </a>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Цена</th>
            <th>Цвет</th>
            <th>Память</th>
            <th>Экран</th>
            <th>Описание</th>
            <th></th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.color}</td>
                <td>${product.capacity} GB</td>
                <td>${product.display}</td>
                <td>${product.description}</td>
                <td>
                    <a href="<c:url value="/admin/products/${product.id}"/>">
                        <button type="submit">Редактировать</button>
                    </a>
                    <form method="post"
                          action="<c:url value="/admin/products/${product.id}/delete"/>">
                        <button type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
