<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 30.10.2021
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="UTF-8" />
    <title>Title</title>
<%--    <style>--%>
<%--        <%@include file="/css/search.css"%>--%>
<%--    </style>--%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
</head>
<body>
<form class="div_finder" action="controller" method="post">
    <div class="div_finder_top">
        <div class="div_year_finder">
            <div class="block_year">
                <div class="label_from_year" >Год от</div>
                <select class="select_year_from" name="select_year_from">
                    <option selected disabled class="start" id="start">Год от</option>
                </select>
            </div>

            <div class="block_year">
                <div class="label_to_year">До</div>
                <select class="select_year_to" name="select_year_to">
                    <option selected disabled class="start" id="end">до</option>
                </select>
            </div>

        </div>

        <div class="div_price_finder">
            <input type="text" class="price" id="start_price" placeholder=" " autocomplete="off">
            <label for="start_price" class="label_price_from">Цена от</label>
            <input type="text" class="price" id="end_price" placeholder=" " autocomplete="off">
            <label for="end_price" class="label_price_end">До</label>
            <div class="dollar">$</div>
        </div>


        <div class="div_engine_finder">
            <div class="block_engine">
                <div class="label_from_engine" >Объем от</div>
                <select class="select_engine_from" name="select_engine_from">
                    <option selected disabled class="start" id="start_engine">Объем от</option>
                </select>
            </div>

            <div class="block_engine">
                <div class="label_to_engine" >До</div>
                <select class="select_engine_to" name="select_engine_to">
                    <option selected disabled class="start" id="end_engine">до</option>
                </select>
            </div>
        </div>

    </div>

    <div class="blue_line">
    </div>

    <div class="div_finder_down">
        <div class="div_type_transmission">
            <div class="both_type">
                <input type="radio" class="input_type_tr" id="input_mechanic" name="input_type_transmission" value="mechanic" hidden>
                <input type="radio" class="input_type_tr" id="input_automat" name="input_type_transmission" value="automat" hidden>
                <label for="input_mechanic" id="label_mechanic" >Механика</label>
                <label for="input_automat" id="label_automat" >Автомат</label>
            </div>
        </div>

        <div class="div_run_search">
            <input type="text" id="input_run" name="run" placeholder=" " autocomplete="off">
            <label for="input_run" id="label_run">Макcимальный пробег</label>
        </div>

        <div class="div_name_search">
            <input type="text" id="input_name" name="name" placeholder=" " autocomplete="off">
            <label for="input_name" id="label_name">Название</label>
        </div>
    </div>

    <div class="div_submit">
        <button type="button" id="drop">Сбросить все</button>
        <input type="submit" value="Найти объявления" class="submit_info">
    </div>

</form>

<div class="div_sort">
    <form class="form_sort">
        <span class="span_start">Сначала</span>
        <select class="select_sort">
            <option selected>Без сортировки</option>
            <option>Дорогие</option>
            <option>Дешевые</option>
            <option>Новые по году</option>
            <option>Старые по году</option>
            <option>С наименьшим пробегом</option>
            <option>С наибольшим объемом</option>
            <option>С наименьшим объемом</option>
        </select>

        <input class="btn_sort" type="submit" value="Отсортировать">
    </form>
</div>
<%--<script charset="UTF-8">--%>
<%--    <%@include file="/js/search.js"%>--%>
<%--</script>--%>
<script>
    function setText(a){
        a.textContent = "Любой";
        a.value = "Любой";
    }
</script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/search.js"></script>
</body>
</html>
