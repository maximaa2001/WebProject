<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 13.10.2021
  Time: 0:58
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
<c:set var="role" value="${sessionScope.get('role')}"/>
<nav>
    <div id="name">MyFirstWebProject</div>
    <ul id="menu_reference">
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=go_to_main" class="menu_ref_item">Главная</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=show_catalog" class="menu_ref_item">Каталог</a>
        </li>
    </ul>

    <c:choose>
        <c:when test="${empty role}">
    <div id="block_activity">
        <div id="menu_log_in"><a href="${pageContext.request.contextPath}/controller?command=go_to_log_in">Авторизация</a></div>
        <div id="menu_reg_in"><a href="${pageContext.request.contextPath}/controller?command=go_to_reg_in">Регистрация</a></div>
    </div>
        </c:when>
        <c:when test="${not empty role}">
            <c:choose>
                <c:when test="${role == 'user'}">
                    <ul id="menu_profile">
                        <div class="role">Role:</div> <div id="role_user">${role}</div>
                        <li class="profile"><a class="btn_my_profile">Мой профиль</a>
                            <ul id="submenu_user" class="hidden">
                                <li class="submenu_li">
                                    <div id="list_products" class="list_element"><a href="${pageContext.request.contextPath}/controller?command=find_user_product">Список моих объявлений</a></div>
                                </li>
                                <li class="submenu_li">
                                    <div id="edit_profile" class="list_element"><a href="#">Редактировать профиль</a></div>
                                </li>
                                <li class="submenu_li">
                                    <div id="log_out_iser" class="list_element"><a href="${pageContext.request.contextPath}/controller?command=log_out">Выход</a></div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${role == 'admin'}">
                    <ul id="menu_profile">
                        <div id="role">Role:</div> <div id="role_admin">${role}</div>
                        <li class="profile"><a class="btn_my_profile">Мой профиль</a>
                            <ul id="submenu_admin" class="hidden">
                                <li class="submenu_li">
                                    <div id="new_users" class="list_element"><a href="#">Новые пользователи</a></div>
                                </li>
                                <li class="submenu_li">
                                    <div id="all_users" class="list_element"><a href="#">Список пользователей</a></div>
                                </li>
                                <li class="submenu_li">
                                    <div id="log_out_admin" class="list_element"><a href="${pageContext.request.contextPath}/controller?command=log_out">Выход</a></div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
            </c:choose>
        </c:when>
    </c:choose>
</nav>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/header.js"></script>
</body>
</html>
