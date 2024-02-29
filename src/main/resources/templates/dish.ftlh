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
    <hr>

    <b>Название блюда: </b>${dish.name}<br>
    <b>Описание блюда: </b>${dish.description}<br>
    <b>Цена блюда: </b>${dish.price} руб.<br>
    <b>Время приготовления
        блюда: </b>${dish.getCookingTime().hour} ч / ${dish.getCookingTime().minute} мин / ${dish.getCookingTime().second} с
    <br>
    <hr>

    <#if dish.getImages()?has_content>
        <b>Изображения блюда: </b><br>
        <ul>
            <#list dish.getImages() as image>
                <li><img src="/image/${image.id}" height="60px" alt="img"></li>
            </#list>
        </ul>
    <#else>
        <b>Изображений блюда нет.</b><br>
    </#if>

    <form action="/dish/delete/${dish.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить блюдо">
    </form>
<#else>
    <h4>Блюдо с таким id не существует.</h4>
    <hr>
</#if>

</body>
</html>
