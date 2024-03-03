<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Management System (RMS)</title>
</head>

<body>
<h1>Restaurant Management System (RMS)</h1>
<hr>

<h4>Заказы</h4>
<hr>

<#list orders as order>
    <div>
        <hr>
        <p>
            <b>id заказа: ${order.id}</b><br>
            Блюда в статусе `Готовится`: <#list order.cookingDishes as cookingDish>${cookingDish.name},&nbsp;</#list><br>
            Блюда в статусе `Готов`: <#list order.cookedDishes as cookedDish>${cookedDish.name},&nbsp;</#list><br>
            Стоимость заказа: ${order.cost} руб.<br>
            Начало заказа: ${order.startTime}<br>
            Конец заказа:<#if order.endTime??>${order.endTime}<#else>NULL</#if><br>
            Статус заказа: `${order.status}`<br>
        </p>
        <hr>
    </div>
</#list>
<hr>

<h3>Создать заказ</h3>
<form action="/order/create" method="post">
    <#-- TODO: implement many dishes to order -->
    Введите id блюда в заказе: <input type="number" name="idDishToCook"/>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить заказ"/>
</form>

</body>
</html>