<#include "../components/header.ftlh">

<h3>Меню</h3>

<form action="/dish/menu" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    Поиск по названию блюда: <input type="text" name="name"/><br>
    <input type="submit" value="Поиск"/>
</form>

<table border="1">
    <tr>
        <th>id</th>
        <th>название</th>
        <th>цена (руб)</th>
        <th>время приготовления</th>
        <th>количество</th>
        <th>вес (в граммах)</th>
        <th>подробнее</th>
    </tr>
    <#list dishes as dish>
        <tr>
            <td>${dish.id}</td>
            <td>${dish.name}</td>
            <td>${dish.price} руб</td>
            <td>${dish.cookingTime.toHoursPart()} часов / ${dish.cookingTime.toMinutesPart()} минут / ${dish.cookingTime.toMinutesPart()} секунд</td>
            <td>${dish.count} (в штуках)</td>
            <td>${dish.weight} (в граммах)</td>
            <td><a href="/dish/${dish.id}">подробнее</a></td>
        </tr>
    </#list>
</table>

<#include "../components/footer.ftlh">
