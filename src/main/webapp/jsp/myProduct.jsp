<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 27.10.2021
  Time: 19:32
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
<div id="create_product"> <div id="btn_create_product"><a href="#">Создать товар</a></div></div>

        <div class="empty_set">${requestScope.empty_user_set_product}</div>

<c:forEach var="element" items="${sessionScope.concrete_user_set_product}">
    <div class="item"><div class="div_image"><img src="${element.imagePath}" alt="Фото не найдено"></div><div class="div_name">
            ${element.name}</div> <div class="div_volume"><div class="volume_dynamic">${element.volumeEngine}, ${element.typeTransmission}</div><br><div class="div_run">${element.run} км</div></div>
        <div class="div_date">${element.date}</div>  <div class="div_price"> ${element.price}$</div>
        <div class="div_about"><a id="about" href="#">Редактировать</a></div></div>
</c:forEach>

    </div>
<jsp:include page="static/footer.jsp"></jsp:include>
</div>
</body>
</html>
