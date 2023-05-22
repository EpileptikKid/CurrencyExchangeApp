<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Обмін валют</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/exchange.css">
</head>
<body>

<h1>Додати курс</h1>
<form action="/admin/currencies/add" method="POST">
  <label for="ccy">Код валюти: ${currency.ccy}</label>
  <input type="text" id="ccy" name="ccy" value="${currency.ccy}" />
  <br/>
  <label for="buy">Курс купівлі:</label>
  <input type="number" step="any" id="buy" name="buy" value="${currency.buy}" />
  <br/>
  <label for="sale">Курс продажу:</label>
  <input type="number" step="any" id="sale" name="sale" value="${currency.sale}" />
  <br/>
  <input type="submit" value="Додати" class="btn">
</form>

<div class="button-container">
  <a href="/admin/currencies" class="btn">Курси валют</a>
</div>
</body>
</html>