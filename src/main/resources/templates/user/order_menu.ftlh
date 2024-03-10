<#include "../components/header.ftlh">

<h3>Ваши заказы (name=${user.name}, email=${user.email})</h3>

<hr>

<#if orders??>
    <table border="1">
        <tr>
            <th>id</th>
            <th>стоимость (руб.)</th>
            <th>начало выполнения заказа</th>
            <th>окончание выполнения заказа</th>
            <th>статус заказа</th>
            <th>подробнее/добавить блюдо в готовящийся заказ</th>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.cost} руб.</td>
                <td>${TimeUtils.FromInstantToMoscowDateTime(order.startTime)}</td>
                <td><#if order.endTime??>${TimeUtils.FromInstantToMoscowDateTime(order.endTime)}<#else>В процессе приготовления</#if></td>
                <td>${order.status}</td>
                <th><a href="/order/${order.id}">подробнее/добавить блюдо в готовящийся заказ</a></th>
            </tr>
        </#list>
    </table>

<#else>

    <h3>У вас пока что нет заказов.</h3>

</#if>

<#include "../components/footer.ftlh">
