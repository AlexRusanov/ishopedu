<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Оформление заказа</title>
</head>
<body>
<jsp:include page="../components/header.jsp"/>
<c:if test="${currentOrder.products.size() == 0}">
    На данный момент товаров в корзине нет
</c:if>

<c:if test="${currentOrder.products.size() > 0}">
    <h1>Товары в корзине</h1>
    <table border="1" cellpadding="5">
        <tr>
            <th>№</th>
            <th>Товар</th>
            <th>Цена</th>
            <th></th>
        </tr>
        <c:forEach items="${currentOrder.products}" var="product" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${product.name} ${product.color} ${product.capacity} GB</td>
                <td>${product.price}</td>
                <td>
                    <form method="post"
                          action="<c:url value="/orders/newOrder/removeProduct?index=${loop.index}"/> ">
                        <button type="submit">x</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <strong>Общая сумма: ${currentOrder.totalAmount} USD</strong>
    <p>
        <a href="<c:url value="/products"/> ">
            <button>Вернться к покупкам</button>
        </a>
    </p>

    <h2>Оформление заказа: </h2>
    <form:form modelAttribute="currentOrder">
        <p>
            <label for="name">Имя:</label>
            <form:input path="name"/>
            <form:errors path="name" cssStyle="color: red"/>
        </p>
        <p>
            <label for="email">Email:</label>
            <form:input path="email"/>
            <form:errors path="email" cssStyle="color: red"/>
        </p>
        <p>
            <label for="phone">Телефон:</label>
            <form:input path="phone" id="phone"/>
        </p>
        <p>
            <label for="address">Адрес доставки:</label> <br/>
            <form:textarea path="address" rows="10" cols="30"/> <br/>
            <form:errors path="address" cssStyle="color: red"/>
        </p>
        <button type="submit">Отправить заказ</button>
    </form:form>
</c:if>

</body>
</html>
