<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Довідник</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/journal.css">
</head>
<body>
<div class="container">
  <a href="/admin/currencies/add">Додати</a>
  <a href="/admin/journal">Назад</a>
</div>
<h1>Курси валют</h1>
<table>
  <thead>
  <tr>
    <th>Код валюти</th>
    <th>Код національної валюти</th>
    <th>Курс купівлі</th>
    <th>Курс продажу</th>
    <th>Змінити</th>
    <th>Видалити</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${currencies}" var="currency">
    <tr>
      <td>${currency.ccy}</td>
      <td>${currency.baseCcy}</td>
      <td>${currency.buy}</td>
      <td>${currency.sale}</td>
      <td><form action="/admin/currencies/${currency.ccy}" method="GET">
        <button type="submit">Змінити</button>
      </form></td>
      <td><form action="/admin/currencies/delete" method="POST">
        <input type="hidden" name="ccy" value="${currency.ccy}">
        <button type="submit">Видалити</button>
      </form></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
