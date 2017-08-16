<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Главная</title>
</head>
<body>
<h1>Добропожаловать в мазазин iShop</h1>
<ul>
    <li><a href="<c:url value="/products"/>">Каталог товаров</a></li>
    <li><a href="<c:url value="/orders"/> ">Предыдущие заказы</a></li>
</ul>
</body>
</html>
