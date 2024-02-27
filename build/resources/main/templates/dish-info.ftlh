<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Management System (RMS)</title>
</head>

<body>
<h1>Restaurant Management System (RMS)</h1>
<hr>

<#if dish??>
    <h4>Подробная информация о блюде</h4>
    <b>Название: </b>${dish.name}<br>
    <b>Описание: </b>${dish.description}<br>
    <b>Цена: </b>${dish.price} руб.<br>
    <hr>

    <form action="/dish/delete/${dish.id}" method="post">
        <input type="submit" value="Удалить блюдо">
    </form>
<#else>
    <h4>Блюдо с таким id не существует.</h4>
</#if>

</body>
</html>
