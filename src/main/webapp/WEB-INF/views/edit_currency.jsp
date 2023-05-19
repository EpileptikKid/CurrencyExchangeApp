<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Обмін валют</title>
  <link rel="stylesheet" type="text/css" href="/resources/static/css/exchange.css">
</head>
<body>

<h1>Змінити курс обміну</h1>
<form action="/admin/currencies/update" method="POST">
  <label for="ccy">Код валюти: ${currency.ccy}</label>
  <input type="hidden" id="ccy" name="ccy" value="${currency.ccy}" />
  <br/>
  <label for="buy">Курс купівлі:</label>
  <input type="number" step="any" id="buy" name="buy" value="${currency.buy}" />
  <br/>
  <label for="sale">Курс продажу:</label>
  <input type="number" step="any" id="sale" name="sale" value="${currency.sale}" />
  <br/>
  <input type="submit" value="Зберегти">
</form>

<div class="button-container">
  <a href="/admin/currencies" class="btn">Курси валют</a>
</div>
</body>
</html>