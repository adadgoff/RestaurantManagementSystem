<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Management System (RMS)</title>
</head>

<body>
<h1>Restaurant Management System (RMS) | <a href="/login">Войти</a></h1>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>
<hr>

<h4>Меню</h4>
<hr>

<form action="/" method="get">
    Поиск по названию блюда: <input type="text" name="name"/><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Поиск"/>
</form>

<#list dishes as dish>
    <div>
        <p><b>${dish.name}</b> ${dish.price} руб. | <a href="dish/${dish.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<hr>

<h3>Создать новое блюдо</h3>
<form action="/dish/create" method="post" enctype="multipart/form-data">
    Название блюда: <input type="text" name="name"/><br>
    Описание блюда: <input type="text" name="description"/><br>
    Цена блюда: <input type="number" min="0" max="9223372036854775807" step="1" name="price" title="Цена должна быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
    <#-- TODO: implement weight -->
    <#--    Вес блюда: <input type="number"> по умолчанию 100г-->
    Изображения блюда: <input type="file" name="files" multiple accept=".png, .jpg, .jpeg, .gif"/><br> <!-- Добавляем multiple, чтобы можно было загрузить несколько файлов -->
    Время приготовления блюда: <input type="text" pattern="${regexTime}" name="cookingTimeStr" placeholder="HH:mm:ss" required title="Введите время приготовления в формате HH:mm:ss с нулями"/><br>
    Вес блюда: <input type="number" name="weight" min="0" max="2147483647" step="1" title="Цена должна быть целым неотрицательным числом, меньшим 2 147 483 647.">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар"/>
</form>

</body>
</html>