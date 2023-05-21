<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.temporal.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Журнал</title>
  <link rel="stylesheet" type="text/css" href="/resources/static/css/journal.css">
</head>
<body>
<h1>Список проведених обмінів</h1>
<c:if test="${exchangeOperationList != null}">
<table>
  <thead>
  <tr>
    <th>Дата</th>
    <th>Валюта</th>
    <th>Курс</th>
    <th>Операція</th>
    <th>Сума</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${exchangeOperationList}" var="exchangeOperation">
    <tr>
      <td>${exchangeOperation.date}</td>
      <td>${exchangeOperation.currency}</td>
      <td>${exchangeOperation.rate}</td>
      <td>${exchangeOperation.operation}</td>
      <td>${exchangeOperation.amount}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</c:if>

<c:if test="${groupedOperations != null}">
  <table>
    <thead>
    <tr>
      <th>Дата</th>
      <th>Операція</th>
      <th>Сума</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groupedOperations}" var="entry">
      <tr>
        <td rowspan="2">${entry.key}</td>
        <td>Купівля</td>
        <td>
          <c:if test="${entry.value.get('buy') != null}">${entry.value.get("buy")}</c:if>
          <c:if test="${entry.value.get('buy') == null}">0</c:if>
        </td>
      </tr>
      <tr>
          <td>Продаж</td>
          <td>
            <c:if test="${entry.value.get('sale') != null}">${entry.value.get("sale")}</c:if>
            <c:if test="${entry.value.get('sale') == null}">0</c:if>
          </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>

<div class="container">
  <form id="groupForm" action="/user/journal" method="GET">
    <div>
      <label for="groupBy">Згрупувати по:</label>
      <select id="groupBy" name="groupBy">
        <option value="day">Дням</option>
        <option value="month">Місяцям</option>
        <option value="quartet">Кварталам</option>
        <option value="year">Рокам</option>
      </select>
      <button type="submit">Сгрупувати</button>
    </div>
  </form>

  <a href="/user/exchange">Назад</a>
</div>

</body>
</html>