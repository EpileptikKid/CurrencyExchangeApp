<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Журнал</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/journal.css">
</head>
<body>
<h1>Список проведених обмінів</h1>

  <table>
    <thead>
    <tr>
      <th>id</th>
      <th>Дата</th>
      <th>Валюта</th>
      <th>Курс</th>
      <th>Операція</th>
      <th>Сума</th>
      <th>Керування</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${exchangeOperationList}" var="exchangeOperation">
      <tr class="${exchangeOperation.status == false ? 'false-status' : ''}">
        <td>${exchangeOperation.id}</td>
        <td>${exchangeOperation.date}</td>
        <td>${exchangeOperation.currency}</td>
        <td>${exchangeOperation.rate}</td>
        <td>${exchangeOperation.operation}</td>
        <td>${exchangeOperation.amount}</td>
        <td>
          <c:choose>
            <c:when test="${exchangeOperation.status eq true}">
              <form action="/admin/journal/delete" method="POST">
                <input type="hidden" name="id" value="${exchangeOperation.id}">
                <button type="submit">Видалити</button>
              </form>
            </c:when>
            <c:when test="${exchangeOperation.status eq false}">
              <form action="/admin/journal/restore" method="POST">
                <input type="hidden" name="id" value="${exchangeOperation.id}">
                <button type="submit">Відновити</button>
              </form>
            </c:when>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

<div class="container">
  <a href="/admin/currencies">Курси валют</a>
  <a href="${pageContext.request.contextPath}/">Назад</a>
</div>



</body>
</html>