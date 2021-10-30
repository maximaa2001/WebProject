<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 24.09.2021
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <style> <%@include file='/css/style.css' %> </style>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<jsp:include page="static/header.jsp"></jsp:include>
<div id="div_form">
    <form class="firstForm" method="post" action="controller">
        <input type="hidden" name="command" value="log_in">
        <label id="label_auth">Авторизация</label>
        <label id="label_login" class="label_field">Login<input class="inputData" type="text" name="login" placeholder="email" autocomplete="off" required></label>
        <label id="label_password" class="label_field">Password<input class="inputData" type="password" name="password" placeholder="password" autocomplete="off" required></label>
        <input  id="btn_submit_log_in" type="submit" value="Вход">
    </form>
    <div id="block_error">${requestScope.un_approve} ${requestScope.incorrect_login} ${requestScope.incorrect_password}</div>
    <div id="no_account"><a href="${pageContext.request.contextPath}/controller?command=go_to_reg_in">Нет аккаунта?</a></div>
</div>

<%--${requestScope.un_approve}--%>
<%--${requestScope.incorrect_login}--%>
<%--${requestScope.incorrect_password}--%>


<jsp:include page="static/footer.jsp"></jsp:include>
</body>
</html>
