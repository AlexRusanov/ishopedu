<%@ page import="edu.bionic.domain.ProductSort" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iShop | Каталог товаров</title>
</head>
<body>
<h1>Каталог товаров iShop</h1>
<form>
    <p>
        <label for="name">Имя товара</label>
        <input type="text" id="name" name="name" value="${param.name}"/>
    </p>
    <p>
        Цена:
        <label for="min">мин</label>
        <input type="text" id="min" name="min" value="${param.min}"/>
        <label for="max">макс</label>
        <input type="text" id="max" name="max" value="${param.max}"/>

    </p>
    <p>
        <label for="sort">Сортировка</label>
        <select id="sort" name="sort">
            <option value="<%= ProductSort.NAME_ASC.name() %>"
                    <%= ProductSort.NAME_ASC.name().equals(request.getParameter("sort")) ? "selected" : "" %>>По алфавиту А-Я</option>
            <option value="<%= ProductSort.NAME_DESC.name() %>"
                    <%= ProductSort.NAME_DESC.name().equals(request.getParameter("sort")) ? "selected" : "" %>>По алфавиту Я-А</option>
            <option value="<%= ProductSort.PRICE_ASC.name() %>"
                    <%= ProductSort.PRICE_ASC.name().equals(request.getParameter("sort")) ? "selected" : "" %>>От дешевых к дорогим</option>
            <option value="<%= ProductSort.PRICE_DESC.name() %>"
                    <%= ProductSort.PRICE_DESC.name().equals(request.getParameter("sort")) ? "selected" : "" %>>От дорогих к дешевым</option>
        </select>
    </p>
    <button type="submit">Поиск</button>
</form>
<ul>
    <c:forEach items="${products}" var="product">
        <jsp:useBean id="product" type="edu.bionic.domain.Product"/>
        <%--<li><c:out value="${product.printInfo()}"/></li>--%>
        <li>
            <p>
                <a href="<c:url value="products/${product.id}"/>">${product.printInfo()}</a>
            </p>
        </li>
    </c:forEach>

    <%-- Paginator --%>
    <p>
        <c:set var="page" value="${param.page != null ? param.page : 1}"/>
        <c:set var="query" value="${pageContext.request.queryString != null ?
            pageContext.request.queryString.replaceFirst('&page=\\\\d+', '') : ''}"/>
        <c:forEach begin="1" end="${productCount % pageSize == 0 ?
        productCount / pageSize :
        productCount / pageSize + 1 }" varStatus="loop">
            <c:choose>
                <c:when test="${page == loop.index}">
                    <button type="button" disabled>${loop.index}</button>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="products?${query}&page=${loop.index}"/>">
                        <button type="button">${loop.index}</button>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </p>
</ul>
<a href="<c:url value="/"/> ">
    <button>На главную</button>
</a>
</body>
</html>
