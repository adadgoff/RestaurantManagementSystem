<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Management System (RMS)</title>
</head>

<body>
<h1>Restaurant Management System (RMS)</h1>
<hr>

<h4>Меню</h4>
<form action="/" method="get">
    Поиск по названию блюда: <input type="text" name="name"/><br>
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
<form action="/dish/create" method="post">
    Название: <input type="text" name="name"/><br>
    Описание: <input type="text" name="description"/><br>
    Цена: <input type="number" name="price"/><br>
    <input type="submit" value="Добавить товар"/>
</form>

</body>
</html>