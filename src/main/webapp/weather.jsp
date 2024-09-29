<%--
  Created by IntelliJ IDEA.
  User: juanw
  Date: 29/09/2024
  Time: 11:21 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clima API - Meteoblue</title>
</head>
<body>
<h1>Clima actual de la ciudad especificada</h1>
<c:choose>
    <c:when test="${not empty temperatureMax}">
        <p>Temperaturas máximas de algunos días de la ciudad especificada (Medellín): ${temperatureMax}°C</p>
    </c:when>
    <c:otherwise>
        <p style="color: red;">${error}</p>
    </c:otherwise>
</c:choose>
</body>
</html>



