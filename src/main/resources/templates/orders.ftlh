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
            Блюда в статусе `Готовится`: ${order.cookingDishes}<br>
            Блюда в статусе `Готов`: ${order.cookedDishes}<br>
            Стоимость заказа: ${order.cost} руб.<br>
            Начало заказа: ${order.startTime}<br>
            Конец заказа: ${order.endTime}<br>
            Статус заказа: `${order.status}`<br>
        </p>
        <hr>
    </div>
</#list>
<hr>

<h3>Создать заказ</h3>
<form action="/order/create" method="post">
    id блюд в заказе через пробел: <input type="text" name="orderDishIdsStr" pattern="${regexIds}" title="Введите id блюд числами через пробел."><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить заказ"/>
</form>

</body>
</html>