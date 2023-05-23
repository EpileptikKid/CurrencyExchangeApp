<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Обмін валют</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/exchange.css">
</head>
<body>

<h1>Змінити курс обміну</h1>
<form:form action="/admin/currencies/update" method="post" modelAttribute="currency">
  <div class="div_container">
    <label for="ccy">Код валюти:</label>
    <form:input path="ccy" type="text" id="ccy" value="${currency.ccy}" readonly="true"/>
  </div>

  <div class="div_container">
    <label for="buy">Курс купівлі:</label>
    <form:input path="buy" id="buy" type="number" step="any" value="${currency.buy}"/>
  </div>

  <div class="div_container">
    <label for="sale">Курс купівлі:</label>
    <form:input path="sale" id="sale" type="number" step="any" value="${currency.sale}"/>
  </div>

  <div class="div_container">
    <input class="btn" type="submit" value="Змінити">
  </div>
</form:form>

<div class="button-container">
  <a href="/admin/currencies" class="btn">Курси валют</a>
</div>
</body>
</html>