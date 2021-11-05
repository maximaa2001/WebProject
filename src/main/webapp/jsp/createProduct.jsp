<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 02.11.2021
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_end.css">
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/createProduct.css">
</head>
<body>

<div class="wrapper">
  <div class="content">
<jsp:include page="static/header.jsp"></jsp:include>
<div class="div_create">
  <label id="main_label_create">Создание объявления</label>
  <form class="form_create" action="controller" method="post" enctype="multipart/form-data">
    <input name="command" value="create_product" hidden>

    <div class="label_input">
      <div class="div_label"><label for="input_1" class="label_create">Название</label></div>
      <div class="div_input"><input id="input_1" type="text" name="name" placeholder="Название" autocomplete="off" required>
      </div>
    </div>

    <div class="label_input">
      <div class="div_label"><label class="label_create">Дата</label> </div>
      <div class="div_input">
        <select required name="date" class="select_date">
          <option disabled selected>Дата</option>
        </select>
      </div>
    </div>

    <div class="label_input">
      <div class="div_label">
        <label for="input_2" class="label_create">Пробег</label>
      </div>
      <div class="div_input">
        <input id="input_2" type="text" name="run" placeholder="Пробег" autocomplete="off" required>
      </div>
    </div>

    <div class="label_input">
      <div class="div_label">
        <label class="label_create">Объем двигателя</label>
      </div>
      <div class="div_input"> <select required name="volume_engine" class="select_engine">
        <option disabled selected>Объем</option>
      </select>
      </div>
    </div>

    <div class="label_input">
      <div class="div_label">
        <label for="input_3" class="label_create">Цена в $</label>
      </div>
      <div class="div_input">
        <input id="input_3" type="text" name="price" placeholder="Цена" autocomplete="off">
      </div>
    </div>

    <div class="div_create_type_transmission">
      <label class="label_create">Механика<input type="radio" name="type_transmission" value="mechanic"></label>
      <label class="label_create">Автомат<input type="radio" name="type_transmission" value="automat"></label>
    </div>



    <div class="div_textarea"> <label for="area" class="label_create">Описание</label>
      <textarea id="area" class="textarea_description" name="description" autocomplete="off" required></textarea>
    </div>

    <div class="div_all_images">
      <div class="div_one_image" id="left_img_one">
        <img src="" alt="Загрузите фото" id="img_1" class="image">
        <input id="input_image_1" type="file" class="input_image" name="image_1" hidden>
        <label for="input_image_1" class="load_image">Загрузить фото</label>
      </div>
      <div class="div_one_image">
        <img src="" alt="Загрузите фото" id="img_2" class="image">
        <input id="input_image_2" type="file" class="input_image" name="image_2"hidden>
        <label for="input_image_2" class="load_image">Загрузить фото</label>
      </div>
      <div class="div_one_image" id="right_img_one">
        <img src="" alt="Загрузите фото" id="img_3" class="image">
        <input id="input_image_3" type="file" class="input_image" name="image_3" hidden>
        <label for="input_image_3" class="load_image">Загрузить фото</label>
      </div>
    </div>
    <div class="div_all_images">
      <div class="div_one_image" id="left_img_two">
        <img src="" alt="Загрузите фото" id="img_4" class="image">
        <input id="input_image_4" type="file" class="input_image" name="image_4" hidden>
        <label for="input_image_4" class="load_image">Загрузить фото</label>
      </div>
      <div class="div_one_image">
        <img src="" alt="Загрузите фото" id="img_5" class="image">
        <input id="image_5" type="file" class="input_image" name="image_5" hidden>
        <label for="image_5" class="load_image">Загрузить фото</label>
      </div>
      <div class="div_one_image" id="right_img_two">
        <img src="" alt="Загрузите фото" id="img_6" class="image">
        <input id="image_6" type="file" class="input_image" name="image_6"hidden>
        <label for="image_6" class="load_image">Загрузить фото</label>
      </div>
    </div>

    <input type="submit" value="Создать" class="submit_create">
  </form>
</div>
  </div>
  <jsp:include page="static/footer.jsp"></jsp:include>
</div>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/createProduct.js"></script>

</body>
</html>
