<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Довідник</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/journal.css">
</head>
<body>
<h1>Курси валют</h1>
<table>
  <thead>
  <tr>
    <th>Код валюти</th>
    <th>Код національної валюти</th>
    <th>Курс купівлі</th>
    <th>Курс продажу</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${currencies}" var="currency">
    <tr>
      <td>${currency.ccy}</td>
      <td>${currency.baseCcy}</td>
      <td>${currency.buy}</td>
      <td>${currency.sale}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="/user/exchange">Назад</a>
</body>
</html>
