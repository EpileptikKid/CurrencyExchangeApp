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

<h1>Додати курс</h1>
<form:form action="/admin/currencies/add" method="post" modelAttribute="addedCurrencies">
  <div class="div_container">
    <label for="ccy">Код валюти: </label>
    <form:input path="ccy" type="text" id="ccy" />
  </div>

  <div class="div_container">
    <label for="buy">Курс купівлі</label>
    <form:input path="buy" id="buy" type="number" step="any" />
  </div>

  <div class="div_container">
    <label for="sale">Курс продажу</label>
    <form:input path="sale" id="sale" type="number" step="any" />
  </div>

  <div class="div_container">
    <input class="btn" type="submit" value="Додати">
  </div>
</form:form>

<div class="button-container">
  <a href="/admin/currencies" class="btn">Курси валют</a>
</div>
</body>
</html>