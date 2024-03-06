<#include "../components/header.ftlh">

<h3>Панель администратора: блюда</h3>

<hr>

<h3>Создать новое блюдо</h3>
<form action="/dish/create" method="post" enctype="multipart/form-data">
    Название блюда: <input type="text" name="name"/><br>
    Описание блюда: <input type="text" name="description"/><br>
    Цена блюда: <input type="number" min="0" max="9223372036854775807" step="1" name="price" title="Цена должна быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
    <#-- TODO: limit neither count of files neither MB size -->
    <#-- TODO: add active -->
    Изображения блюда: <input type="file" name="files" multiple accept=".png, .jpg, .jpeg, .gif"/><br>
    Время приготовления блюда: <input type="text" pattern="${regexTime}" name="cookingTimeStr" placeholder="HH:mm:ss" required title="Введите время приготовления в формате HH:mm:ss с нулями"/><br>
    Количество блюда: <input type="number" name="count" min="0" max="9223372036854775807" step="1" title="Количество должно быть целым неотрицательным числом, меньшим 9 223 372 036 854 775 807."/><br>
    Вес блюда: <input type="number" name="weight" min="0" max="2147483647" step="1" title="Цена должна быть целым неотрицательным числом, меньшим 2 147 483 647."><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар"/>
</form>

<hr>

<h3>Все блюда</h3>
<table style="border-collapse: collapse">
    <tr>
        <#-- TODO:       <th>описание + картинки</th> + отзывы в подробнее -->
        <th>id</th>
        <th>название</th>
        <th>цена (руб)</th>
        <th>время приготовления</th>
        <th>количество</th>
        <th>вес (в граммах)</th>
        <th>статус активности (в меню/убрали)</th>
        <th>подробнее</th>
        <th>редактировать</th>
        <th>удалить</th>
    </tr>
    <#list dishes as dish>
        <td>${dish.id}</td>
        <td>${dish.name}</td>
        <td>${dish.price} руб</td>
        <td>${dish.cookingTime.toHoursPart()} часов / ${dish.cookingTime.toMinutesPart()} минут / ${dish.cookingTime.toMinutesPart()} секунд</td>
        <td>${dish.count} штук</td>
        <td>${dish.weight} грамм</td>
        <td>${dish.active}</td>
        <td><a href="/dish/${dish.id}">подробнее</a></td>
        <td><a href="/admin/dish/${dish.id}">редактировать</a></td>
        <td><a href="/admin/dish/delete/${dish.id}">удалить</a></td>
    </#list>
</table>

<#include "../components/footer.ftlh">
