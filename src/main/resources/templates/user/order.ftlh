<#include "../components/header.ftlh">

<#if !error??>
    <h3>Подробная информация о заказе(id=${order.id})</h3>

    <hr>

    <b>Id заказа: </b>${order.id}<br>
    <b>Почта пользователя, оформившего заказ: </b>${user.email}<br>
    <b>Имя пользователя, оформившего заказ: </b>${user.name}<br>

    <hr>

    <h3>Блюда в процессе приготовления</h3>
    <table border="1">

    </table>

    <hr>

    <h3>Приготовленные блюда</h3>
    <table border="1">

    </table>

    <hr>

    <b>Стоимость заказа: </b>${order.cost} руб.<br>
    <b>Начало выполнения заказа: </b>${TimeUtils.FromInstantToMoscowDateTime(order.startTime)}<br>
    <b>Окончание выполнения
        заказа: </b><#if order.endTime??>${TimeUtils.FromInstantToMoscowDateTime(order.endTime)}<#else>В процессе приготовления</#if>
    <br>
    <b>Статус заказа: </b>${order.status}<br>

    <hr>
    <h3>Добавить блюда в заказ, пока он еще готовится</h3>

    <form action="/order/update" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <table border="1">
            <tr>
                <th>id</th>
                <th>название</th>
                <th>цена (руб)</th>
                <th>время приготовления</th>
                <th>количество</th>
                <th>вес (в граммах)</th>
                <th>подробнее</th>
                <th style="background-color: pink">добавить в заказ</th>
            </tr>
            <#list dishes as dish>
                <tr>
                    <td>${dish.id}</td>
                    <td>${dish.name}</td>
                    <td>${dish.price} руб</td>
                    <td>${dish.cookingTime.toHoursPart()} часов / ${dish.cookingTime.toMinutesPart()} минут
                        / ${dish.cookingTime.toMinutesPart()} секунд
                    </td>
                    <td>${dish.count} (в штуках)</td>
                    <td>${dish.weight} (в граммах)</td>
                    <td><a href="/dish/${dish.id}" target="_blank">подробнее</a></td>
                    <td style="background-color: pink">
                        <input type="number" min="0" max="${dish.count}" step="1" value="0"
                               name="dishCounts[${dish.id}]" title="количество блюда ограничено"/>
                    </td>
                </tr>
            </#list>
        </table>

        <hr>

        <input type="submit" value="добавить блюда в заказ"/>
    </form>

    <form action="" method="post">

    </form>
<#else>
    <h3>${error}</h3>
</#if>

<#include "../components/footer.ftlh">
