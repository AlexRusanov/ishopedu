<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | ${product.name}</title>
</head>
<body>
<jsp:include page="../components/header.jsp"/>
    <h2>${product.name}</h2>
    <h3>Цена: ${product.price} USD</h3>
    <div>
        <p>
            <strong>Характеристики:</strong> <br/>
            Цвет: ${product.color} <br/>
            Экран: ${product.display} <br/>
            Память: ${product.capacity} GB<br/>
        </p>
        <form:form method="post" servletRelativeAction="/products/${product.id}/addToBasket">
            <a href="<c:url value="/products"/>"><button type="button">Вернуться</button></a>
            <button type="submit">Добавить в корзину</button>
        </form:form>
    </div>
    <hr/>
    <c:if test="${currentOrder.products.size() > 0}">
        <p><strong><i>Количество товаров в корзине: ${currentOrder.products.size()}.
            Общая сумма ${currentOrder.totalAmount}</i></strong> </p>
        <a href="<c:url value="/orders/newOrder"/> "><button>Перейти к оформлению заказа</button></a>
        <hr/>
    </c:if>
    <div>
        <h4>Отзывы:</h4>
        <c:forEach items="${comments}" var="comment">
            <p>
                <i>${comment.dateTime.format(dateTimeFormatter)}</i>
                <strong>${comment.author}</strong> <br/>
                ${comment.text} <br/>
                <u>Оценка: ${comment.rating}</u>
            </p>
        </c:forEach>
        <h4>Оставить свой отзыв</h4>
        <form:form modelAttribute="newComment" method="post" servletRelativeAction="/comments/" >
            <p>
                <label for="name">Имя:</label>
                <form:input type="text" id="name" path="author"/>
                <form:errors path="author" cssStyle="color:red;"/>
            </p>
            <p>
                <label for="comment">Комментарий:</label>
                <br/>
                <form:errors path="text" cssStyle="color:red;"/> <br/>
                <form:textarea id="comment" path="text" rows="10" cols="30"/>
            </p>
            <p>
                <label>Выставить оценку:</label> <br/>
                <label for="radio1">1</label>
                <form:radiobutton id="radio1" path="rating" value="1"/>
                <label for="radio2">2</label>
                <form:radiobutton id="radio2" path="rating" value="2"/>
                <label for="radio3">3</label>
                <form:radiobutton id="radio3" path="rating" value="3"/>
                <label for="radio4">4</label>
                <form:radiobutton id="radio4" path="rating" value="4"/>
                <label for="radio5">5</label>
                <form:radiobutton id="radio5" path="rating" value="5" checked="checked"/>
            </p>

            <form:hidden path="product.id" value="${product.id}"/>
            <button type="submit">Отправить</button>
        </form:form>
    </div>
</body>
</html>
