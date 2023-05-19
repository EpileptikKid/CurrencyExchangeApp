<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Currency" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<!DOCTYPE html>
<html>
<head>
    <title>Обмін валют</title>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/exchange.css">
</head>
<body>
<%
    List<Currency> currencies = (List<Currency>) request.getAttribute("currencies");
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(currencies);
    request.setAttribute("currenciesJson", json);
%>
<script src="/resources/static/js/exchange.js"></script>
<script>
    window.onload = function() { updateRate(${currenciesJson}) }
    const changeListener = () => { updateRate(${currenciesJson}) }

</script>

<h1>Створення операції обміну валют</h1>
<form class="container" action="/user" method="POST">
    <label for="currency">Оберіть валюту:</label>
    <select name="currency" id="currency" onchange="changeListener()">
        <c:forEach items="${currencies}" var="currency">
            <option value="${currency.ccy}">${currency.ccy}</option>
        </c:forEach>
    </select>
    <br><br>
    <label for="operation">Оберіть операцію:</label>
    <select name="operation" id="operation" onchange="changeListener()">
        <option value="buy">Покупка</option>
        <option value="sale">Продаж</option>
    </select>
    <br><br>
    <label for="rate">Курс:</label>
    <input type="number" name="rate" id="rate" readonly>
    <br><br>
    <label for="amount">Сума для обміну:</label>
    <input type="number" step="0.01" name="amount" id="amount">
    <br><br>
    <input type="submit" value="Провести операцію">
</form>

<div class="button-container">
    <a href="/user/journal" class="btn">Журнал</a>
    <a href="/user/currencies" class="btn">Курси валют</a>
</div>

</body>
</html>