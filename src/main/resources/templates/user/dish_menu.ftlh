<#include "../components/header.ftlh">

<h3>Меню</h3>

<hr>

<form action="/dish/menu" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    Поиск по названию блюда: <input type="text" name="name"/><br>
    <input type="submit" value="Поиск"/>
</form>

<form action="/order/create" method="post">
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
            <th style="background-color: aqua">количество в заказе</th>
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
                <td style="background-color: aqua">
                    <input type="number" min="0" max="${dish.count}" step="1" value="0"
                           name="dishCounts[${dish.id}]" title="количество блюда ограничено"/>
                </td>
            </tr>
        </#list>
    </table>

    <hr>

    <h3>Для создания заказа укажите количество блюд в заказе</h3>

    <hr>

    <input type="submit" value="создать заказ"/>
</form>

<#include "../components/footer.ftlh">
