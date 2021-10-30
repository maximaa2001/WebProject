<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 17.10.2021
  Time: 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>

<footer class="fixed_down">
  <div id="footer_first_block">
    <div id="contacts">Контакты</div>
    <div id="telephone">Tel: +375-29-123-45-67</div>
    <div id="email">Email: MyFirstWeb@Project.com</div>
  </div>

  <div id="footer_second_block">
    &copy 2021 Copyright: MyFirstWebProject
  </div>

  <div id="footer_third_block">
    <div id="footer_become_user">Стать пользователем?</div>
    <div id="footer_reg"> <a href="${pageContext.request.contextPath}/controller?command=go_to_reg_in">Зарегистрироваться</a></div>
  </div>
</footer>

</body>
</html>
