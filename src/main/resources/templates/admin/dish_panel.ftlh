<#include "../components/header.ftlh">

<h3>Панель администратора: блюда</h3>

<hr>

<h3>Создать новое блюдо</h3>
<form action="/admin/dish/create" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    Название блюда: <input type="text" name="name"/><br>
    Описание блюда: <input type="text" name="description"/><br>
    Цена блюда (в руб): <input type="number" min="0" max="9223372036854775807" step="1" name="price"
                       title="Цена должна быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
    Время приготовления блюда: <input type="text" pattern="${regexTime}" name="cookingTimeStr" placeholder="HH:mm:ss"
                                      required title="Введите время приготовления в формате HH:mm:ss с нулями"/><br>
    Количество блюда (в штуках): <input type="number" name="count" min="0" max="9223372036854775807" step="1"
                             title="Количество должно быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
    Вес блюда (в граммах): <input type="number" name="weight" min="0" max="2147483647" step="1"
                      title="Цена должна быть целым неотрицательным числом, меньшим 2 147 483 647."><br>
    Статус активности блюда (в актуальном меню/в разработке): <input type="checkbox" name="active" checked/><br>
    <#-- TODO: limit neither count of files neither MB size -->
    Изображения блюда: <input type="file" name="files" multiple accept=".png, .jpg, .jpeg, .gif"/><br>

    <input type="submit" value="добавить блюдо"/>
</form>

<hr>

<h3>Все блюда</h3>

<form action="/admin/dish/panel" method="get">
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
        <th>статус активности (в меню/убрали)</th>
        <th>подробнее/редактировать</th>
        <th>удалить</th>
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
            <td>${dish.active?c}</td>
            <td><a href="/admin/dish/${dish.id}">подробнее/редактировать</a></td>
            <td>
                <form action="/admin/dish/delete/${dish.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="удалить">
                </form>
            </td>
        </tr>
    </#list>
</table>

<#include "../components/footer.ftlh">
