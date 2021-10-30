<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 22.10.2021
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style> <%@include file='/css/style.css' %> </style>

</head>
<body>

<div class="wrapper">
    <div class="content">

<jsp:include page="static/header.jsp"></jsp:include>

<c:forEach var="element" items="${sessionScope.list_products}">
    <div class="item"><div class="div_image"><img src="${element.imagePath}" alt="Фото не найдено"></div><div class="div_name">
            ${element.name}</div> <div class="div_volume"><div class="volume_dynamic">${element.volumeEngine}, ${element.typeTransmission}</div><br><div class="div_run">${element.run} км</div></div>
        <div class="div_date">${element.date}</div>  <div class="div_price"> ${element.price}$</div>
        <div class="div_about"><a id="about" href="#">Подробнее</a></div></div>
</c:forEach>

    </div>
<jsp:include page="static/footer.jsp"></jsp:include>
    </div>
</body>
</html>
