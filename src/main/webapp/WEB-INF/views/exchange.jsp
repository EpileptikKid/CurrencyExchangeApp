<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Currency" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<!DOCTYPE html>
<html>
<head>
    <title>Обмін валют</title>
    <link rel="stylesheet" href="/resources/static/css/exchange.css">
</head>
<body>
<%
    List<Currency> currencies = (List<Currency>) request.getAttribute("currencies");
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(currencies);
    request.setAttribute("currenciesJson", json);
%>
<script src="${pageContext.request.contextPath}/resources/static/js/exchange.js"></script>
<script>
    window.onload = function() { updateRate(${currenciesJson}) }
    const changeListener = () => { updateRate(${currenciesJson}) }
</script>

<h1>Створення операції обміну валют</h1>

<form:form action="/user" method="post" modelAttribute="newOperation">
    <div class="div_container">
        <label for="currency">Оберіть валюту:</label>
        <form:select id="currency" path="currency">
            <form:options items="${currencies}" itemLabel="ccy" itemValue="ccy" />
        </form:select>
    </div>

    <div class="div_container">
        <label for="operation">Оберіть операцію:</label>
        <form:select id="operation" path="operation" onchange="changeListener()">
            <form:option value="buy">Покупка</form:option>
            <form:option value="sale">Продаж</form:option>
        </form:select>
    </div>

    <div class="div_container">
        <label for="rate">Курс:</label>
        <form:input id="rate" type="number" path="rate" readonly="true"/>
    </div>

    <div class="div_container">
        <label for="amount">Сума для обміну:</label>
        <form:input type="number" step="0.01" id="amount" path="amount"/>
    </div>

    <div class="div_container">
        <input class="btn" type="submit" value="Провести операцію" />
    </div>
</form:form>

<div class="button-container">
    <a href="/user/journal" class="btn">Журнал</a>
    <a href="/user/currencies" class="btn">Курси валют</a>
</div>

</body>
</html>