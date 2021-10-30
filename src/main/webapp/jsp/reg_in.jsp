<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 21.10.2021
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="static/header.jsp"></jsp:include>


<div id="div_form_reg">
    <form class="firstForm" method="post" action="controller">
        <input type="hidden" name="command" value="reg_in">
        <label id="label_reg">Регистрация</label>
        <label id="label_login" class="label_field">Login<input class="inputData" type="text" name="login" placeholder="email" autocomplete="off" value="${requestScope.remember_login}" required></label>
        <div class="label_error"><span>${requestScope.login_error_reg}</span></div>

        <label id="label_password" class="label_field">Password<input class="inputData" type="password" name="password" placeholder="password" autocomplete="off" required></label>
        <div class="label_error"><span>${requestScope.password_error_reg}</span></div>

        <label id="label_password_repeat" class="label_field">Password<input class="inputData" type="password" name="password_repeat" placeholder="repeat password" autocomplete="off" required></label>
        <div class="label_error"><span>${requestScope.error_password_repeat}</span></div>

        <label id="label_number" class="label_field">Number<input class="inputData" type="text" name="number" placeholder="number" autocomplete="off" value="${requestScope.remember_number}" required></label>
        <div class="label_error"><span>${requestScope.number_error_reg}</span></div>

        <input  id="btn_submit_reg_in" type="submit" value="Зарегистрироваться">

        <div class="label_error"><span>${requestScope.number_already_exist}</span></div>
        <div class="reg_success"><span>${requestScope.success_reg}</span></div>
    </form>
    <div id="yes_account"><a href="${pageContext.request.contextPath}/controller?command=go_to_log_in">Уже есть аккаунт?</a></div>
</div>

<jsp:include page="static/footer.jsp"></jsp:include>
</body>
</html>
