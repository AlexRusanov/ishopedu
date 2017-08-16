<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Предыдущие заказы</title>
</head>
<body>
    <h1>Предыдущие заказы</h1>
    <c:if test="${orders.size() == 0}">
        Истоиря заказов на данный момент отсутствует
    </c:if>
    <c:forEach items="${orders}" var="order">
        <p>
            <strong>Дата:</strong> ${order.dateTime.format(dateTimeFormatter)} <br/>
            <strong>Имя:</strong> ${order.name} <br/>
            <strong>Почта:</strong> ${order.email} <br/>
            <strong>Телефон:</strong> ${order.phone} <br/>
            <strong>Общая сумма:</strong> ${order.totalAmount} <br/>
            <strong>Товары:</strong> <br/>
            <c:forEach items="${order.products}" var="product">
                ${product.name} ${product.color} ${product.capacity} GB <br/>
            </c:forEach>
            <strong>Адрес доставки:</strong> ${order.address}
        </p>
    </c:forEach>
    <a href="<c:url value="/"/> "><button>На главную</button></a>
</body>
</html>
